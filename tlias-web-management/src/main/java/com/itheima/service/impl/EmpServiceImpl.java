package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

/*    @Override
    public PageResult<Emp> queryPage(Integer page, Integer pageSize, String name, Integer gender,
                                     LocalDate begin,
                                     LocalDate end) {
        //设置翻页参数

        PageHelper.startPage(page,pageSize);
        //执行查询

        List<Emp> empslist = empMapper.list(name, gender, begin, end);
        //解析查询参数并封装
        Page<Emp>p=(Page<Emp>) empslist;

        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }*/

    @Override
    public PageResult<Emp> queryPage(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //执行查询

        List<Emp> empslist = empMapper.list(empQueryParam);
        //解析查询参数并封装
        Page<Emp> p = (Page<Emp>) empslist;

        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }

    /*   @Override
    public PageResult<Emp> queryPage(Integer page, Integer pageSize) {
        Long total = empMapper.count();

        //2. 获取结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //3. 封装结果
        return new PageResult<Emp>(total, rows);
    }*/
    @Transactional  // 只会对RUNtimeException进行事务管理
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        List<EmpExpr> emprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(emprList)) {
            for (EmpExpr empExpr : emprList) {
                empExpr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(emprList);
        }
    }

    @Override
    public List<EmpOriginal> findall() {
        return empMapper.selectall();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletebyids(List<Integer> ids) {
        empMapper.deletebyids(ids);
        empExprMapper.deletebyempids(ids);
    }

    @Override
    public Emp getById(Integer id) {
        // 1. 查询员工信息
        Emp emp = empMapper.getById(id);

            // 2. 查询员工工作经历
    /*    List<EmpExpr> empExprList = empExprMapper.getByEmpId(id);
        emp.setExprList(empExprList);*/
        return emp;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp) {
        //1.根据id修改员工的基本信息
     emp.setUpdateTime(LocalDateTime.now());
     empMapper.updateById(emp);

        //2.根据id修改员工的工作经历信息
        //2.1 先删除 现根据 员工id删除原有的工作经历
        empMapper.deletebyids(Arrays.asList(emp.getId()));
        //2.2在添加 再添加员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)) {
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(emp.getId());
            }
        }

    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp loginEmp = empMapper.getByUsernameAndPassword(emp);
        if(loginEmp != null){
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setId(loginEmp.getId());
            loginInfo.setUsername(loginEmp.getUsername());
            loginInfo.setName(loginEmp.getName());

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginEmp.getId());
            String token = JwtUtils.generateJwt(claims);
            loginInfo.setToken(token);
            return loginInfo;
        }
        return null;
    }
}



