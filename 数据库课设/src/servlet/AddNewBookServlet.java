package servlet;

import com.info.Bookinfo;
import jdbc.JDBC;
import net.sf.json.JSON;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "AddNewBookServlet", value = "/AddNewBookServlet")
public class AddNewBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            String bookname = request.getParameter("bookname");
            String auther = request.getParameter("auther");
            String booktype = request.getParameter("booktype");
            String publisher = request.getParameter("publisher");
            int booknum = Integer.parseInt(request.getParameter("booknum"));
            double price = Double.parseDouble(request.getParameter("price"));

            Bookinfo book = new Bookinfo(bookname, auther, booktype, publisher, booknum, price, 1);

            String str = "\"书名：" + book.getBookname() + "，作者：" + book.getAuther() + '\"';
            int row = JDBC.insertNewBook(book);

            // 1成功，3书籍类型不同，4出版社不同，6价格不同
            switch (row){
                case 1: break;
                case 3:
                    str += "的书籍类型与已添加的此类书不同\n";
                    break;
                case 4:
                    str += "的出版社与已添加的此类书不同\n";
                    break;
                case 6:
                    str += "的价格与已添加的此类书不同\n";
            }

            if (row != 1) {
                out.print(str);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
