package com.itheima.mapper;

import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {
    @Select("select o.*,e.username operate_emp_name   from  operate_log o,emp e where o.operate_emp_id=e.id")
    public List<OperateLog> list(EmpQueryParam empQueryParam) ;

}
