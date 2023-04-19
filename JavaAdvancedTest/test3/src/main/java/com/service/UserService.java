package com.service;

import java.util.List;
import com.po.User;
public interface UserService {
    public List<User> selectUserByUname(User user);
}