package com.wyh.controller;

import com.wyh.pojo.Emp;
import com.wyh.pojo.LoginInfo;
import com.wyh.pojo.Result;
import com.wyh.service.EmpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("登录：{}",emp);
        LoginInfo info=empService.login(emp);
        if (info!=null)
        return Result.success(info);
        else return Result.error("登录失败，用户名或密码错误");
    }

}
