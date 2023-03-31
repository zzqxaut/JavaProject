package com.mybatis.test;

import com.mybatis.mapper.UserMapper;
import com.mybatis.poji.User;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParameterTest {
    @Test
    public void testGetUserByUsername(){
        //获取sql会话对象sqlsession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        //通过接口类创建接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //获取查询对象
        User user = mapper.getUserByUsername("ddd");
        System.out.println(user);
    }
    @Test
    public void testCheckLogin(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.checkLogin("aaa","111");
        System.out.println(user);
    }
    @Test
    public void testCheckLoginByMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username","ddd"); //手动设置键值对
        map.put("password","123");
        User user = mapper.checkLoginByMap(map);
        System.out.println(user);
    }
    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(3,"111","222","zzq","1","2","3",23.4,1);
        mapper.insertUser(user);
    }

    @Test
    public void testCheckLoginByParam(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.checkLoginByParam("ddd","123");
        System.out.println(user);
    }

}
