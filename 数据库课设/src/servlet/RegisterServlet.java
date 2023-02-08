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

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
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
        String password = request.getParameter("password");
        String truename = request.getParameter("truename");
        String phonenumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String usertype = request.getParameter("usertype");
        String checkCode = request.getParameter("checkCode").toLowerCase();

        PrintWriter out = response.getWriter();
        try {
            String checknumber = ((String)session.getAttribute("checknumber")).toLowerCase();

            int flag = JDBC.check_user(username);
            if (!checkCode.equals(checknumber)){
                out.println(-1);
            }else if (flag == 0){
                out.println(0);
            }else {
                int rs = JDBC.insert_user(username, password, truename, usertype, email, phonenumber);
                if(rs == 1) {
                    out.println(1);
                }else {
                    System.out.println("注册失败！");
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
