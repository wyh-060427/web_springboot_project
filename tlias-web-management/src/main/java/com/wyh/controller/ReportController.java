package com.wyh.controller;

import com.wyh.mapper.EmpMapper;
import com.wyh.pojo.JobOption;
import com.wyh.pojo.Result;
import com.wyh.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位员工人数");
        JobOption jobOption=reportService.countEmpJobData();
        return Result.success(jobOption);
    }
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map<String,Object>> mapList=reportService.countEmpGenderData();
        return Result.success(mapList);
    }
}
