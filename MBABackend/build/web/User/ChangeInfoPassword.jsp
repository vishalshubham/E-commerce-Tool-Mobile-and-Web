<%--
    Document   : ChangeInfoPassword
    Created on : 9 Mar, 2011, 11:19:16 PM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mba.user.main.DateGetter" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

         <%
           Object obj =  session.getAttribute("USERNAME");

           if(obj == null)
               {
                    response.sendRedirect("/MBA/User/Login.jsp");
               }
        %>



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
#apDiv2 {
	position:absolute;
	width:182px;
	height:23px;
	z-index:10;
	left: 841px;
	top: 6px;
	text-align: right;
}
    </style>
</head>
<body>



<!-- Header Start -->
<div id="Header">

<table width="1003" align="center">
  <tr>
    <td><img src="images/Header.png" width="900" height="119" border="0" usemap="#Social">
      <div class="guest" id="apDiv2"> Hi, Guest</div></td>
  </tr>
</table>

</div>
<!-- Header End -->

<!-- Menu Start -->
<div id="menu">
    <ul class="menu">
        <li><a href="/MBA/User/Services.jsp"><span>Home</span></a></li>
<li class="parent"><a href="/MBA/User/ItemCategory.jsp"><span>Shopping</span></a>
                <ul>
                <li><a href="#">Groceries</a></li>
                <li><a href="#">Fruits</a></li>
                <li><a href="#">Vegetables</a></li>
                <li><a href="#">Drinkable</a></li>
                <li><a href="#">House Hold</a></li>
                </ul>
	  </li>
        <li><a href="/MBA/User/ViewYourCart.jsp"><span>View Cart</span></a></li>
        <li><a href="/MBA/User/CancelOrder.jsp"><span>Cancel Order</span></a></li>
        <li><a href="/MBA/User/Discount.jsp"><span>Discount Offers</span></a></li>
        <li><a href="/MBA/User/ChangeInfo.jsp"><span>Change User Information</span></a></li>
        <li><a href="/MBA/User/Help.jsp"><span>Help</span></a></li>
        <li class="last"><a href="/MBA/htmllogout"><span>Check Out</span></a></li>
    </ul>
</div>
<!-- Menu End -->



<!-- Main Frame Start -->
<div id="MainFrame">

<!-- Dynamic Start -->
<div id="Dynamic">
<!-- Heading Table Start-->
<table width="750" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ItemHeading"><h2>Change Password :	</h2></td>
  </tr>
</table>
<!-- Heading Table End -->


<form action="http://localhost:8084/MBA/htmlcontactupdate">

<table width="750" border="1" cellspacing="0" cellpadding="0" bordercolor="#CCCCCC">
  <tr class="ItemHeading" >
    <td width="200" align="center">Enter Old Password :</td>
    <td align="center"><input type="text" id="oldpassword" name="oldpassword" width="170"></td>
  </tr>
  <tr class="ItemHeading" >
    <td width="200" align="center">Enter New Password :</td>
    <td align="center"><input type="text" id="password" name="password" width="170px"></td>
  </tr>
  <tr class="ItemHeading" >
    <td width="200" align="center">Confirm New Password :</td>
    <td align="center"><input type="text" id="confirmpassword" name="confirmpassword" width="170px"></td>
  </tr>
                  <input type="hidden" id="serviceid" name="serviceid" value="6"/>
                <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
                <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>"/>
                <%DateGetter d = new DateGetter();
                  String spacedate = d.getDate();%>
                  <input type="hidden" id="date" name="date" value=<%=spacedate%>>

<tr class="ItemHeading">
<td width="200" align="center">Change Password:</td>
<td align="center"><input type="submit" value="       Edit Password       "></form></td>
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
  <area shape="rect" coords="626,58,675,108" href="http://www.facebook.com/" target="_blank">
  <area shape="rect" coords="687,58,736,106" href="http://www.twitter.com/" target="_blank">
  <area shape="rect" coords="748,58,797,108" href="http://www.youtube.com/" target="_blank">
</map>
</body>
</html>