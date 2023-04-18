package com.test;

import com.pojo.HelloWorld;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloWorldTest {

    @Test
    public void test(){
        //获取IOC容器,ApplicationContext对程序员开放
        //FileSystemXmlApplicationContext,通过磁盘路径获取xml配置
        //ClassPathXmlApplicationContext,通过类路径xml配置
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取IOC容器中的Bean
        HelloWorld helloworld = (HelloWorld) ioc.getBean("helloworld");
        //调用其方法
        helloworld.sayHello();

    }
}
