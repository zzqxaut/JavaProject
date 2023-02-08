package servlet;

import com.info.Bookno;
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

@WebServlet(name = "FindBookNoServlet", value = "/FindBookNoServlet")
public class FindBookNoServlet extends HttpServlet {
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

            ArrayList<Bookno> books =  JDBC.searchBookno(bookname);

            JSONArray jsonArray = JSONArray.fromObject(books);

            out.print(jsonArray);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
