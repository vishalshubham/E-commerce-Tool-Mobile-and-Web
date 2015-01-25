<%--
    Document   : Services
    Created on : 6 Mar, 2011, 6:18:12 PM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
        <%
           Object obj =  session.getAttribute("USERNAME");
           String name = (String)obj;
           if(obj == null)
               {
                    response.sendRedirect("/MBA/User/Login.jsp");
               }
        %>
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
<!-- Heading Table Start
<table width="800" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ItemHeading"><h2>CATEGORIES AVAILABLE :</h2></td>
  </tr>
</table>
 Heading Table End -->


<table width="100" height="180" border="0" cellspacing="0" cellpadding="0" align="left">
  <tr>
    <td height="150" class="AllBorderTable"><a href="/MBA/User/ItemCategory.jsp"><img src="images/homepage/Shopping.png" border="0"></a></td>
  </tr>
  <tr>
    <td class="ItemHeading" align="center"><a href="/MBA/User/ItemCategory.jsp">Shopping</a></td>
  </tr>
</table>
<table width="100" height="180" border="0" cellspacing="0" cellpadding="0" align="left">
  <tr>
    <td height="150" class="AllBorderTable"><a href="/MBA/User/ViewYourCart.jsp"><img src="images/homepage/viewyourcart.png" border="0"></a></td>
  </tr>
  <tr>
    <td class="ItemHeading" align="center"><a href="/MBA/User/ViewYourCart.jsp">View Your Cart</a></td>
  </tr>
</table>
<table width="100" height="180" border="0" cellspacing="0" cellpadding="0" align="left">
  <tr>
    <td height="150" class="AllBorderTable"><a href="/MBA/User/CancelOrder.jsp"><img src="images/homepage/cancelorder.png" border="0"></a></td>
  </tr>
  <tr>
    <td class="ItemHeading" align="center"><a href="/MBA/User/CancelOrder.jsp">Cancel Order</a></td>
  </tr>
</table>
<table width="100" height="180" border="0" cellspacing="0" cellpadding="0" align="left">
  <tr>
    <td height="150" class="AllBorderTable"><a href="/MBA/User/Discount.jsp"><img src="images/homepage/discount.png" border="0"></a></td>
  </tr>
  <tr>
    <td class="ItemHeading" align="center"><a href="/MBA/User/Discount.jsp">Discount Offers</a></td>
  </tr>
</table>
<table width="100" height="180" border="0" cellspacing="0" cellpadding="0" align="left">
  <tr>
    <td height="150" class="AllBorderTable"><a href="/MBA/User/ChangeInfo.jsp"><img src="images/homepage/change.png" border="0"></a></td>
  </tr>
  <tr>
    <td class="ItemHeading" align="center"><a href="/MBA/User/ChangeInfo.jsp">Change User Information</a></td>
  </tr>
</table>
<table width="100" height="180" border="0" cellspacing="0" cellpadding="0" align="left">
  <tr>
    <td height="150" class="AllBorderTable"><a href="/MBA/User/Help.jsp"><img src="images/homepage/help.png" border="0"></a></td>
  </tr>
  <tr>
    <td class="ItemHeading" align="center"><a href="/MBA/User/Help.jsp">Help</a></td>
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
  <area shape="rect" coords="627,58,675,107" href="http://www.facebook.com/" target="_blank">
  <area shape="rect" coords="687,58,737,105" href="http://www.twitter.com/" target="_blank">
  <area shape="rect" coords="749,58,797,105" href="http://www.youtube.com/" target="_blank">
</map>
</body>
</html>