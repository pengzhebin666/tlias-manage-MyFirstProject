package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.annotation.LogOperation;
import com.itheima.exception.PeopleException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }

    @Override
    public List<Clazz> findall() {
        return clazzMapper.selectAll();
    }

    @Override
    public PageResult<Clazz> queryPage(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
       List<Clazz> clazzlist = clazzMapper.list(clazzQueryParam);
       Page<Clazz> p =(Page<Clazz>) clazzlist;
       return new PageResult<Clazz>(p.getTotal(),p.getResult());




    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) throws Exception {
        Integer stuCount = clazzMapper.countStuByDeptId(id);
        if (stuCount > 0) {
            // 2. 如果有员工，抛出业务异常（全局异常处理器会捕获并返回提示）
            throw new PeopleException("对不起，当前部门下有员工，不能直接删除！");
        }
        clazzMapper.deleteById(id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        Clazz clazz = clazzMapper.getById(id);
        return clazz;
    }
}
