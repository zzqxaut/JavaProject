package com.test.mybatis.mapper;

import com.test.mybatis.pojo.User;

import java.util.ArrayList;
import java.util.List;

//对应User类的Mapper接口,注意命名
public interface UserMapper {
    int insertUser();
    void updateUser();
//    删除用户信息
    void deleteUser();
    User getUserByUsername();//根据id查询用户信息
    List<User> getAllUser();
}
