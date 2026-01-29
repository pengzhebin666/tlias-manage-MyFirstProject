package com.itheima.controller;


import com.itheima.annotation.LogInfo;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
部门管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
  /*
  查询所有部门信息
   */
    /*@RequestMapping(value = "/depts",method = RequestMethod.GET)*/
    @GetMapping
    public Result list(){
        log.info("查询所有部门信息");
     List<Dept> deptList = deptService.findall();
     return Result.success(deptList);
    }

/*    @DeleteMapping("/depts")
    public Result deleteById(HttpServletRequest  request){
        String id = request.getParameter("id");
        int i = Integer.parseInt(id);
        System.out.println(i);
        return Result.success();
    }*/
@LogInfo
    @DeleteMapping /*value = "id"就是在url船体的参数名，required 代表这个是否是必传项，后面的id就是java中自带的形参
    如果请求值和参数名一致，那么value和required可以省略，utl传参
    这个是url传参 没有返回值 只有查找才有返回值
   */
    public Result deleteById(/*@RequestParam(value = "id",required = false)*/ Integer id) throws Exception {
        log.info("根据id删除部门,");
        deptService.deleteById(id);
        return Result.success();
    }

/*

- JSON格式的参数，通常会使用一个实体对象进行接收 。
- 规则：JSON数据的键名与方法形参对象的属性名相同，并需要使用@RequestBody注解标识。
在接口文档中他值传一个name的json值
 */@LogInfo
    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        deptService.addDept(dept);
        return Result.success();
    }
    /*
   - GET ：  查询
- POST ：新增
- PUT ：  修改
- DELETE ：删除
     */

    @GetMapping("{id}")
/*    public Result getById(@PathVariable("id") Integer deptId){*/
    public Result getById(@PathVariable Integer id){
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
//这个查询语句 还需要封装成json 返回前端

    @LogInfo
    @PutMapping
    public Result updateDept(@RequestBody Dept dept){
        deptService.updateDept(dept);
        return Result.success();
    }
}
