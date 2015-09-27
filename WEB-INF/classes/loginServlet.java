import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class loginServlet extends HttpServlet
{
	domaildatabase dm=new domaildatabase();
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
    {
    	
    	response.setContentType("text/html");
    	HttpSession session=request.getSession(true);
        PrintWriter out = response.getWriter();
        String str="",str1="",str2="";
        int str3=0;
    	if((String)session.getAttribute("uname")==null)
    	{
    	String email,password;
        email=request.getParameter("txtemail");
        password=request.getParameter("txtpsd");
        try{
       	ResultSet rs,rs1;
		rs=dm.singlerow("users",email,password);
		if(rs.next())
		{
				Integer c=(Integer)session.getAttribute("count");
				if(c==null)
				{
				c=new Integer(1);
				}
				else
				{
				c=new Integer(c.intValue()+ 1);
				}
				session.setAttribute("count", c);
				 str=rs.getString("fname");
				 str1=rs.getString("uname");
             str2=rs.getString("lname");
            session.setAttribute("fname",str);
            session.setAttribute("uname",str1);
            session.setAttribute("lname",str2);
            str3=rs.getInt("u_id");
            session.setAttribute("uniqueid",str3);
            out.println("<!doctype html><html><head><meta charset='utf-8'><title>Mail Detail</title>");
			out.println("<link rel='stylesheet' type='text/css' href='css/login.css'>");
			out.println("<script src='js/login.js' language='javascript'></script>");
			out.println("</head>");
			out.println("<body style='background:linear-gradient(to left,rgba(100,50,20,1),rgba(100,50,20,0),rgba(100,50,20,1));'>");
			out.println("<header>");
            out.println("<div id='logout' onMouseOver='show();' onMouseOut='hide();'>");
            out.println("<span style='vertical-align:top; color:#CC6600; font-size:20px;'>");
            out.println(str +"&nbsp;&nbsp;&nbsp;&nbsp; </span>");
            out.println("<img src='Images/male.jpg' width='50px' height='50px'  style='border-radius:25px;'/>");
            out.println("</div>");
            out.println("<div id='popup' class='hoverout' onMouseOver='show();' onMouseOut='hide();'>");
            out.println("<div class='Image'>");
            out.println("<img src='Images/male1.jpg' width='80px' height='90px' />");
            out.println("</div>");
            out.println("<div class='Name'>");
            out.println("<span style='font-size:20px;'>");
            out.println(str+ " "+str2 +"</span><br>"+str1+"<br><br>");
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
        out.println("<input type='submit' value='Forward' name='forward_btn' onClick='setid(this)'>");
        out.println("<input type='hidden' name='msg' value='inbox'>");
        out.println("</nav>");
         out.println("<div>");
         out.println("<table cellpadding='0px' height='20px' width='1050px' cellspacing='0px' border='1px' bordercolor='white' id='msg_table'>");
         out.println("<tr><th style='width:20px; display:none;'>Serial No</th><th style='width:40px;'>&nbsp;</th><th style='font-size:20px; color:red;'>Subject</th> <th style='font-size:20px; color:red;'>Content</th></tr>");
         	rs1=dm.select("inbox");
				while(rs1.next())
				{
					if(rs1.getString("tomail").equals(email))
					{
						out.println("<tr><td style='display:none;'>"+rs1.getInt("serialno")+"</td><td><input type='checkbox' style='margin:10px;' name='checkbox_state' value="+rs1.getInt("serialno")+"></td><td><a href='OpenMailServlet?mailmessages=inbox&openmail="+rs1.getInt("serialno")+"' style='text-decoration:none;'>"+rs1.getString("subject")+"</a></td><td>"+rs1.getString("msg")+"</td> </tr>");
					}
				}
            out.println("</table>");
         out.println("</div>");
         out.println("</form>");
		out.println("</body>");
		out.println("</html>");
				}
		else
		{
		
			out.println("<!doctype html><html><head><meta charset='utf-8'><title>Index Page</title><link rel='stylesheet' href='css/index.css'></head><body style='background:linear-gradient(to left,rgba(100,50,20,1),rgba(100,50,20,0),rgba(100,50,20,1));'>");
			out.println("<h1 style='text-align:center; color:red;'>Invalid Email or Pasword</h1>");
			out.println("<div style='height:500px; margin-left:500px; margin-top:50px; border:2px solid black; width:300px; background:white;'>    <img src='Images/domail.PNG' width='280px' height='90px' style='margin:10px;' />     <form name='loginform' action='LoginServlet' method='get'>");	
			out.println("<div align='center'><table border='0' cellpadding='10' cellspacing='5px' width='400'>");
           out.println("<tr> <td> <input type='email' id='email' size='30' name='txtemail' style='padding:5px;' placeholder='Email' class='login' /> </td></tr>");
      		out.println(" <tr> <td> <input type='password' id='pwd' size='30' name='txtpsd' style='padding:5px;' placeholder='Password' class='login' /> </td></tr>");
           out.println("<tr><td><div style='margin:0px;'><input type='checkbox' name='ch1' value='chk1'> Keep me signed in</div></td></tr>");
           out.println("<tr> <td><div> <input type='submit' class='buttons'  value='Login' name='loginbtn' style='background-color:#00F;'/> </div></td> </tr></form>");
           out.println("<tr><td><div style='margin-left:5px;'>I cannot access my account.</div></td></tr>");    
           out.println("<tr><td><div style='margin-left:5px;'>Help</div></td></tr>");
          out.println("<tr><td><form name='loginform' action='signupServlet' method='get'><div>"); 
             out.println("<input type='submit' class='buttons' value='Create New Account' name='newaccountbtn'  style='background-color:#660099;' /> </div></td></form>"); 
          out.println("</tr></table></div></div></body></html>");
		}
		
        }
        catch(Exception e)
        {
        	System.out.println("Error");
        }
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
        out.println("<input type='submit' value='Forward' name='forward_btn' onClick='setid(this)'>");
        out.println("<input type='hidden' name='msg' value='inbox'>");
        out.println("</nav>");
         out.println("<div>");
         out.println("<table cellpadding='0px' height='20px' width='1050px' cellspacing='0px' border='1px' bordercolor='white' id='msg_table'>");
         out.println("<tr><th style='width:20px; display:none;'>Serial No</th><th style='width:40px;'>&nbsp;</th><th style='font-size:20px; color:red;'>Subject</th> <th style='font-size:20px; color:red;'>Content</th></tr>");
         	try{
         	rs1=dm.select("inbox");
				while(rs1.next())
				{
					if(rs1.getString("tomail").equals(temp1))
					{
						out.println("<tr><td style='display:none;'>"+rs1.getInt("serialno")+"</td><td><input type='checkbox' style='margin:10px;' name='checkbox_state' value="+rs1.getInt("serialno")+"></td><td><a href='OpenMailServlet?mailmessages=inbox&openmail="+rs1.getInt("serialno")+"' style='text-decoration:none;'>"+rs1.getString("subject")+"</a></td><td>"+rs1.getString("msg")+"</td> </tr>");
					}
				}}
				catch(Exception e)
				{
					System.out.println("Error");
				}
					
            out.println("</table>");
         out.println("</div>");
         out.println("</form>");
		out.println("</body>");
		out.println("</html>");}
    }

}
