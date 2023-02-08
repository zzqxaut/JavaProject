package com.example.db_course_design;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "findbook", value = "/findbook")
public class findbook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String bookname = (String)request.getAttribute("bookname");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        try {
            Connection conn = jdbc.getConnection();
            String bookname = request.getParameter("bookname");
            System.out.println(bookname);
            ArrayList<bookinfo> bookl = jdbc.search_book(bookname);

//            System.out.println("auther="+bookl.getAuther());
            ArrayList<bookno> booklist = (ArrayList<bookno>)jdbc.search_bookno(bookname);
            // 将信息放置到request之中
            request.setAttribute("book", bookl);
            request.setAttribute("booklist", booklist);
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 请求转发
        request.getRequestDispatcher("findbook.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
