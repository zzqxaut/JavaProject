package servlet;


import com.info.Informinfo;
import jdbc.JDBC;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "InformServlet", value = "/InformServlet")
public class InformServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        try {
            ArrayList<Informinfo> list = JDBC.showInform();
            JSONArray jsonarray = JSONArray.fromObject(list);
            response.getWriter().println(jsonarray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
