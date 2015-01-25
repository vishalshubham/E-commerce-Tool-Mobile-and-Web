<%-- 
    Document   : ExpiryDateAnalysis
    Created on : 3 May, 2011, 11:53:25 PM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mba.manager.main.DateGetter" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

        <%
           Object obj =  session.getAttribute("USERNAME");

           if(obj == null)
               {
                    response.sendRedirect("/MBA2/Manager/Login.jsp");
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
        <li><a href="/MBA/Manager/Services.jsp"><span>Home</span></a></li>
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
    <td class="ItemHeading"><h2>Expiry Duration To be Calculated in :</h2></td>
  </tr>
</table>
<!-- Heading Table End -->


<table width="750" border="1" cellspacing="0" cellpadding="0" bordercolor="#CCCCCC">
  <tr class="ItemHeading">
      <form action="/MBA2/htmlexpirydateanalysisdays">
           <input type="hidden" id="serviceid" name="serviceid" value="36"/>
           <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
           <input type="hidden" id="category" name="category" value="<%=session.getAttribute("EDA_CATEGORY")%>"/>
           <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>"/>
           <%DateGetter d = new DateGetter();
             String spacedate0 = d.getDate();%>
           <input type="hidden" id="date" name="date" value=<%=spacedate0%>>
    <td width="250" align="center"><input type="submit" name="Login" value="Calculate In Days" /></td>
     </form>

    <form action="/MBA2/htmlexpirydateanalysismonths">
           <input type="hidden" id="serviceid" name="serviceid" value="36"/>
           <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
           <input type="hidden" id="category" name="category" value="<%=session.getAttribute("EDA_CATEGORY")%>"/>
           <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>"/>
           <%DateGetter dd = new DateGetter();
             String spacedate1 = dd.getDate();%>
           <input type="hidden" id="date" name="date" value=<%=spacedate1%>>
           <td width="250" align="center"><input type="submit" name="Login" value="Calculate In Months" /></td>
            </form>

            <form action="/MBA2/htmlexpirydateanalysisyears">
           <input type="hidden" id="serviceid" name="serviceid" value="36"/>
           <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
           <input type="hidden" id="category" name="category" value="<%=session.getAttribute("EDA_CATEGORY")%>"/>
           <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>"/>
           <%DateGetter ddd = new DateGetter();
             String spacedate2 = ddd.getDate();%>
           <input type="hidden" id="date" name="date" value=<%=spacedate2%>>
           <td width="250" align="center"><input type="submit" name="Login" value="Calculate In Years" /></td>
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




<%--
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <center>
            Expiry Duration To be Calculated in :
            Days :
        <form action="/MBA2/htmlexpirydateanalysisdays">
           <input type="hidden" id="serviceid" name="serviceid" value="36"/>
           <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
           <input type="hidden" id="category" name="category" value="<%=session.getAttribute("EDA_CATEGORY")%>"/>
           <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>"/>
           <%DateGetter d = new DateGetter();
             String spacedate0 = d.getDate();%>
           <input type="hidden" id="date" name="date" value=<%=spacedate0%>>
           <input type="submit" name="Login" value="Calculate In Days" />
        </form>
           <br>
           Months :
            <form action="/MBA2/htmlexpirydateanalysismonths">
           <input type="hidden" id="serviceid" name="serviceid" value="36"/>
           <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
           <input type="hidden" id="category" name="category" value="<%=session.getAttribute("EDA_CATEGORY")%>"/>
           <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>"/>
           <%DateGetter dd = new DateGetter();
             String spacedate1 = dd.getDate();%>
           <input type="hidden" id="date" name="date" value=<%=spacedate1%>>
           <input type="submit" name="Login" value="Calculate In Months" />
            </form>
           <br>
           Years :
            <form action="/MBA2/htmlexpirydateanalysisyears">
           <input type="hidden" id="serviceid" name="serviceid" value="36"/>
           <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
           <input type="hidden" id="category" name="category" value="<%=session.getAttribute("EDA_CATEGORY")%>"/>
           <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>"/>
           <%DateGetter ddd = new DateGetter();
             String spacedate2 = ddd.getDate();%>
           <input type="hidden" id="date" name="date" value=<%=spacedate2%>>
           <input type="submit" name="Login" value="Calculate In Years" />
        </form>
        </center>

    </body>
</html>
--%>