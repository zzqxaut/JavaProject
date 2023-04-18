package com.mybatis.mapper;

import com.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    /**
     * 根据ID查询员工信息
     * @param empId
     * @return
     */
    Emp getEmpByEmpId(@Param("empId") Integer empId);

    /**
     * 获取员工以及对应的部门信息
     * @param empId
     * @return
     */
    Emp getEmpAndDeptByEmpId(@Param("empId") Integer empId);

    /**
     * 查询员工及部门信息，分步查询第一步
     * @param empId
     * @return
     */
    Emp getEmpAndDeptByStepOne(@Param("empId") Integer empId);

    /**
     * 查询部门及对应员工，分步查询第二步
     * @param deptId
     * @return
     */
    List<Emp> getDeptAndEmpByStepTwo(@Param("deptId") Integer deptId);//注意不能是员工id！
}
