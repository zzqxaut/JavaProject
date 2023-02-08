package servlet;

import jdbc.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FindPasswordServlet", value = "/FindPasswordServlet")
public class FindPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String phonenumber = request.getParameter("phonenumber");
        String checkCode = request.getParameter("checkCode").toLowerCase();

        PrintWriter out = response.getWriter();

        try {
            String checknumber = ((String)session.getAttribute("checknumber")).toLowerCase();
            int flag = JDBC.find_pasword(username, phonenumber);
            if (flag == 1 && checknumber.equals(checkCode)){
                out.println(1);
            }else if (!checknumber.equals(checkCode)){
                out.println(-1);
            }else {
                out.println(0);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
