package com.controller;

import com.po.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testStudentController {
    @Test
    public void testAddStudent(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentController studentController = (StudentController) applicationContext.getBean("studentController");
        Student stu = new Student();
        stu.setStuname("张三");
        stu.setStusex("2");
        stu.setStunum("111111111");
        studentController.addStudent(stu);
    }
    @Test
    public void testSelectAllStudent()
    {
        ApplicationContext appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentController suc = (StudentController)appCon.getBean("studentController");
        suc.selectAllStudent();
    }
    @Test
    public void testUpdateStudent()
    {
        ApplicationContext appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentController suc = (StudentController)appCon.getBean("studentController");
        Student stu = new Student();
        stu.setStuid(2);
        stu.setStuname("张一");
        stu.setStusex("1");
        stu.setStunum("66666");
        suc.updateStudent(stu);
    }
    @Test
    public void testDeleteStudentByStuid()
    {
        ApplicationContext appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentController suc = (StudentController)appCon.getBean("studentController");
        suc.deleteStudent(2);
    }
    @Test
    public void testSelectStudentByStuId()
    {
        ApplicationContext appCon = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentController suc = (StudentController)appCon.getBean("studentController");
        suc.selectStudentByStuId(3);
    }
}
