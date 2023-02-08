package servlet;

import com.info.Bookinfo;
import jdbc.JDBC;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "FindBookServlet", value = "/FindBookServlet")
public class FindBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {

            String bookname = request.getParameter("seekBookname");
            ArrayList<Bookinfo> books = JDBC.searchBook(bookname);

            JSONArray jsonArray = JSONArray.fromObject(books);

            out.print(jsonArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
