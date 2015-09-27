// JavaScript Document
function signUp(){
	   location.href="signup.html";
   }
	var src=new Array("Images/Images/img1.jpg","Images/Images/img2.jpg","Images/Images/img3.jpg","Images/Images/img4.jpg","Images/Images/img5.jpg","Images/Images/img6.jpg","Images/Images/img7.jpg","Images/Images/img8.jpg","Images/Images/img9.jpg");	
	var i=0;
	
	function slideshow()  
    {
		  var img=document.getElementById("slide_img");	    
	      if(i==src.length)
	         i=0;
		  img.src=src[i++];
		  //document.write(i++);
	}
	function changeimage()
	{
		var slide=window.setInterval("slideshow()",1500);
	}
