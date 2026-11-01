package com.wyh.service;

import com.wyh.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteID(Integer id);

    void add(Dept dept);

    Dept getInfo(Integer id);

    void updateData(Dept dept);
}
