package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClazzCount;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JobOption getEmpJobDate() {
        //1调用mapper接口 获取 数据
        List<Map<String,Object>> list = empMapper.countEmpJobDate();
        //2 初始化两个列表
        ArrayList<String> jobList = new ArrayList<>();
        ArrayList<Integer> dataList = new ArrayList<>();
        //3 遍历拆分数据
        for (Map<String, Object> map : list) {
            jobList.add(map.get("pos").toString());
            dataList.add(Integer.parseInt(map.get("num").toString()));
        }
        JobOption jobOption = new JobOption(jobList,dataList);


        return jobOption;
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderDate();
    }

    @Override
    public List<Map<String, Object>> getDegreeData() {
      return  studentMapper.countEmpDegreeDate();
    }

    @Override
    public ClazzCount getStudentCountData() {
        //1调用mapper接口 获取 数据
        List<Map<String,Object>> list = studentMapper.countClazzCount();
        //2 初始化两个列表
        ArrayList<String> clazzList = new ArrayList<>();
        ArrayList<Integer> dataList = new ArrayList<>();
        //3 遍历拆分数据
        for (Map<String, Object> map : list) {
            clazzList.add(map.get("class").toString());
            dataList.add(Integer.parseInt(map.get("num").toString()));
        }
        ClazzCount clazzCount = new ClazzCount(clazzList,dataList);
        return clazzCount;
    }
}
