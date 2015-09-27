import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class DeleteRowServlet extends HttpServlet
{
	domaildatabase dm=new domaildatabase();
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int z;
        HttpSession session=request.getSession(false);
        if((String)session.getAttribute("uname")==null)
        	{
        		response.sendRedirect("index.jsp");
        	}
        	else
        	{
        String temp1=(String)session.getAttribute("uname");
        int y=(Integer)session.getAttribute("uniqueid");
        String delete=" ";
        	delete=request.getParameter("delete_btn");
        String deleteall=" ";
        deleteall=request.getParameter("deleteall_btn");
        String restore="";
        restore=request.getParameter("restore_btn");
        String restoreall=" ";
        restoreall=request.getParameter("restoreall_btn");
        String forward=" ";
        forward=request.getParameter("forward_btn");
        ResultSet rs,rs1;
        if(delete!=null)
        {
        if(delete.equals("Delete"))
        {
        	
        	String msg=request.getParameter("msg");
        if(msg.equals("inbox"))
        {
        	System.out.println("wow");
       	String arr[]=request.getParameterValues("checkbox_state");
       	for(int i=0;i<arr.length;i++)
       	{
       		int no=Integer.parseInt(arr[i]);
       		try{
       			rs=dm.single("inbox",no);
       			rs1=dm.select("trash");
			if(rs1.next())
			{
				rs1.last();
				z=rs1.getInt("serialno");
				z=z+1;
			}
			else{
				z=1;
			}
       			if(rs.next())
       			{
       				dm.trashinsert("trash",rs.getInt("u_id"),z,rs.getString("frommail"),rs.getString("tomail"),rs.getString("subject"),rs.getString("msg"),rs.getString("date"),"inbox");
       			}
       			dm.deletesinglerow("inbox",no);
       		
       		}
       		catch(Exception e)
       		{
       			System.out.println(e.getMessage());
       		}
       	}
       	response.sendRedirect("msgsServlet?param=inbox");
       }
        if(msg.equals("outbox"))
        {
       	String arr[]=request.getParameterValues("checkbox_state");
       	for(int i=0;i<arr.length;i++)
       	{
       		int no=Integer.parseInt(arr[i]);
       		try{
       		dm.deletesinglerow("outbox",no);
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       	}
       	response.sendRedirect("msgsServlet?param=outbox");
       }
        if(msg.equals("sent"))
        {
       	String arr[]=request.getParameterValues("checkbox_state");
       	for(int i=0;i<arr.length;i++)
       	{
       		int no=Integer.parseInt(arr[i]);
       		try{
       			
       		dm.deletesinglerow("sent",no);
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       	}
       	response.sendRedirect("msgsServlet?param=sent");
       }
       
        if(msg.equals("draft"))
        {
       	String arr[]=request.getParameterValues("checkbox_state");
       	for(int i=0;i<arr.length;i++)
       	{
       		int no=Integer.parseInt(arr[i]);
       		try{
       			
       		dm.deletesinglerow("draft",no);
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       	}
       	response.sendRedirect("msgsServlet?param=draft");
       }
       if(msg.equals("trash"))
        {
       	String arr[]=request.getParameterValues("checkbox_state");
       	for(int i=0;i<arr.length;i++)
       	{
       		int no=Integer.parseInt(arr[i]);
       		try{
       		dm.deletesinglerow("trash",no);
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       	}
       	response.sendRedirect("msgsServlet?param=trash");
       }
       }}
       if(deleteall!=null)
        {

       if(deleteall.equals("Delete All"))
        {
        	String msg=request.getParameter("msg");
        	if(msg.equals("inbox"))
        	{
        		try{
        			System.out.println(temp1);
       			rs=dm.getrows("inbox",temp1);
       			rs1=dm.select("trash");
			if(rs1.next())
			{
				rs1.last();
				z=rs1.getInt("serialno");
				z=z+1;
			}
			else{
				z=1;
			}
			System.out.println("value of z"+z);
       			while(rs.next())
       			{
       				System.out.println("Delete All");
       				dm.trashinsert("trash",rs.getInt("u_id"),z,rs.getString("frommail"),rs.getString("tomail"),rs.getString("subject"),rs.getString("msg"),rs.getString("date"),"inbox");
       				z=z+1;
       			}
       			dm.deleteto("inbox",temp1);
       		}
       		catch(Exception e)
       		{
       			System.out.println(e.getMessage());
       		}
       		response.sendRedirect("msgsServlet?param=inbox");
        	}
        	if(msg.equals("outbox"))
        	{
        		try{
        		dm.deletefrom("outbox",temp1);
       		
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       		response.sendRedirect("msgsServlet?param=outbox");
        	}
        	if(msg.equals("sent"))
        	{
        				try{
        				dm.deletefrom("sent",temp1);
       		
       				}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       		response.sendRedirect("msgsServlet?param=sent");
        	}
        	if(msg.equals("draft"))
        	{
        		try{
        		dm.deletefrom("draft",temp1);
       		
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       		response.sendRedirect("msgsServlet?param=draft");
        	}
        	if(msg.equals("trash"))
        	{
        		try{
        		dm.deleteto("trash",temp1);
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       		response.sendRedirect("msgsServlet?param=trash");
        	}
        }}
        if(restore!=null)
        {

        if(restore.equals("Restore"))
        {
        	String msg=request.getParameter("msg");
        	if(msg.equals("trash"))
        	{
        		String arr[]=request.getParameterValues("checkbox_state");
       	for(int i=0;i<arr.length;i++)
       	{
       		int no=Integer.parseInt(arr[i]);
       		try{
       			rs=dm.single("trash",no);
       			rs1=dm.select("inbox");
			if(rs1.next())
			{
				rs1.last();
				z=rs1.getInt("serialno");
				z=z+1;
			}
			else{
				z=1;
			}
       			if(rs.next())
       			{
       			
       				dm.insertmsg("inbox",rs.getInt("u_id"),z,rs.getString("frommail"),rs.getString("tomail"),rs.getString("subject"),rs.getString("msg"),rs.getString("date"));
       			}
       			dm.deletesinglerow("trash",no);
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       	}
       	response.sendRedirect("msgsServlet?param=trash");
        	}
        }
        }
        if(restoreall!=null)
        {
        if(restoreall.equals("Restore All"))
        {
        	String msg=request.getParameter("msg");
        	if(msg.equals("trash"))
        	{
        		try{
       			rs=dm.getrows("trash",temp1);
       			rs1=dm.select("inbox");
			if(rs1.next())
			{
				rs1.last();
				z=rs1.getInt("serialno");
				z=z+1;
			}
			else{
				z=1;
			}
       			while(rs.next())
       			{
       			
       				dm.insertmsg("inbox",rs.getInt("u_id"),z,rs.getString("frommail"),rs.getString("tomail"),rs.getString("subject"),rs.getString("msg"),rs.getString("date"));
       				z=z+1;
       			}
       			dm.deleteto("trash",temp1);
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
        	}
        	response.sendRedirect("msgsServlet?param=trash");
        }
        }
        if(forward!=null)
        {
        if(forward.equals("Forward"))
        {
        	String arr[]=request.getParameterValues("checkbox_state");
        String msg=request.getParameter("msg");
        	out.println("<!doctype html><html><head><meta charset='utf-8'><title>forward mail</title><link rel='stylesheet' href='css/compose.css'></head>");
			out.println("<body style='background:linear-gradient(to left,rgba(100,50,20,1),rgba(100,50,20,0),rgba(100,50,20,1));'>");
     		out.println("<div align='center'>");
     		out.println("<form action='sendsaveServlet' method='get'>");
        	out.println("<table border='0' cellpadding='5px' cellspacing='20px'><tr><td width='60px' height='30px'> To </td><td> : </td>");
            out.println("<td> <input type='email' id='to' autofocus size='50' name='txt_to' style='height:30px;' /></td> </tr>");
            out.println("<tr><td width='60px' height='30px'> Subject </td><td> : </td>");
            if(msg.equals("inbox"))
        	{
       		int no=Integer.parseInt(arr[0]);
       		try{
       			rs=dm.single("inbox",no);
       			if(rs.next())
       			{
       			out.println(" <td> <input type='text' id='sub' autofocus size='50' name='txt_subject' style='height:30px;' value='"+rs.getString("subject")+"'/></td></tr>");
       			out.println("<tr><td width='60px' height='30px'> Message </td><td> : </td>");
       			System.out.println("value is"+rs.getString("msg"));
              	out.println(" <td> <textarea id='msg' rows='10' cols='51' name='txt_content'>"+rs.getString("msg")+"</textarea></td></tr>");
       			}
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       		}
       		if(msg.equals("outbox"))
        	{
       		int no=Integer.parseInt(arr[0]);
       		try{
       			rs=dm.single("outbox",no);
       			if(rs.next())
       			{
       			out.println(" <td> <input type='text' id='sub' autofocus size='50' name='txt_subject' style='height:30px;' value='"+rs.getString("subject")+"'/></td></tr>");
       			out.println("<tr><td width='60px' height='30px'> Message </td><td> : </td>");
              	out.println(" <td> <textarea id='msg' rows='10' cols='51' name='txt_content'>"+rs.getString("msg")+"</textarea></td></tr>");
       			}
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       		}
       		if(msg.equals("sent"))
        	{
       		int no=Integer.parseInt(arr[0]);
       		try{
       			rs=dm.single("sent",no);
       			if(rs.next())
       			{
       			out.println(" <td> <input type='text' id='sub' autofocus size='50' name='txt_subject' style='height:30px;' value='"+rs.getString("subject")+"'/></td></tr>");
       			out.println("<tr><td width='60px' height='30px'> Message </td><td> : </td>");
             	out.println(" <td> <textarea id='msg' rows='10' cols='51' name='txt_content'>"+rs.getString("msg")+"</textarea></td></tr>");
       			}
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       		}
       		if(msg.equals("draft"))
        	{
       		int no=Integer.parseInt(arr[0]);
       		try{
       			rs=dm.single("draft",no);
       			if(rs.next())
       			{
       			out.println(" <td> <input type='text' id='sub' autofocus size='50' name='txt_subject' style='height:30px;' value='"+rs.getString("subject")+"'/></td></tr>");
       			out.println("<tr><td width='60px' height='30px'> Message </td><td> : </td>");
              	out.println(" <td> <textarea id='msg' rows='10' cols='51' name='txt_content'>"+rs.getString("msg")+"</textarea></td></tr>");
       			}
       		}
       		catch(Exception e)
       		{
       			System.out.println("Error");
       		}
       		}
            out.println("<tr><td colspan='3' align='center'><br/><br/>");
           out.println("<input type='submit' class='buttons' id='send' name='btn_send' value='Send' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
           out.println("<input type='submit' class='buttons' id='save' name='btn_save' value='Save' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
           out.println("<input type='reset' class='buttons' id='clear' value='Clear'/></td></tr> </table></form>  </div></body></html>");
        }}
    }
    }
}
