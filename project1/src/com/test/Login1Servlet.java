package com.test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "Login1Servlet", value = "/Login1Servlet")
public class Login1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection conn = JDBC.connection();
            String sname = request.getParameter("sname");

            String sql="";
            if(sname == null){ //登陆显示数据库所有数据
                // 定义sql语句
                sql += "select * from user";
//                System.out.println(111);
            }
            else{ //sql语句为查找结果
//                System.out.println(sname);
                sql += "select * from user where username = \'" + sname +"\'"; //注意要拼接上反斜杠，否则错误
//                System.out.println(sql);
            }

            // 获取Statement对象
            //Statement stmt = conn.createStatement();
            Statement ps = conn.createStatement();
            // 执行sql
            ResultSet rs = ps.executeQuery(sql);

            ArrayList<User> list = new ArrayList<>();

            // 处理结果
            while (rs.next()){
                User user = new User();
                user.setUsertype(rs.getString("usertype"));
                user.setWorkplace(rs.getString("workplace"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhonenumber(rs.getString("phonenumber"));
                user.setTruename(rs.getString("truename"));
                user.setUsername(rs.getString("username"));

                list.add(user);
            }

            // 将信息放置到request之中
            request.setAttribute("list", list);
            // 释放资源

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 请求转发到Users.jsp
        request.getRequestDispatcher("manage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
