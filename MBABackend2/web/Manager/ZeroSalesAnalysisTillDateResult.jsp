<%-- 
    Document   : ZeroSalesAnalysisTillDateResult
    Created on : 3 May, 2011, 8:13:48 PM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.Set" %>
<%@page import="java.util.Iterator" %>
<%@page import="com.mba.manager.html.services.zerosalesanalysis.ZeroSalesAnalysisObject" %>
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


     <%
           Object obj =  session.getAttribute("USERNAME");

           if(obj == null)
               {
                    response.sendRedirect("/MBA2/Manager/Login.jsp");
               }
        %>


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
        <li><a href="/MBA2/Manager/Services.jsp"><span>Home</span></a></li>
        <li class="parent"><a href="/MBA2/Manager/CategorySalesAnalysisMenu.jsp"><span>Category Sales</span></a>
                <%--<ul>
                <li><a href="/MBA2/Manager/CategorySalesAnalysisMenu.jsp">Daily Category Sales</a></li>
                <li><a href="/MBA2/Manager/CategorySalesAnalysisMenu.jsp">Monthly Category Sales</a></li>
                <li><a href="/MBA2/Manager/CategorySalesAnalysisMenu.jsp">Yearly Category Sales</a></li>
                </ul>--%>
	</li>
        <li class="parent"><a href="/MBA2/Manager/ProductSalesAnalysisMenu.jsp"><span>Product Sales</span></a>
                <%--<ul>
                <li><a href="/MBA2/Manager/ProductSalesAnalysisMenu.jsp">Daily Product Sales</a></li>
                <li><a href="/MBA2/Manager/ProductSalesAnalysisMenu.jsp">Monthly Product Sales</a></li>
                <li><a href="/MBA2/Manager/ProductSalesAnalysisMenu.jsp">Yearly Product Sales</a></li>
                </ul>--%>
	</li>
        <li class="parent"><a href="/MBA2/Manager/RevenueGeneratedTime.jsp"><span>Revenue Analysis</span></a>
                <ul>
                <li><a href="/MBA2/Manager/RevenueGeneratedDaily.jsp">Daily Revenue</a></li>
                <li><a href="/MBA2/Manager/RevenueGeneratedMonthly.jsp">Monthly Revenue</a></li>
                <li><a href="/MBA2/Manager/RevenueGeneratedYearly.jsp">Yearly Revenue</a></li>
                </ul>
	</li>
        <li class="parent"><a href="/MBA2/Manager/ZeroSalesAnalysisMenu.jsp"><span>Zero Sales</span></a>
                <ul>
                <li><a href="/MBA2/Manager/ZeroSalesAnalysisDaily.jsp">Daily Zero Sales Analysis</a></li>
                <li><a href="/MBA2/Manager/ZeroSalesAnalysisTillDate.jsp">Till Date Zero Sales Analysis</a></li>
                </ul>
	</li>
        <li class="parent"><a href="/MBA2/Manager/HomeDeliveryAnalysisTime.jsp"><span>Home Delivery</span></a>
                <ul>
                <li><a href="/MBA2/Manager/HomeDeliveryAnalysisDaily.jsp">Daily Home Delivery</a></li>
                <li><a href="/MBA2/Manager/HomeDeliveryAnalysisMonthly.jsp">Monthly Home Delivery</a></li>
                <li><a href="/MBA2/Manager/HomeDeliveryAnalysisYearly.jsp">Yearly Home Delivery</a></li>
                </ul>
	</li>
        <li class="parent"><a href="/MBA2/Manager/ExpiryDateAnalysisMenu.jsp"><span>Expiry Analysis</span></a>
                <%--<ul>
                <li><a href="/MBA2/Manager/ExpiryDateAnalysisMenu.jsp">Daily Expiry Analysis</a></li>
                <li><a href="/MBA2/Manager/ExpiryDateAnalysisMenu.jsp">Monthly Expiry Analysis</a></li>
                <li><a href="/MBA2/Manager/ExpiryDateAnalysisMenu.jsp">Yearly Expiry Analysis</a></li>
                </ul>--%>
	</li>
        <li class="parent"><a href="/MBA2/Manager/StockQuantityAnalysisMenu.jsp"><span>Stock Analysis</span></a>
        <li><a href="/MBA2/htmlmanagerlogout"><span>Logout</span></a></li>
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
    <td class="ItemHeading"><h2>Zero Sales : Till Date Report</h2></td>
  </tr>
</table>
<!-- Heading Table End -->


<%
            Map<String, ZeroSalesAnalysisObject> c = (Map<String, ZeroSalesAnalysisObject>)request.getSession().getAttribute("ZSA_TILLDATE_OBJECT");

            if(c==null)
                {
                System.out.println("oooooooooooooooooooppppppppppppppppppppppppppppsssssssssssssssssssssssss");
        %>
            <table width="800" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="ItemHeading"><h3>Sorry No Sales on that day.</h3></td>
                  </tr>
                </table>
        <%
                }
            else
                {
                Iterator<String> cset = c.keySet().iterator();
                if(c.size() == 0)
                {
                    System.out.println("ssssssssssssssssssssssssssssssssspppppppppppppppppppppppppppppppppppppppppoooooooooooooooooooooooo");
                    %>
                    <table width="800" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="ItemHeading"><h3>Sorry No Sales on that day.</h3></td>
                  </tr>
                </table>
                    <%

                }
                else
                {   %>
                  <br> <table width="750" border="1" cellspacing="0" cellpadding="0" bordercolor="#CCCCCC">
                  <tr class="ItemHeading">
                    <td width="250" align="center">Item Id </td>
                    <td width="250" align="center">Item Name</td>
                    <td width="250" align="center">Item Category</td>

                  </tr>

                         <%
               while(cset.hasNext())
                {
                    String key = cset.next();
                    ZeroSalesAnalysisObject cc = c.get(key);
                    String itemId = cc.getItemid();
                    String itemName = cc.getItem_name();
                    String itemCategory= cc.getCategory();


                    %>
                    <center>
                    <tr>
                    <td align="center"><span class="ItemDetail"><%=itemId%></span></td>
                    <td align="center"><span class="ItemDetail"><%=itemName%></span></td>
                    <td align="center"><span class="ItemDetail"><%=itemCategory%></span></td>
                    </tr></center>





                    <%
               }
                }
                }
                    %>



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

