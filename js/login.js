// JavaScript Document
var btnid="";
function show()
{
	 var d=document.getElementById("popup");
	 d.className = "hover";
 }
 function hide()
{
	 var d=document.getElementById("popup");
	 d.className = "hoverout";
	
}
function setid(btnsubmit){
	btnid=btnsubmit.value;
}
function showrow()
{
var count=0;
	var flag=false;
	var table=document.getElementById("msg_table");
	var rowcount=table.rows.length;
	var checkbox=document.getElementsByName('checkbox_state');
	if(rowcount>1)
	{
		if(btnid == "Delete All")
	{
		return true;
	}
	if(btnid == "Restore All"){
		return true;
	}
	if(btnid == "Forward"){
		
		for(var i=0;i<checkbox.length;i++)
		{
			
			var checkfrd=checkbox[i].checked;
		if(checkfrd==false)
		{
			continue;
		}
		else
		{
		
			count++;
		}
		
		}
		
			if(count==0)
			{
				alert("plz select a checkbox");
				return false;
			}
			else
			{
				if(count==1)
					{
						return true;}
						else
						{
					alert("plz select only one msg to forward");
					return false;}
					}
	}
	for(var i=0;i<checkbox.length;i++)
	{
		var check=checkbox[i].checked;
		if(check==false)
		{
		continue;
		}
		else
		{
		flag=true;
		break;
		}		
	}
	if(flag==false)
	{
		alert('plz select a checkbox');
		return false;
	}
	return true;	
	}
	else
		{
			alert("there are no msgs in this folder");
		return false;}
}