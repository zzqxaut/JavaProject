package com.test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username1 = request.getParameter("username");
        String usertype1 = request.getParameter("usertype");
        String password1 = request.getParameter("password");
        String truename1 = request.getParameter("truename");
        String phonenumber1 = request.getParameter("phonenumber");
        String email1 = request.getParameter("email");
        String workplace1 = request.getParameter("workplace");

//        PrintWriter out = response.getWriter();
        try {
            Connection conn = JDBC.connection();
            // 更新SQL语句
            String sql = "update user set password=?, truename=?, usertype=?, " +
                    "phonenumber=?, email=?, workplace=? where username=?";
            // 获取PreparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);

            // 对SQL语句中参数赋值
            ps.setString(1, password1);
            ps.setString(2, truename1);
            ps.setString(3, usertype1);
            ps.setString(4, phonenumber1);
            ps.setString(5, email1);
            ps.setString(6, workplace1);
            ps.setString(7, username1);
            ps.executeUpdate();

            // 关闭
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 重定向到FindServlet
        response.sendRedirect("Login1Servlet");
    }

}
