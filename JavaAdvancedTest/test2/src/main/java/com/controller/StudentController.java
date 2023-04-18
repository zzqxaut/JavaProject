package com.controller;
import java.util.List;

import com.dao.StudentDao;
import com.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("studentController")
public class StudentController {
    @Autowired
    public StudentDao studentDao;

    public void addStudent(Student student){
        int row = studentDao.addStudent(student);
        System.out.println("添加了"+row+"条记录");
    }

    public void deleteStudent(int stuid){
        int row = studentDao.deleteStudentByStuid(stuid);
        System.out.println("删除了"+row+"条记录");
    }
    public void updateStudent(Student student)
    {
        studentDao.updateStudent(student);
        System.out.println("更新信息为"+student);
    }
    public void selectAllStudent(){
        List<Student> list = studentDao.selectAllStudent();
        for(Student stu:list){
            System.out.println(stu);
        }
    }
    public void selectStudentByStuId(int stuid){
        Student student = studentDao.selectStudentByStuId(stuid);
        System.out.println(student);
    }
}
