package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.info.Userinfo;
import jdbc.JDBC;
import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "UserinfoServlet", value = "/UserinfoServlet")
public class UserinfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        try {
            ArrayList<Userinfo> list = new ArrayList<>();
            Userinfo user = JDBC.searchUser(username);
            list.add(user);
            JSONArray jsonarray = JSONArray.fromObject(list);
            response.getWriter().println(jsonarray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
