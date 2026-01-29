package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    /*
    学员列表查询

     */
    @GetMapping
    public Result queryPage(StudentQueryParam studentQueryParam){
        log.info("员工查询:{}",studentQueryParam);
        PageResult<Student> pageResult= studentService.queryPage(studentQueryParam);
        return Result.success(pageResult);
    }
    /*
    员工删除
     */
    @DeleteMapping("/{ids}")
    public Result deleteByIDs(@PathVariable List<Integer> ids){
        log.info("员工删除：{}",ids);
        studentService.deletebyids(ids);
        return Result.success();
    }
    /*

   添加学员
     */
    @PostMapping
    public Result addStudent(@RequestBody Student student){
        log.info("员工添加:{}",student);
        studentService.save(student);
        return Result.success();
    }
    /*
    根据id查询
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询：{}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /*
    修改员工
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("员工修改：{}",student);
        studentService.update(student);
        return Result.success();
    }
    @PutMapping("violation/{id}/{score}")
    public  Result updateViolation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("员工修改：{}",id);
        studentService.updateViolation(id,score);
        return Result.success();
    }

}
