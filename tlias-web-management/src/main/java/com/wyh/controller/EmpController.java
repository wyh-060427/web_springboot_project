package com.wyh.controller;

import com.wyh.pojo.Emp;
import com.wyh.pojo.EmpQueryParam;
import com.wyh.pojo.PageResult;
import com.wyh.pojo.Result;
import com.wyh.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询：{}，{}，姓名包含：{}，性别：{}，更新日期起始：{}，终止：{}，",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("插入数据：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除的员工id为{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result message(@PathVariable Integer id){
        log.info("要进行修改信息的员工id为{}",id);
        Emp emp=empService.message(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新完的员工信息为：{}",emp.toString());
        empService.update(emp);
        return Result.success();
    }
}
