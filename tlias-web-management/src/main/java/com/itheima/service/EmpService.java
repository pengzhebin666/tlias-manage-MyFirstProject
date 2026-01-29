package com.itheima.service;

import com.itheima.pojo.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
  /*  PageResult<Emp> queryPage(Integer page, Integer pageSize, String name, Integer gender,
                              LocalDate begin,
                              LocalDate end);*/

    PageResult<Emp> queryPage(EmpQueryParam empQueryParam);

    void save(Emp emp);

    List<EmpOriginal> findall();

    void deletebyids(List<Integer> ids);
//回显
    Emp getById(Integer id);
//修改员工
    void update(Emp emp);


    LoginInfo login(Emp emp);
}
