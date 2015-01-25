<%--
    Document   : ItemFetch
    Created on : 12 Mar, 2011, 11:05:45 AM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mba.user.http.Item.ItemListGetter" %>
<%@page import="com.mba.html.user.transaction.CartItemObject" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <title>BEST MART - Best Quality | Price | Service</title>
    <link type="text/css" href="menu.css" rel="stylesheet" />
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

</style>
</head>
<body>

        <%String category = request.getParameter("category"); %>


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
    <td class="ItemHeading"><h2><%= category %> Category Products:</h2></td>
  </tr>
</table>
<!-- Heading Table End -->

        <%
                System.out.println("in itemlist html page");
                ArrayList<CartItemObject> ct = ItemListGetter.getItemList(request.getParameter("category"));
        %>

        <%
            for(int i=0; i < ct.size(); i++ )
                {
                    CartItemObject io = new CartItemObject();
                    io = ct.get(i);

                    String itemId = io.getItemId();
                    String itemName= io.getItemName();
                    String itemPrice= io.getItemPrice();
                    String itemCategory= io.getCategory();
                    String itemQuantity= io.getQuantity();

        %>


<table width="360" border="0" align="left" cellpadding="0" cellspacing="0" height="150px" class="AllBorderTable">
  <tr>
    <td width="102" class="RBoarderTable"><img src="images/itemlist/<%= itemName%>.jpg" width="100" height="150"></td>
    <td width="162" class="RBoarderTable">

    <table width="150" height="150px" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td class="ItemHeading" width="60">Id</td>
        <td class="ItemDetail" width="90"><%= itemId%></td>
        </tr>
      <tr>
        <td class="ItemHeading">Name </td>
        <td class="ItemDetail"><%= itemName%></td>
        </tr>
      <tr>
        <td class="ItemLastHeading">Price</td>
        <td class="ItemLastDetail">Rs. <%= itemPrice%></td>
        </tr>
    </table>

<script type="text/javascript">
function show_alert()
{
alert("Item added to your Cart");
}
</script>

    </td>
    <td width="92">
    <table width="84" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
              <form action="http://localhost:8084/MBA/htmladdtocart">
                <%
                    itemName = itemName.replaceAll(" ", "%20");
                    itemCategory = itemCategory.replaceAll(" ", "%20");
                %>
                <input type="hidden" id="itemid" name="itemid" value=<%=itemId%>>
                <input type="hidden" id="itemname" name="itemname" value=<%=itemName%>>
                <input type="hidden" id="itemprice" name="itemprice" value=<%=itemPrice%>>
                <input type="hidden" id="category" name="category" value=<%=itemCategory%>>
                <input type="hidden" id="quantity" name="quantity" value=<%=itemQuantity%>>

                <td width="84"><input type="submit" value="Add To Cart" onclick="show_alert()"> </form></td>
  </tr>
</table>
    </td>
  </tr>
</table>
  <%
            }
        %>



    </td>
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
  <area shape="rect" coords="626,57,675,106" href="http://www.facebook.com/" target="_blank">
  <area shape="rect" coords="688,58,735,104" href="http://www.twitter.com/" target="_blank">
  <area shape="rect" coords="748,58,796,105" href="http://www.youtube.com/" target="_blank">
</map>
</body>
</html>