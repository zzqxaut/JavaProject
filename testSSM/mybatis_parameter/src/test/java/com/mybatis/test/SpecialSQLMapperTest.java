package com.mybatis.test;

import com.mybatis.mapper.SpecialSQLMapper;
import com.mybatis.poji.User;
import com.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class SpecialSQLMapperTest {


    @Test
    public void testgetUserByLike(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> list = mapper.getUserByLike("d");
        list.forEach(System.out::println);
    }
    @Test
    public void deleteMoreUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        mapper.deleteMoreUser("9,10");
    }
    @Test
    public void testGetUserList(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> list = mapper.getUserList("user");
        list.forEach(System.out::println);
    }
    @Test
    public void testJDBC(){
        try{
            Class.forName("");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database","root","111111");
//            String sql = "select * from user where username like '%%' ";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,"a");
            //通过JDBC获取自增主键
            String sql = "insert into user values()";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();//获取自增主键（是一个结果集，但是只有一个数据）
            resultSet.next();
            int id = resultSet.getInt(1);
            System.out.println(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertUser(){  //
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        User user = new User(null,"111","222","zzq","1","2","3",23.4,1);
        mapper.insertUser(user);
        System.out.println(user);
    }
}
