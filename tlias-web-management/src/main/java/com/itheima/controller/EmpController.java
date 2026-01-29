package com.itheima.controller;

import com.itheima.annotation.LogInfo;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

 /*   @GetMapping
    public Result queryPage(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            String name, Integer gender,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info ("员工查询:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
       PageResult<Emp> pageResult= empService.queryPage(page,pageSize,name,gender,begin,end);
       return Result.success(pageResult);*/
        @GetMapping
        public Result queryPage(EmpQueryParam empQueryParam){
            log.info ("员工查询:{}",empQueryParam);
            PageResult<Emp> pageResult= empService.queryPage(empQueryParam);
            return Result.success(pageResult);
    }
    @LogInfo
         @PostMapping
         public Result save(@RequestBody Emp emp) throws Exception {
            log.info ("员工保存:{}",emp);
            empService.save(emp);
            return Result.success();
         }

         /**
          *
          * 查询全部员工
          */
         @GetMapping( "list")
         public Result list(){
            log.info("查询所有员工信息");
            List<EmpOriginal> empList = empService.findall();
            return Result.success(empList);
         }

/**
 * 员工删除
 */
@LogInfo
@DeleteMapping()
    public Result deleteByIDs(@RequestParam List<Integer> ids){
        log.info("员工删除：{}",ids);
        empService.deletebyids(ids);
        return Result.success();
    }


    @GetMapping("/{id}")
   public Result getById (@PathVariable Integer id){
        log.info("员工查询：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }


    @LogInfo
    @PutMapping
    public Result update(@RequestBody Emp emp){
    log.info("员工修改：{}",emp);
    empService.update(emp);
    return Result.success();
    }
}
