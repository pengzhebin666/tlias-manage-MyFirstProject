package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
*员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工工作经历
     * @param emprList
     */
    void insertBatch(List<EmpExpr> emprList);

    void deletebyempids(List<Integer> ids);
}
