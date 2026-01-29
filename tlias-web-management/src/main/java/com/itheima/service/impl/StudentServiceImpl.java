package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public PageResult<Student> queryPage(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> studentList = studentMapper.list(studentQueryParam);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<Student>(p.getTotal(), p.getResult());

    }

    @Override
    public void deletebyids(List<Integer> ids) {
        studentMapper.deletebyids(ids);
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getById(Integer id) {

        return studentMapper.getByid(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void updateViolation(Integer id, Integer score) {
// 1. 参数合法性校验（避免无效请求进入数据库）
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("学员ID不能为空且必须为正整数");
        }
        if (score == null || score <= 0) {
            throw new IllegalArgumentException("违纪扣分不能为负数或零");
        }

        // 2. 检查学员是否存在（避免更新不存在的学员数据）
        Student student = studentMapper.getByid(id);
        if (student == null) {
            throw new RuntimeException("学员不存在，无法更新违纪信息");
        }

        // 3. 调用 Mapper 层更新违纪数据：违纪次数+1、扣分累加、更新最后修改时间
        studentMapper.updateViolation(id, score);

    }
}
