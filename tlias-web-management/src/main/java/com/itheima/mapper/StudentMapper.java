package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
     List<Student> list(StudentQueryParam studentQueryParam);

     void deletebyids(List<Integer> ids);

     void insert(Student student);

     Student getByid(Integer id);

     void update(Student student);
@MapKey("name")
    List<Map<String, Object>> countEmpDegreeDate();

@MapKey("class")
     List<Map<String, Object>> countClazzCount();

    void updateViolation(@Param("id") Integer id, @Param("score") Integer score);
}
