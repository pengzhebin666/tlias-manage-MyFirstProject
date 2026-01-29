package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
/*
           3.3添加班级
 */
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){

        clazzService.addClazz(clazz);
        return Result.success();
    }


    /*
    3.6查询所有班级
 */
    @GetMapping("list")
    public Result list(){
         List<Clazz> clazzList = clazzService.findall();
         return Result.success(clazzList);
    }


   /*3.1翻页查询班级
 */
    @GetMapping
    public Result queryPage(ClazzQueryParam clazzQueryParam){
        log.info ("员工查询:{}",clazzQueryParam);
        PageResult<Clazz> pageResult= clazzService.queryPage(clazzQueryParam);
        return Result.success(pageResult);
    }

    /*
    3.2根据id删除班级，简单参数
 */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable Integer id) throws Exception {
        log.info("根据id删除部门,");
        clazzService.deleteById(id);
        return Result.success();
    }

    /*
    3.5 修改班级 传递是请求体
     */
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    /*
    3.4 根据id查询班级
     */
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询班级：{}",id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
}
