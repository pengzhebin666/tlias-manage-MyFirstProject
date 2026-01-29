package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {



    void addClazz(Clazz clazz);

    List<Clazz> findall();

    PageResult<Clazz> queryPage(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id) throws  Exception;

    void updateClazz(Clazz clazz);

    Clazz getById(Integer id);
}
