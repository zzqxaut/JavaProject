package com.dao;

import com.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentDao")
@Mapper
public interface StudentDao {
    public List<Student> selectAllStudent();
    public int addStudent(Student student);
    public int updateStudent(Student student);
    public int deleteStudentByStuid(@Param("stuid")Integer stuid);
    public Student selectStudentByStuId(@Param("stuid")Integer stuid);
}
