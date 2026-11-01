package com.wyh.service;

import com.wyh.mapper.EmpMapper;
import com.wyh.pojo.Emp;
import com.wyh.pojo.EmpQueryParam;
import com.wyh.pojo.LoginInfo;
import com.wyh.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp message(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
