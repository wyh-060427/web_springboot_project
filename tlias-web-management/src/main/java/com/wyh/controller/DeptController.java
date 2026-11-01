package com.wyh.controller;

import com.wyh.pojo.Dept;
import com.wyh.pojo.Result;
import com.wyh.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.IPv6Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DeptController {
    //private static final Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;
    @GetMapping("/depts")
    public Result list(){
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }
    @DeleteMapping("/depts")
    public Result deleteId(Integer id){
        //System.out.println("根据id为"+id+"的数据");
        log.info("根据id为"+id+"的数据");
        deptService.deleteID(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        //System.out.println("新增部门"+dept);
        log.info("新增部门"+dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("查询id为"+id);
        log.info("查询id为"+id);
        Dept dept=deptService.getInfo(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result updateData(@RequestBody Dept dept){
        //System.out.println("修改为"+dept+"部");
        log.info("修改为"+dept+"部");
        deptService.updateData(dept);
        return Result.success();
    }
}
