package com.itheima.controller;

import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogContorller {
    @Autowired
    private LogService LogService;

     @GetMapping("/page")
     public Result queryPage(EmpQueryParam empQueryParam){
         log.info ("员工查询:{}",empQueryParam);
         PageResult<OperateLog> pageResult= LogService.queryPage(empQueryParam);
         return Result.success(pageResult);
     }


}
