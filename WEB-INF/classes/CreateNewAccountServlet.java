import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
public class CreateNewAccountServlet extends HttpServlet{  
public void doGet(HttpServletRequest request,HttpServletResponse response)  throws ServletException,IOException  
{  
response.setContentType("text/html");  
PrintWriter out=response.getWriter();  
  
response.sendRedirect("signup.html");  
  
out.close();  
}}  