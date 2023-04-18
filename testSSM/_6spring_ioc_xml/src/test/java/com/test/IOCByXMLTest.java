package com.test;

import com.pojo.Person;
import com.pojo.Student;
import com.pojo.Class;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCByXMLTest {
    /**
     * 1、根据bean的id获取（唯一性，但获得的类型是Object）
     * 2、根据bean的类型获取(唯一性，若有多个bean类型相同会报错)
     * 3、根据bean的id和类型（交集唯一性）
     * 方法2，3获取的类型都是bean对应实体类，常用方法2
     * 4、通过bean的类实现的接口/继承的父类获取该类（继承该接口的类只能有一个配置bean）
     */
    @Test
    public void testIOD(){
        //获取IOC
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取Bean
        Student student1 = (Student) ioc.getBean("student1");
//        Student student2 = ioc.getBean(Student.class);
//        System.out.println(student2);
        Student student3 = ioc.getBean("student1", Student.class);
        System.out.println(student1);

        System.out.println(student3);

//        Person person = ioc.getBean(Person.class);
//        System.out.println(person);
        Student student4 = ioc.getBean("student2", Student.class);
        System.out.println(student4);
        Student student5 = ioc.getBean("student3", Student.class);
        System.out.println(student5);
        Student student6 = ioc.getBean("student4", Student.class);
        System.out.println(student6);

        Student student7 = ioc.getBean("student5", Student.class);
        System.out.println(student7);

        Student student8 = ioc.getBean("student6", Student.class);
        System.out.println(student8);

        Class class2 = ioc.getBean("class2", Class.class);
        System.out.println(class2);

        Student student9 = ioc.getBean("student7", Student.class);
        System.out.println(student9);

    }
}
