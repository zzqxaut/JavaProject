package servlet;

import com.info.Bookinfo;
import com.info.Bookno;
import com.info.BorrowBook;
import com.info.Userinfo;
import jdbc.JDBC;
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
import java.util.regex.Pattern;

@WebServlet(name = "FindReturnBookServlet", value = "/FindReturnBookServlet")
public class FindReturnBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            Connection conn = JDBC.getConnection();
            String bookno = request.getParameter("bookno");
            String username = request.getParameter("username");
            String bookname = request.getParameter("bookname");

            Userinfo user = JDBC.searchUser(username);
            int row = 0;
            if (user.getBalance() > 0) {
                row = JDBC.insertReturnRecord(bookno, username, bookname);
            }

            out.print(row);
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            String username = request.getParameter("username");
            String bookname = request.getParameter("seekBookname");
            bookname = bookname.toLowerCase();

            ArrayList<BorrowBook> books = new ArrayList<>();
            String reg = "^[\u0391-\uFFE5a-z0-9A-Z\\.\s]*"+bookname+"[\u0391-\uFFE5a-z0-9A-Z\\.\s]*$";
            for (BorrowBook book : JDBC.searchRecord(username)){
                if (Pattern.matches(reg, book.getBookname().toLowerCase())){
                    books.add(book);
                }
            }


            JSONArray jsonArray = JSONArray.fromObject(books);

            out.print(jsonArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
