package com.test.mybatis.test;

import com.test.mybatis.mapper.UserMapper;
import com.test.mybatis.pojo.User;
import com.test.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class mybatistest {
    @Test
    public void testInsert() throws IOException {
        //此段代码是共有的
        //获取核心配置文件的输入流
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder对象，工厂模式对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);//根据输入流创建对象
        //（重要）获取sql的会话对象SqlSession，是MyBatis提供的操作数据库对象(会自动提交事务，无参则不会)
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        //获取UserMapper的代理实现类对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);//传入类，返回实例对象
        //调用mapper接口中的方法，实现添加用户信息的功能
        int result = mapper.insertUser();
        System.out.println("结果：" + result);
        //提交事务,若没设置自动提交，则需要这个
//        sqlSession.commit();
        //关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser();
        sqlSession.close();//不能缺少
    }
    @Test
    public void testDelete(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser();
        sqlSession.close();
    }
    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserByUsername();
        System.out.println(user);
        sqlSession.close();
    }
    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> allUser = mapper.getAllUser();
        allUser.forEach(System.out::println);//循环输出所有对象，::引用println方法
        sqlSession.close();
    }
}

