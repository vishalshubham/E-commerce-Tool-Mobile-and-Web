<%--
    Document   : CancelOrderList
    Created on : 24 Apr, 2011, 5:10:14 PM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.mba.user.main.DateGetter" %>
<%@page import="java.util.Collection" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Set" %>
<%@page import="java.util.Iterator" %>
<%@page import="com.mba.html.user.transaction.CartDelete" %>
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
<!-- Heading Table Start-->
<table width="800" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ItemHeading"><h2>ORDER DETAILS :</h2></td>
  </tr>
</table>
<!-- Heading Table End -->

 <%
                Map<String, CartDelete> cart = (Map<String, CartDelete>)request.getSession().getAttribute("ORDEREDCART");
                if(cart == null)
                {
                    %>
                    <table width="800" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="ItemHeading"><h3>Sorry You have not ordered anything till now</h3></td>
                  </tr>
                </table>
                    <%
                }
                else
                {
                Collection<CartDelete> co = cart.values();
                Iterator<String> cartset = cart.keySet().iterator();

        %>

        <%

        if(cart.size() == 0)
            {
                    %>
                    <table width="800" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="ItemHeading"><h3>Sorry You have not ordered anything till now</h3></td>
                  </tr>
                </table>
                    <%

            }
                   else
                    {
            while(cartset.hasNext())
                {
                    String key = cartset.next();
                    CartDelete cartItem = cart.get(key);
                    String username = cartItem.getUsername();
                    String orderTime = cartItem.getOrderTime();

                    %>



<table width="750" border="1" cellspacing="0" cellpadding="0" bordercolor="#CCCCCC">
  <tr class="ItemHeading">
    <td width="88" align="center">Sr No.</td>
    <td width="508" align="center">Order Date</td>
    <td width="146" align="center">Cancel Cart</td>
  </tr>
    <tr>
    <td align="center"><span class="ItemDetail">1</span></td>
    <td align="center"><%=orderTime%></td>
    <form action="http://localhost:8084/MBA/htmlcartdelete">
                    <input type="hidden" id="serviceid" name="serviceid" value="7">
                    <input type="hidden" id="requestsourceid" name="requestsourceid" value="2">
                    <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>">
                    <input type="hidden" id="cancelorder" name="cancelorder" value=<%=orderTime%>>
                    <%DateGetter d = new DateGetter();
                    String spacedate = d.getDate();%>
                    <input type="hidden" id="date" name="date" value=<%=spacedate%>>

    <td align="center"><input type="submit" value="   Cancel  Order   "></td>
    </form>
    <%

                }
                }
                }
                %>
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