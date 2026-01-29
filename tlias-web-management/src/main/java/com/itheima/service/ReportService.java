package com.itheima.service;

import com.itheima.pojo.ClazzCount;
import com.itheima.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobDate();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getDegreeData();


    ClazzCount getStudentCountData();
}
