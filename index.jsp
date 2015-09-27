<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Index Page</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body style="background:linear-gradient(to left,rgba(100,50,20,1),rgba(100,50,20,0),rgba(100,50,20,1));">
<%@ page session="true" %>
<%
				System.out.println(session);
				if((String)session.getAttribute("uname")!=null)
				{
				System.out.println((String)session.getAttribute("uname"));
				response.sendRedirect("LoginServlet");
				} 
				else
				System.out.println("Hello");
%>
<div style="height:500px; margin-left:500px; margin-top:60px; border:2px solid black; width:300px; background:white;">
    <img src="Images/domail.PNG" width="280ox" height="90px" style="margin:10px;" /> 
    <form name="loginform" action="LoginServlet" method="get">
	<div align="center">
		<table border="0" cellpadding="10" cellspacing="5px" width="400">
           <tr> 
              <td> <input type="email" id="email" size="30" name="txtemail" style="padding:5px;" 
         required     placeholder="Email" class="login" /> </td>          
           </tr>
           <tr> 
              <td> <input type="password" id="pwd" size="30" name="txtpsd" style="padding:5px;" 
              placeholder="Password" class="login" required /> </td>          
           </tr>
           <tr>
           <td><div style="margin:0px;"><input type="checkbox" name="ch1" value="chk1"> Keep me signed in</div></td>
           </tr>
           <tr> 
              <td><div> <input type="submit" class="buttons"  value="Login" name="loginbtn" style=" background-color:#00F;"/> </div></td>          
           </tr>
           </form>
           <tr>
           <td><div style="margin-left:5px;">I cannot access my account.</div></td>
           </tr>    
           <tr>
           <td><div style="margin-left:5px;">Help</div></td>
           </tr>
           <tr>
              <td><form name="loginform" action="signupServlet" method="get">
              <div> 
              <input type="submit" class="buttons" value="Create New Account" name="newaccountbtn"  style="background-color:#660099;" /> </div></td>
              </form> 
          </tr>
		</table>	
    </div>
    </div>
	</body>
</html>
