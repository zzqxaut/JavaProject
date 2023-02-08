package com.test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println(666);
        //获取登陆填写的账号及密码
        String username1 = request.getParameter("username");
        String password1 = request.getParameter("password");
        //保存session
        HttpSession session = request.getSession(false);
        session.setAttribute("username", username1);
//        session.setAttribute("password", password1);
        Cookie cookie = new Cookie("usernameCookie",username1+"#"+password1 );
        cookie.setMaxAge(60 * 60 * 24 * 30); //设置cookie有效期30天
        response.addCookie(cookie); //保存cookie

        try {
            // 加载数据库驱动，注册到驱动管理器
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 数据库连接字符串
            String url = "jdbc:mysql://localhost:3306/dbmis";//dbmis是数据库名称
            String u = "root";
            String p = "111111";
            // 创建Connection连接
            Connection conn = DriverManager.getConnection(url,u,p);
            // 更新SQL语句
            String sql = "select * from user where username = ? and password = ?";
            // 获取PreparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            //位数据库擦寻语句设置参数
            ps.setString(1, username1);
            ps.setString(2, password1);

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();

            if(ps.executeQuery().next())//若查找非空
            {

                out.println("<HTML>");
                out.println(" <HEAD>");
                out.println("<TITLE>登陆成功！</TITLE>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://jwgl.xaut.edu.cn/jsxsd/assets_sddx/css/xalgLogin.css\">\n");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\"/>");
                out.println("</HEAD>");
                out.println("<BODY>");
                out.println("<h1>密码正确，登陆成功！</h1> ");
                out.println("</BODY>");
                out.println("</HTML>");
                response.setHeader("refresh","2;URL=http://localhost:8082/_war_exploded/Login1Servlet");  //延迟2秒
//                request.getRequestDispatcher("Login1Servlet").forward(request,response);
            }
            else
            {
                out.println("<HTML>");
                out.println(" <HEAD>");
                out.println("<TITLE>登陆失败！</TITLE>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://jwgl.xaut.edu.cn/jsxsd/assets_sddx/css/xalgLogin.css\">\n");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\"/>");
                out.println("<h1>账户名或密码输入错误！</h1>");
                out.println(" </BODY>");
                out.println("</HTML>");

                response.setHeader("refresh","2;URL=http://localhost:8082/_war_exploded/index.jsp");  //延迟2秒
            }

            out.flush();
            out.close();// 关闭
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
