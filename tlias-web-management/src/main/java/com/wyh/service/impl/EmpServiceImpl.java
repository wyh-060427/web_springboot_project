package com.wyh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyh.mapper.EmpExprMapper;
import com.wyh.mapper.EmpMapper;
import com.wyh.pojo.*;
import com.wyh.service.EmpLogService;
import com.wyh.service.EmpService;
import com.wyh.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp>empList=empMapper.list(empQueryParam);
        Page<Emp> p=(Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
    //@Transactional //事务管理 - 默认出现运行时异常RuntimeException才会回滚
    @Transactional(rollbackFor = {Exception.class}) //加上参数，指定所有异常都会回滚
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insert_emp_expr(exprList);
            }
        }finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(),emp.toString());
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        empMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp message(Integer id) {
        Emp emp=empMapper.searchEmp(id);
        List<EmpExpr> empExpr=empMapper.searchEmpExpr(id);
        emp.setExprList(empExpr);
        return emp;
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据ID修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.根据ID修改员工的工作经历信息
        //2.1 先删除
        empMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2，2 再添加
        List<EmpExpr> exprList=emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insert_emp_expr(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e=empMapper.selectByUsernameAndPassword(emp);
        if(e!=null){
           log.info("登录成功，员工信息：{}",e);
            Map<String, Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String s = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),s);
        }
        return null;
    }
    //----------原始分页查询逻辑----------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long count=empMapper.count();
//        Integer start=(page-1)*pageSize;
//        List<Emp> list=empMapper.list(start,pageSize);
//        return new PageResult<Emp>(count,list);
//    }
}
