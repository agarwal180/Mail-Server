// JavaScript Document
function signup()
{
	var fname=document.getElementById("lstname").value;
	var alpha=/^[a-zA-Z]+$/;
	if(!fname.match(alpha))
	{
			alert("invalid First name");
			return false;}		
	var lname=document.getElementById("lstname1").value;
	if(!lname.match(alpha))
	{
			alert("invalid last name");
			return false;}
	var alphanum=/^[0-9a-zA-Z]+$/;
	var uname=document.getElementById("username").value;
	if(!uname.match(alphanum))
	{
		alert("invalid user name");
			return false;}
	if(uname.length>15)
	{
		alert("maximum length allowed 14");
			return false;}

	var password=document.getElementById("psd").value;
	if(password.length>13 || password.length<5)
	{
		alert("enter password between 6 to 12");
			return false;}
	if(!password.match(alphanum))
	{
		alert("invalid passowrd");
			return false;}
	var repassword=document.getElementById("repsd").value;
	if(repassword.length>13 || repassword.length<5)
	{
		alert("enter password between 6 to 12");
			return false;}
	if(!repassword.match(alphanum))
	{
		alert("invalid reenter-passowrd");
			return false;}
	if(!repassword.match(password))
	{
		alert("password not match");
			return false;}
	var backupemail=document.getElementById("backmail").value;
		var temp=/^[@.0-9a-zA-Z]+$/;
		if(!backupemail.match(temp))
	{
			alert("invalid backupemail name");
			return false;}		
		var month=document.getElementById("mnth").value;
		if(month.match("Month"))
		{
			alert("Selcet month");
			return false;}
		var day=document.getElementById("day").value;
		if(day.match("Day"))
		{
			alert("Selcet Day");
			return false;}
		var year=document.getElementById("yer").value;
		if(year.match("Year"))
		{
			alert("Selcet year");
			return false;}
		var m=document.getElementById("rad").checked;
		var f=document.getElementById("rad1").checked;
		if(!m && !f)
		{
			alert("Select a radio button");
			return false;}
		return true;		

}
	