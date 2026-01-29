package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findall();
/*
删除部门
 */
    void deleteById(Integer id) throws Exception;

    void addDept(Dept dept);

    Dept getById(Integer id);


    void updateDept(Dept dept);

}
