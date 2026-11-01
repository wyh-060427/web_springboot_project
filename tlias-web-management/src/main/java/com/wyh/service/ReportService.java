package com.wyh.service;

import com.wyh.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();
}
