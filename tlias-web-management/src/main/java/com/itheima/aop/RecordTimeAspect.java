package com.itheima.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
/*@Aspect
@Component*/
public class RecordTimeAspect {
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pointcut(){

    }

   @Around(" pointcut()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - begin)+"ms");
        return result;
    }
    @Before(" @annotation(com.itheima.annotation.LogOperation)")
    public void before(){
        System.out.println("前置通知");
    }
    @After("pointcut()")
    public void after(){
        System.out.println("后置通知");
    }
    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.out.println("返回后通知");
    }
    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        System.out.println("异常后通知");
    }
}
