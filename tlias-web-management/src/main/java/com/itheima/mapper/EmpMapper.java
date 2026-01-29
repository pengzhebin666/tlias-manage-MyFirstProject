package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpOriginal;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

/*    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id ")
    Long count();
    @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id " +
            " order by  update_time desc  limit #{start}, #{pageSize}")
    List<Emp> list(Integer start, Integer pageSize);*/
//使用插件

   /* List<Emp> list( String name, Integer gender,
                   LocalDate begin,
                   LocalDate end);*/
/*

 */
    List<Emp> list(EmpQueryParam empQueryParam);


    @Options(useGeneratedKeys = true,keyColumn = "id")//获取到生成的主键
@Insert("insert into emp( username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)\n" +
        "    values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);


    @Select("select emp.*, d.name as deptName from emp left join tlias.dept d on d.id = emp.dept_id")
    List<EmpOriginal> selectall();

    void deletebyids(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);
@MapKey("pos")
  List<Map<String,Object>> countEmpJobDate();
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderDate();

@Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
