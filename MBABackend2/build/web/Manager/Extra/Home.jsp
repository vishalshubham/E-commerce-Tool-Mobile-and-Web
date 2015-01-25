<%-- 
    Document   : Home
    Created on : 11 May, 2011, 12:29:04 AM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <title> Menu Bar</title>
    <link type="text/css" href="menu.css" rel="stylesheet" />
    <script type="text/javascript" src="menu.js"></script>
</head>
<body>

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

</style>


<!-- Main Frame Start -->
<div id="MainFrame">

<!-- Menu Start -->
<div id="menu">
    <ul class="menu">
        <li><a href="#"><span>Home</span></a></li>
<li class="parent"><a href="#"><span>Shopping</span></a>

                <ul>
                <li><a href="#">Groceries</a></li>
                <li><a href="#">Fruits</a></li>
                <li><a href="#">Vegetables</a></li>
                <li><a href="#">Drinkable</a></li>
                <li><a href="#">House Hold</a></li>
                </ul>
	  </li>
        <li><a href="#"><span>View Cart</span></a></li>
        <li><a href="#"><span>Cancel Order</span></a></li>
        <li><a href="#"><span>Discount Offers</span></a></li>
        <li><a href="#"><span>Change User Information</span></a></li>
        <li><a href="#"><span>Help</span></a></li>
        <li class="last"><a href="#"><span>Check Out</span></a></li>
    </ul>
</div>
<!-- Menu End -->

<!-- Dynamic Start -->
<div id="Dynamic">

    <%
        String name = "HairOil";
        for(int i = 0 ; i < 40 ; i++)
            {
    %>

<table width="100px" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td class="AllBorderTable"><img src="../../images/Category/HairOil.png" width="100" height="150"></td>
  </tr>
  <tr>
    <td class="LRBBoarderTable"><%=name%></td>
  </tr>
</table>
<%
        }
%>

</div>
<!-- Dynamic End -->

</div>
<!-- Main Frame End -->

</body>
</html>
