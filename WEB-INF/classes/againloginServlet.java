import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class againloginServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        response.sendRedirect("index.jsp");
        out.close();
    }

}
