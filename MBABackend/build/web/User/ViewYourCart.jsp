<%--
    Document   : ViewYourCart
    Created on : 9 Mar, 2011, 10:52:21 PM
    Author     : VISHAL
--%>

<%@page import="java.util.Collection" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.Set" %>
<%@page import="java.util.Iterator" %>
<%@page import="com.mba.user.main.DateGetter" %>
<%@page import="com.mba.html.user.transaction.CartItemObject" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
<table width="800" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ItemHeading"><h2>VIEW YOUR CART :</h2></td>
  </tr>
</table>
<!-- Heading Table End -->

<%
                Map<String, CartItemObject> cart = (Map<String, CartItemObject>)request.getSession().getAttribute("CART");
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
                Collection<CartItemObject> co = cart.values();
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
                    {  %>
                    <table width="750" border="1" cellspacing="0" cellpadding="0" bordercolor="#CCCCCC">
                  <tr class="ItemHeading">
                    <td width="46" align="center">Sr No.</td>
                    <td width="100" align="center">ID</td>
                    <td width="222" align="center">Name</td>
                    <td width="75" align="center">Price</td>
                    <td width="71" align="center">Quantity</td>
                    <td width="110" align="center">Edit Item</td>
                    <td width="110" align="center">Delete Item</td>
                  </tr>



        <%
            int count = 0;
            while(cartset.hasNext())
                {
                    
                    String key = cartset.next();
                    CartItemObject cartItem = cart.get(key);
                    String itemId = cartItem.getItemId();
                    String itemName = cartItem.getItemName();
                    String itemPrice = cartItem.getItemPrice();
                    String itemCategory= cartItem.getCategory();
                    String itemQuantity= cartItem.getQuantity();
                    String srno = Integer.toString(++count);

                    itemName = itemName.replaceAll("%20", " ");
                    itemCategory = itemCategory.replaceAll("%20", " ");

                    System.out.println("===="+itemName + "= = = = ="+itemCategory);
                    %>

                   <tr>
                    <td align="center"><span class="ItemDetail"><%=srno%></span></td>
                    <td align="center"><span class="ItemDetail"><%=itemId%></span></td>
                    <td align="center"><span class="ItemDetail"><%=itemName%></span></td>
                    <td align="center"><span class="ItemDetail"><%=itemPrice%></span></td>

                    <form action="http://localhost:8084/MBA/htmledititem">
                        <%
                        itemName = itemName.replaceAll(" ", "%20");
                        itemCategory = itemCategory.replaceAll(" ", "%20");
                        %>

                    <input type="hidden" id="itemid" name="itemid" value=<%=itemId%>>
                    <input type="hidden" id="itemname" name="itemname" value=<%=itemName%>>
                    <input type="hidden" id="itemprice" name="itemprice" value=<%=itemPrice%>>
                    <input type="hidden" id="itemcategory" name="itemcategory" value=<%=itemCategory%>>
                    <td align="center"><input type="text" id="itemquantity" name="itemquantity" value=<%=itemQuantity%> width="70px"></td>
                    <td align="center"><input type="submit" value="   Edit   "></td>
                    </form>

                    <form action="http://localhost:8084/MBA/htmldeleteitem">
                    <input type="hidden" id="itemid" name="itemid" value=<%=itemId%>>
                    <td align="center"><input type="submit" value="   Delete   " ></td>
                    </form>
                  </tr>

                    <%--Item Id : <%=itemId%>
                    Item Name : <%=itemName%>
                    Item Price : <%=itemPrice%>
                    Item Category : <%=itemCategory%>
                    Item Quantity :

                    <form action="http://localhost:8084/MBA/htmledititem">
                    <input type="hidden" id="itemid" name="itemid" value=<%=itemId%>>
                    <input type="hidden" id="itemname" name="itemname" value=<%=itemName%>>
                    <input type="hidden" id="itemprice" name="itemprice" value=<%=itemPrice%>>
                    <input type="hidden" id="itemcategory" name="itemcategory" value=<%=itemCategory%>>
                    <input type="text" id="itemquantity" name="itemquantity" value=<%=itemQuantity%>>
                    <input type="submit" value="Edit" >
                    </form>

                    <form action="http://localhost:8084/MBA/htmldeleteitem">
                    <input   type="hidden" id="itemid" name="itemid" value=<%=itemId%>>
                    <input type="submit" value="Delete" >
                    <br>--%>

                    <%

                }
            %>

            
           </table>

            <br>
                <table width="750" border="1" cellspacing="0" cellpadding="0" bordercolor="#CCCCCC">
                  <tr class="ItemHeading">
                    <td width="386" align="center">Place Order : </td>
                    
                        <form action="http://localhost:8084/MBA/htmlcartsend">
                        <input type="hidden" id="serviceid" name="serviceid" value="2"/>
                        <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
                        <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>"/>
                        <%DateGetter d = new DateGetter();
                          String spacedate = d.getDate();%>
                        <input type="hidden" id="date" name="date" value=<%=spacedate%>>
                        <td align="center"><input type="submit" value="Order your Cart"/></td>
                        </form>
                  </tr>
                </table>

                <%
                }
                }
                %>

<!--
<table width="750" border="1" cellspacing="0" cellpadding="0" bordercolor="#CCCCCC">
  <tr class="ItemHeading">
    <td width="46" align="center">Sr No.</td>
    <td width="100" align="center">ID</td>
    <td width="222" align="center">Name</td>
    <td width="75" align="center">Price</td>
    <td width="71" align="center">Quantity</td>
    <td width="110" align="center">Edit Item</td>
    <td width="110" align="center">Delete Item</td>
  </tr>
    <tr>
    <td align="center"><span class="ItemDetail">1</span></td>
    <td align="center"><span class="ItemDetail">10023</span></td>
    <td align="center"><span class="ItemDetail">HARSH VARDHAN</span></td>
    <td align="center"><span class="ItemDetail">40000000</span></td>
    <td align="center"><input type="text" id="Text1" name="Text1" width="70px"></td>
    <td align="center"><input type="submit" id="Text1" name="Text1" value="   Edit   "></td>
    <td align="center"><input type="submit" id="Text1" name="Text1" value="   Delete   "></td>
  </tr>
</table>
-->










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