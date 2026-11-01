package com.wyh.service.impl;

import com.wyh.mapper.DeptMapper;
import com.wyh.pojo.Dept;
import com.wyh.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeprServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMappter;
    @Override
    public List<Dept> findAll() {
        return deptMappter.findAll();
    }

    @Override
    public void deleteID(Integer id) {
        deptMappter.deleteID(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMappter.add(dept);
    }

    @Override
    public Dept getInfo(Integer id) {
        return deptMappter.getId(id);
    }

    @Override
    public void updateData(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMappter.updateDate(dept);
    }
}
