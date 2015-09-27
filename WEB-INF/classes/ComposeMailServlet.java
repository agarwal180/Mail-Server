import java.io.*;
import javax.servlet.*;
import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;

public class ComposeMailServlet extends HttpServlet
{
	domaildatabase dm=new domaildatabase();
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
		String send=request.getParameter("btn_send");
		String save=request.getParameter("btn_save");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String datetime=dateFormat.format(date).toString();
		String to,sub,msg;
        to=request.getParameter("txt_to");
        sub=request.getParameter("txt_subject");
        msg=request.getParameter("txt_content");
		int id=(Integer)session.getAttribute("uniqueid");
		String from=(String)session.getAttribute("uname");
		ResultSet rs,rs2,rs3,rs4;
		int x,y;
		if(send!=null)
		{
		if(send.equals("Send"))
		{
			try{
			rs3=dm.selectuser("users",to);
			if(rs3.next())
			{
				rs=dm.select("inbox");
			if(rs.next())
			{
				rs.last();
				x=rs.getInt("serialno");
				x=x+1;
			}
			else{
				x=1;
			}
			rs2=dm.select("sent");
			if(rs2.next())
			{
				rs2.last();
				y=rs2.getInt("serialno");
				y=y+1;
			}
			else{
				y=1;
			}
			dm.insertmsg("inbox",id,x,from,to,sub,msg,datetime);
			dm.insertmsg("sent",id,y,from,to,sub,msg,datetime);
			}
			else
			{
				rs4=dm.select("outbox");
			if(rs4.next())
			{
				rs4.last();
				y=rs4.getInt("serialno");
				y=y+1;
			}
			else{
				y=1;
			}
			dm.insertmsg("outbox",id,y,from,to,sub,msg,datetime);}}
		catch(Exception e)
		{
			System.out.println("Error");
		}}}
		if(save!=null)
		{
			if(save.equals("Save"))
			{
				try{
					rs=dm.select("draft");
			if(rs.next())
			{
				rs.last();
				x=rs.getInt("serialno");
				x=x+1;
			}
			else{
				x=1;
			}
			dm.insertmsg("draft",id,x,from,to,sub,msg,datetime);
			}
			catch(Exception e)
			{
				System.out.println("Error");
			}
				
			}
		}
		response.sendRedirect("msgsServlet?param=inbox");}
    }
}
