package servlet;

import com.info.Userinfo;
import jdbc.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AlertInformServlet", value = "/AlertInformServlet")
public class AlertInformServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            String username = request.getParameter("username");
            String truename = request.getParameter("truename");
            String password = request.getParameter("password");
            String phonenumber = request.getParameter("phonenumber");
            String email = request.getParameter("email");

            int row = JDBC.AlertUserInform(username, truename, password, phonenumber, email);

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
