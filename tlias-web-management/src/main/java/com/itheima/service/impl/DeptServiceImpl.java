package com.itheima.service.impl;

import com.itheima.exception.PeopleException;
import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findall() {
        return deptMapper.selectall() ;
    }
@Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) throws Exception {
        Integer empCount = deptMapper.countEmpByDeptId(id);
        if (empCount > 0) {
            // 2. 如果有员工，抛出业务异常（全局异常处理器会捕获并返回提示）
            throw new PeopleException("对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void addDept(Dept dept) {
        //补全部门对象的数据 创建时间和更新时间
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
           deptMapper.addDept(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }


}
