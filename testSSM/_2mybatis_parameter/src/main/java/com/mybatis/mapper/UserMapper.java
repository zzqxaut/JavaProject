package com.mybatis.mapper;

import com.mybatis.poji.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

//          接口文件


public interface UserMapper {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 验证登录
     * @param username
     * @param password
     * @return
     */
    User checkLogin(String username,String password);

    /**
     * 验证登陆2
     * @param map
     * @return
     */
    User checkLoginByMap(Map<String,Object> map);

    /**
     * 添加用户信息
     * @param user
     */
    void insertUser(User user);

    /**
     *验证登录 使用@Param注解（命名参数）
     * @param username
     * @param password
     * @return
     */
    //等价于@Param(value = "username") ,默认value属性赋值
    User checkLoginByParam(@Param("username") String username, @Param("password") String password);
}
