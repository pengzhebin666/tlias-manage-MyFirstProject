package com.itheima.aop;


import cn.hutool.json.JSONUtil;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Slf4j
@Component
@Aspect
public class LogInfoAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.itheima.annotation.LogInfo)")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
       // 操作人
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUseId());
        //操作时间
        operateLog.setOperateTime(LocalDateTime.now());
        //执行方法的全类名
        String name = joinPoint.getTarget().getClass().getName();
        operateLog.setClassName(name);
        //方法名
        operateLog.setMethodName(joinPoint.getSignature().getName());
        //方法运行时参数
        operateLog.setMethodParams(JSONUtil.toJsonStr(joinPoint.getArgs()));
        //方法返回值
        operateLog.setReturnValue(JSONUtil.toJsonStr(result));
        //方法执行时长
        operateLog.setCostTime(end - begin);

        log.info("操作日志：{}",operateLog);

        //- 操作人、操作时间、执行方法的全类名、执行方法名、方法运行时参数、返回值、方法执行时长
      operateLogMapper.insert(operateLog);
        return result;



    }
    private Integer getCurrentUseId() {
        return CurrentHolder.getCurrentId();
    }
}
