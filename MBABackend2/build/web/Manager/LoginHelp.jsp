<%-- 
    Document   : LoginHelp
    Created on : 16 Jun, 2011, 4:03:42 PM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>BEST MART - Best Quality | Price | Service</title>
    <link type="text/css" href="menushopping.css" rel="stylesheet" />
    <script type="text/javascript" src="menu.js"></script>
    <style type="text/css">
* { margin:0;
    padding:0;
}
body { background:#FFF; }
div#menu { margin:5px auto; }
div#copyright {
    font:11px 'Trebuchet MS';
    color:#fff;
    text-indent:30px;
    padding:40px 0 0 0;
}

#apDiv1 {
	position:absolute;
	width:200px;
	height:115px;
	z-index:10;
}
    a:link {
	text-decoration: none;
	color: #33C;
}
a:visited {
	text-decoration: none;
	color: #33C;
}
a:hover {
	text-decoration: none;
	color: #F00;
}
a:active {
	text-decoration: none;
	color: #33C;
}
</style>
</head>
<body>



<!-- Header Start -->
<div id="Header">

<table width="1003" align="center">
  <tr>
    <td><img src="images/Header.png" width="900" height="119" border="0" usemap="#Social"></td>
  </tr>
</table>

</div>
<!-- Header End -->

<!-- Menu Start -->
<div id="menu">
    <ul class="menu">
        <li><a href="/MBA2/Manager/Login.jsp"><span>Login</span></a></li>
        <%--<li><a href="/MBA2/Manager/ManagerSignup.jsp"><span>Sign Up</span></a></li>--%>
        <li><a href="/MBA2/Manager/AboutUs.jsp"><span>About Us</span></a></li>
        <li><a href="/MBA2/Manager/LearnMore.jsp"><span>Learn More</span></a></li>
        <li><a href="/MBA2/Manager/Policy.jsp"><span>Privacy & Policy</span></a></li>
        <li class="parent"><a href="#"><span>Social Networking</span></a>
        <ul>
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Twitter</a></li>
                <li><a href="#">You Tube</a></li>
                </ul>
            <li><a href="/MBA2/Manager/Contact.jsp"><span>Contact Us</span></a></li>
        <li><a href="/MBA2/Manager/LoginHelp.jsp"><span>Help</span></a></li>

</li>
    </ul>
</div>
<!----><!-- Menu End -->

<!-- Main Frame Start -->
<div id="MainFrame">

<!-- Dynamic Start -->
<div id="Dynamic">
<!-- Heading Table Start-->
<table width="750" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ItemHeading"><h2>Help Desk :</h2></td>
  </tr>
</table>
<!-- Heading Table End -->


<table width="750" border="1" cellspacing="0" cellpadding="0" bordercolor="#CCCCCC">
  <tr class="ItemHeading">
    <td width="200" align="center">Write Your Query Here:</td>
    <form action="/MBA2/htmlhelp">
    <td width="508" align="center"><input type="text" id="helprequest" name="helprequest"></td>
    <td width="146" align="center"><input type="submit" value="    Send Query    "></td>
    </form>
  </tr>
</table>

<!-- Do Not Delet -->
<table width="100">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

</div>
<!-- Dynamic End -->

</div>
<!-- Main Frame End -->



<map name="Social">
  <area shape="rect" coords="625,58,675,107" href="http://www.facebook.com/" target="_blank">
  <area shape="rect" coords="688,56,735,105" href="http://www.twitter.com/" target="_blank">
  <area shape="rect" coords="749,58,797,105" href="http://www.youtube.com/" target="_blank">
</map>
</body>
</html>