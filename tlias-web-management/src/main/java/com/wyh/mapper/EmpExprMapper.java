package com.wyh.mapper;

import com.wyh.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    void insert_emp_expr(List<EmpExpr> exprList);
}
