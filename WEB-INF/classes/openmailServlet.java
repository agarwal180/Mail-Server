import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class openmailServlet extends HttpServlet
{
		domaildatabase dm=new domaildatabase();
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try
        {
        	HttpSession session=request.getSession(false);
        	if((String)session.getAttribute("uname")==null)
        	{
        		response.sendRedirect("index.jsp");
        	}
        	else
        	{
        	String temp=(String)session.getAttribute("fname");
        	String temp1=(String)session.getAttribute("uname");
        	String temp2=(String)session.getAttribute("lname");
        	ResultSet rs1;
        	out.println("<!doctype html><html><head><meta charset='utf-8'><title>Mail Detail</title>");
			out.println("<link rel='stylesheet' type='text/css' href='css/login.css'>");
			out.println("<script src='js/login.js' language='javascript'></script>");
			out.println("</head>");
			out.println("<body style='background:linear-gradient(to left,rgba(100,50,20,1),rgba(100,50,20,0),rgba(100,50,20,1));'>");
			out.println("<header>");
            out.println("<div id='logout' onMouseOver='show();' onMouseOut='hide();'>");
            out.println("<span style='vertical-align:top; color:#CC6600; font-size:20px;'>");
            out.println(temp +"&nbsp;&nbsp;&nbsp;&nbsp; </span>");
            out.println("<img src='Images/male.jpg' width='50px' height='50px'  style='border-radius:25px;'/>");
            out.println("</div>");
            out.println("<div id='popup' class='hoverout' onMouseOver='show();' onMouseOut='hide();'>");
            out.println("<div class='Image'>");
            out.println("<img src='Images/male1.jpg' width='80px' height='90px' />");
            out.println("</div>");
            out.println("<div class='Name'>");
            out.println("<span style='font-size:20px;'>");
            out.println(temp+ " "+temp2 +"</span><br>"+temp1+"<br><br>");
            out.println("<form action='signoutServlet' method='get'>");
            out.println("<input type='submit' value='Sign Out' style='margin-left:40px; margin-top:50px; font-size:18px; padding:5px;'/>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
			out.println("</header>");
            out.println("<br><hr>");
        	out.println("<div id='loginpage_leftbar'>");
        out.println("<div id='compose_button'>");
        out.println("<form action='ComposeServlet' method='get'>");
        out.println("<input type='submit' value='Compose' style='padding:10px 20px 10px 20px; background-color:#EC4D35; border-color:#EC4D35;'>");
        out.println("</form>");
        out.println("</div>");
        out.println("<div id='left_list'>");
        out.println("<ul><li><a href='msgsServlet?param=inbox'>Inbox</a></li><li><a href='msgsServlet?param=outbox'>Outbox</a></li><li><a href='msgsServlet?param=sent'>Sent</a></li><li><a href='msgsServlet?param=draft'>Draft</a></li><li><a href='msgsServlet?param=trash'>Trash</a></li></ul>");
        out.println("</div>");
        out.println("</div>");
        if(request.getParameter("mailmessages").equals("inbox"))
         {
         	//System.out.println("msg"+request.getParameter("mailmessages"));
         	int no=Integer.parseInt(request.getParameter("openmail"));
         	//System.out.println("value of no"+no);
         	rs1=dm.single("inbox",no);
         	if(rs1.next())
         	{
         		out.println("<br><br><span style='font-size:25px; padding:20px;'>Date :"+rs1.getString("date")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>From : "+rs1.getString("frommail")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>Subject: "+rs1.getString("subject")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>msg: "+rs1.getString("msg")+"</span>");
         	}
         }
         if(request.getParameter("mailmessages").equals("outbox"))
         {
         	//System.out.println("msg"+request.getParameter("mailmessages"));
         	int no=Integer.parseInt(request.getParameter("openmail"));
         	//System.out.println("value of no"+no);
         	rs1=dm.single("outbox",no);
         	if(rs1.next())
         	{
         		out.println("<br><br><span style='font-size:25px; padding:20px;'>Date :"+rs1.getString("date")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>To : "+rs1.getString("tomail")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>Subject: "+rs1.getString("subject")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>msg: "+rs1.getString("msg")+"</span>");
         	}
         }
         if(request.getParameter("mailmessages").equals("sent"))
         {
         	//System.out.println("msg"+request.getParameter("mailmessages"));
         	int no=Integer.parseInt(request.getParameter("openmail"));
         	//System.out.println("value of no"+no);
         	rs1=dm.single("sent",no);
         	if(rs1.next())
         	{
         		out.println("<br><br><span style='font-size:25px; padding:20px;'>Date :"+rs1.getString("date")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>To : "+rs1.getString("tomail")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>Subject: "+rs1.getString("subject")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>msg: "+rs1.getString("msg")+"</span>");
         	}
         }
         if(request.getParameter("mailmessages").equals("draft"))
         {
         	//System.out.println("msg"+request.getParameter("mailmessages"));
         	int no=Integer.parseInt(request.getParameter("openmail"));
         	//System.out.println("value of no"+no);
         	rs1=dm.single("draft",no);
         	if(rs1.next())
         	{
         		out.println("<br><br><span style='font-size:25px; padding:20px;'>Date :"+rs1.getString("date")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>To : "+rs1.getString("tomail")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>Subject: "+rs1.getString("subject")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>msg: "+rs1.getString("msg")+"</span>");
         	}
         }
         if(request.getParameter("mailmessages").equals("trash"))
         {
         	//System.out.println("msg"+request.getParameter("mailmessages"));
         	int no=Integer.parseInt(request.getParameter("openmail"));
         	//System.out.println("value of no"+no);
         	rs1=dm.single("trash",no);
         	if(rs1.next())
         	{
         		out.println("<br><br><span style='font-size:25px; padding:20px;'>Date :"+rs1.getString("date")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>From : "+rs1.getString("frommail")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>Subject: "+rs1.getString("subject")+"</span><br><br><br>");
        		out.println("<span style='font-size:25px; padding:20px;'>msg: "+rs1.getString("msg")+"</span>");
         	}
         }
         out.println("</body>");
		out.println("</html>");
        }}
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
    }

}
