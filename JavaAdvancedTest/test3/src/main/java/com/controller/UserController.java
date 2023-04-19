package com.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.User;
import com.service.UserService;
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/select")
    public String select(User user, Model model) {
        List<User> list = userService.selectUserByUname(user);
        System.out.println("list:"+list);
        model.addAttribute("userList", list);
        return "userList";
    }
}