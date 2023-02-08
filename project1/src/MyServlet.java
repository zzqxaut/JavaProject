import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", value = "/MyServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println(" <HEAD>");
        out.println("<TITLE>登陆成功！</TITLE>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://jwgl.xaut.edu.cn/jsxsd/assets_sddx/css/xalgLogin.css\">\n");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\"/>");
        out.println("</HEAD>");
        out.println(" <BODY>");
        out.println(" <h1>登录成功</h1> ");
        //out.print(this.getClass());

        //out.println("<a href=\"manage.jsp\"><input type=\"button\" name=\"\" id=\"\" value=\"管理\" /></a></td>");
        out.println(" </BODY>");
        out.println("</HTML>");
        response.setHeader("refresh","2;URL=http://localhost:8082/_war_exploded/manage.jsp");  //延迟2秒
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
