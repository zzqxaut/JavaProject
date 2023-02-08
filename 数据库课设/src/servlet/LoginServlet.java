package servlet;
import com.info.Userinfo;
import jdbc.JDBC;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

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
        String checkCode = request.getParameter("checkCode").toLowerCase();
        String usertype = request.getParameter("usertype");

        PrintWriter out = response.getWriter();

        try {
            String checkNumber = ((String)session.getAttribute("checknumber")).toLowerCase();
            Userinfo user = JDBC.searchUser(username);
            String truePassword = user.getPassword();
            String Usertype = user.getUsertype();
            String trueName = user.getTruename();

            session.setAttribute("truename", trueName);
            session.setAttribute("username", username);

            if (truePassword != null && truePassword.equals(password) && checkNumber.equals(checkCode) && usertype.equals(Usertype)) {
                if (usertype.equals("管理员")){
                    out.println(2);
                }else {
                    out.println(1);
                }
            } else if (!checkNumber.equals(checkCode)) {
                out.println(0);
            } else{
                if (username.equals(Usertype)){
                    out.println(-1);
                }else {
                    out.println(-2);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
