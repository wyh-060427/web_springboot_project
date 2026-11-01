package com.wyh.service.impl;

import com.wyh.mapper.EmpMapper;
import com.wyh.pojo.JobOption;
import com.wyh.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public JobOption countEmpJobData() {
        List<Map<String, Object>> maps = empMapper.countEmpJobData();
        List<Object> pos = maps.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> num = maps.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(pos,num);
    }

    @Override
    public List<Map<String, Object>> countEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
