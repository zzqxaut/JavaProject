package com.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("username");

        try {

            Connection conn = JDBC.connection();
            // 定义sql语句
            String sql = "delete from user where username=?";

            // 获取Statement对象
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();

            System.out.println(id);

            // 释放资源
            conn.close();
            ps.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("Login1Servlet");
    }
}
