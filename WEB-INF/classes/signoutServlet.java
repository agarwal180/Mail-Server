import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class signoutServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);
		if((String)session.getAttribute("uname")==null)
        	{
        		response.sendRedirect("index.jsp");
        	}
        	else
        	{
		session.invalidate();
        response.sendRedirect("index.jsp");
        out.close();}
    }

}
