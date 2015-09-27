import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

public class accountcreateServlet extends HttpServlet
{
	domaildatabase dm=new domaildatabase();
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fname,lname,uname,password,repassword,backupemail,birthmnth,birthdate,birthyear,dob,sexmale,sexfemale,sex="";
        fname=request.getParameter("txt_frst_name");
        lname=request.getParameter("txt_lst_name");
        uname=request.getParameter("txt_user_name");
        uname=uname+"@domail.com";
        password=request.getParameter("txtpsd");
        repassword=request.getParameter("txtrepsd");
        backupemail=request.getParameter("backup_email");
        birthmnth=request.getParameter("birth_mnth");
        birthdate=request.getParameter("birth_date");
        birthyear=request.getParameter("birth_yr");
        dob=birthyear+"-"+birthmnth+"-"+birthdate;
        sex=request.getParameter("sex");
        System.out.println(sex);
        //sexfemale=request.getParameter("sex_female");
        if(sex.equals("male"))
			{
				sex="Male";
			}
			else
			{	
				if(sex.equals("female"))
				{
					sex="Female";
				}
			}
        try{
        	int x;
        	ResultSet rs,rs1;
        	rs1=dm.selectuser("users",uname);
        	if(!(rs1.next()))
        	{
        		rs=dm.select("users");
			if(rs.next())
			{
				rs.last();
				x=rs.getInt("u_id");
				x=x+1;
			}
			else{
				x=1;
			}
					dm.insertuserinfo("users",x,fname,lname,uname,password,backupemail,dob,sex);
        
        out.println("<html>");
        out.println("<body>");
		out.println("<h1 style='align:center;'>");
		out.println("Hello! "+fname+" Your account has been Created");
		out.println("<br> <br>click here to");
		out.println("<a href='indexServlet' style='text-decoration:none;'>Login</a>");
		out.println("</h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
        }
        else
        {
        	out.println("<!doctype html><html><head><meta charset='utf-8'><title>SignUp Page</title><link rel='stylesheet' href='css/newaccount.css'><script src='js/signup.js'></script></head>");
			out.println("<body style='background:linear-gradient(to left,rgba(100,50,20,1),rgba(100,50,20,0),rgba(100,50,20,1));'>");
			out.println("<div id='sign'>Sign Up</div><h3 style='text-align:center;'>This account already exist plz create account with Different username</h3><div id='txt'>");
			out.println("<form action='NewAccountServlet' method='get' name='signup page' onSubmit='return signup()'>");
			out.println("<input type='text' autofocus placeholder='First name' name='txt_frst_name' id='lstname' required>");
            out.println("<input type='text' placeholder='Last name' name='txt_lst_name' id='lstname1' required><br>");
			out.println("<input type='text' placeholder='User name' name='txt_user_name' id='username' class='same' required><br>");
			out.println("<input type='password' placeholder='password' id='psd' name='txtpsd' class='same' required><br>");
			out.println("<input type='password' placeholder='Re-enter passowrd' name='txtrepsd' id='repsd' class='same' required><br>");
			out.println("<input type='email' placeholder='backupmail email' name='backup_email' id='backmail' class='same' required><br>");
			out.println("<span class='name'>Birthday</span><br>");
			out.println("<select  class='box' name='birth_mnth' id='mnth'><option>Month</option><option>Jan</option><option>Feb</option><option>Mar</option><option>Apr</option><option>May</option><option>Jun</option><option>July</option><option>Aug</option><option>Sep</option><option>Oct</option><option>Nov</option><option>Dec</option></select>");
			out.println("<select  class='box1' name='birth_date' id='day'><option>Day</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option><option>13</option><option>14</option><option>15</option><option>16</option><option>17</option><option>18</option><option>19</option><option>20</option><option>21</option><option>22</option><option>23</option><option>24</option><option>25</option><option>26</option><option>27</option><option>28</option><option>29</option><option>30</option><option>31</option></select>");
			out.println("<select  class='box1' name='birth_yr' id='yer'><option>Year</option><option>2014</option><option>2013</option><option>2012</option><option>2011</option><option>2010</option><option>2009</option><option>2008</option><option>2007</option><option>2006</option><option>2005</option><option>2004</option><option>2003</option><option>2002</option><option>2001</option><option>2000</option><option>1999</option><option>1998</option><option>1997</option><option>1996</option><option>1995</option><option>1994</option><option>1993</option><option>1992</option><option>1991</option><option>1990</option><option>1989</option><option>1988</option><option>1987</option><option>1986</option><option>1985</option></select><br>");
			out.println("<input type='radio' id='rad' name='sex' value='male'><span style='font:20px tahoma; margin-left:10px; margin-right:10px;'>Male</span>");
			out.println("<input type='radio' name='sex' value='female' id='rad1'><span style='font:20px tahoma; margin-left:10px;'>Female</span><br>");
			out.println("<input type='submit' value='Signup' name='Signupbtn' class='signupbutton'>");
			out.println("</form></div></body></html>");
			out.close();
        	
        }
        }
        catch(Exception e)
        {
        	System.out.println("Error");
        }
    }
}
