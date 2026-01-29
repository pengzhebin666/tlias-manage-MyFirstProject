package com.itheima.controller;

import com.itheima.pojo.ClazzCount;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {
/*
统计员工职位人数的
 */

    @Autowired
    private ReportService reportService;
    @GetMapping ("/empJobData")
    public Result getEmpJobDate(){
        JobOption jobOption = reportService.getEmpJobDate();
        return Result.success(jobOption);
    }


    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        List<Map<String,Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        List<Map<String,Object>> list = reportService.getDegreeData();
        return Result.success(list);
    }
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        ClazzCount clazzCount = reportService.getStudentCountData();
        return Result.success(clazzCount);
    }
}
