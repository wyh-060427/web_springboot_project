package com.wyh.mapper;

import com.wyh.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

@Mapper
public interface DeptMapper {
    List<Dept> findAll();

    void deleteID(Integer id);

    void add(Dept dept);

    Dept getId(Integer id);

    void updateDate(Dept dept);
}
