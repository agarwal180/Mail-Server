import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class messageServlet extends HttpServlet
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
        out.println("<nav id='option_list'>");
        out.println("<form action='DeleteServlet' mehtod='get' onSubmit='return showrow()'>");
        out.println("<input type='submit' value='Delete' name='delete_btn'>");
        out.println("<input type='submit' value='Delete All' name='deleteall_btn' onClick='setid(this)'>");
         if(request.getParameter("param").equals("inbox"))
         {
         	out.println("<input type='submit' value='Forward' name='forward_btn' onClick='setid(this)'>");
        out.println("</nav>");
         out.println("<div> ");
         out.println("<table cellpadding='0px' height='20px' width='1050px' cellspacing='0px' border='1px' bordercolor='white' id='msg_table'>");
         out.println("<tr><th style='width:20px; display:none;'>Serial No</th><th style='width:40px;'>&nbsp;</th><th style='font-size:20px; color:red;'>Subject</th> <th style='font-size:20px; color:red;'>Content</th></tr>");
         	out.println("<input type='hidden' name='msg' value='inbox'>");
         	rs1=dm.select("inbox");
				while(rs1.next())
				{
					if(rs1.getString("tomail").equals(temp1))
					{
						out.println("<tr><td style='display:none;'>"+rs1.getInt("serialno")+"</td><td><input type='checkbox' style='margin:10px;' name='checkbox_state' value="+rs1.getInt("serialno")+"></td><td><a href='OpenMailServlet?mailmessages=inbox&openmail="+rs1.getInt("serialno")+"' style='text-decoration:none;'>"+rs1.getString("subject")+"</a></td><td>"+rs1.getString("msg")+"</td> </tr>");
					}
				}
         }
         if(request.getParameter("param").equals("outbox"))
         {
         	out.println("<input type='submit' value='Forward' name='forward_btn' onClick='setid(this)'>");
        out.println("</nav>");
         out.println("<div> ");
         out.println("<table cellpadding='0px' height='20px' width='1050px' cellspacing='0px' border='1px' bordercolor='white' id='msg_table'>");
         out.println("<tr><th style='width:20px; display:none;'>Serial No</th><th style='width:40px;'>&nbsp;</th><th style='font-size:20px; color:red;'>Subject</th> <th style='font-size:20px; color:red;'>Content</th></tr>");
         	out.println("<input type='hidden' name='msg' value='outbox'>");
         	rs1=dm.select("outbox");
				while(rs1.next())
				{
					if(rs1.getString("frommail").equals(temp1))
					{
						out.println("<tr><td style='display:none;'>"+rs1.getInt("serialno")+"</td><td><input type='checkbox' style='margin:10px;' name='checkbox_state' value="+rs1.getInt("serialno")+"></td><td><a href='OpenMailServlet?mailmessages=outbox&openmail="+rs1.getInt("serialno")+"' style='text-decoration:none;'>"+rs1.getString("subject")+"</a></td><td>"+rs1.getString("msg")+"</td> </a></tr>");					}
				}
         }
         if(request.getParameter("param").equals("sent"))
         {
         	out.println("<input type='submit' value='Forward' name='forward_btn' onClick='setid(this)'>");
        out.println("</nav>");
         out.println("<div> ");
         out.println("<table cellpadding='0px' height='20px' width='1050px' cellspacing='0px' border='1px' bordercolor='white' id='msg_table'>");
         out.println("<tr><th style='width:20px; display:none;'>Serial No</th><th style='width:40px;'>&nbsp;</th><th style='font-size:20px; color:red;'>Subject</th> <th style='font-size:20px; color:red;'>Content</th></tr>");
         	out.println("<input type='hidden' name='msg' value='sent'>");
         	rs1=dm.select("sent");
   			while(rs1.next())
				{
					if(rs1.getString("frommail").equals(temp1))
					{
						out.println("<tr><td style='display:none;'>"+rs1.getInt("serialno")+"</td><td><input type='checkbox' style='margin:10px;' name='checkbox_state' value="+rs1.getInt("serialno")+"></td><td><a href='OpenMailServlet?mailmessages=sent&openmail="+rs1.getInt("serialno")+"' style='text-decoration:none;'>"+rs1.getString("subject")+"</a></td><td>"+rs1.getString("msg")+"</td></tr>");
					}
				}
         }
         if(request.getParameter("param").equals("draft"))
         {
         	out.println("<input type='submit' value='Forward' name='forward_btn' onClick='setid(this)'>");
        out.println("</nav>");
         out.println("<div> ");
         out.println("<table cellpadding='0px' height='20px' width='1050px' cellspacing='0px' border='1px' bordercolor='white' id='msg_table'>");
         out.println("<tr><th style='width:20px; display:none;'>Serial No</th><th style='width:40px;'>&nbsp;</th><th style='font-size:20px; color:red;'>Subject</th> <th style='font-size:20px; color:red;'>Content</th></tr>");
         	out.println("<input type='hidden' name='msg' value='draft'>");
         	rs1=dm.select("draft");
				while(rs1.next())
				{
					if(rs1.getString("frommail").equals(temp1))
					{
						out.println("<tr><td style='display:none;'>"+rs1.getInt("serialno")+"</td><td><input type='checkbox' style='margin:10px;' name='checkbox_state' value="+rs1.getInt("serialno")+"></td><td><a href='OpenMailServlet?mailmessages=draft&openmail="+rs1.getInt("serialno")+"' style='text-decoration:none;'>"+rs1.getString("subject")+"</a></td><td>"+rs1.getString("msg")+"</td></tr>");
					}
				}
         }
         if(request.getParameter("param").equals("trash"))
         {
         	out.println("<input type='submit' value='Restore' name='restore_btn'>");
        out.println("<input type='submit' value='Restore All' name='restoreall_btn' onClick='setid(this)'>");
        out.println("</nav>");
         out.println("<div> ");
         out.println("<table cellpadding='0px' height='20px' width='1050px' cellspacing='0px' border='1px' bordercolor='white' id='msg_table'>");
         out.println("<tr><th style='width:20px; display:none;'>Serial No</th><th style='width:40px;'>&nbsp;</th><th style='font-size:20px; color:red;'>Subject</th> <th style='font-size:20px; color:red;'>Content</th></tr>");
         	out.println("<input type='hidden' name='msg' value='trash'>");
         	rs1=dm.select("trash");
				while(rs1.next())
				{
					if(rs1.getString("tomail").equals(temp1))
					{
						out.println("<tr><td style='display:none;'>"+rs1.getInt("serialno")+"</td><td><input type='checkbox' style='margin:10px;' name='checkbox_state' value="+rs1.getInt("serialno")+"></td><td><a href='OpenMailServlet?mailmessages=trash&openmail="+rs1.getInt("serialno")+"' style='text-decoration:none;'>"+rs1.getString("subject")+"</a></td><td>"+rs1.getString("msg")+"</td> </tr>");
					}
				}
         }
          out.println("</table>");
         out.println("</div>");
         out.println("</form>");
		out.println("</body>");
		out.println("</html>");}
        }
        catch(Exception e)
        {
        	System.out.println("Error");
        }
    }

}
