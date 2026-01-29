package com.itheima.service;

import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> queryPage(EmpQueryParam empQueryParam);
}
