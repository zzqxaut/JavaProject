package servlet;

import com.info.Bookno;
import com.info.Userinfo;
import jdbc.JDBC;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "BorrowBookServlet", value = "/BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            String bookno = request.getParameter("bookno");
            String username = request.getParameter("username");
            String bookname = request.getParameter("bookname");

            Userinfo user = JDBC.searchUser(username);
            int row = 0;
            if (user.getBalance() > 0) {
                row = JDBC.insertBorrowRecord(bookno, username, bookname);
            }

            out.print(row);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
