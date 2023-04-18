package com.mybatis.mapper;

import com.mybatis.poji.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper {
    /**
     * 查询结果为多条，不能以实体类作为返回值
     * 根据密码查询用户信息,返回值为多个，用list作为返回值，否则异常TooManyResultsException
     * @param password
     * @return
     */
    //
    List<User>  getUserByPassword(@Param("password") String password);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getAllUser();

    /**
     * 查询用户数量
     * @return
     */
    Integer getCount();

    /**
     * 根据id查询用户信息位map集合
     * @param id
     * @return
     */
    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);
}
