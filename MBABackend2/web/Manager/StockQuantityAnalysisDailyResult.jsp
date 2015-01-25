<%-- 
    Document   : StockQuantityAnalysisDailyResult
    Created on : 4 May, 2011, 1:39:09 AM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.Set" %>
<%@page import="java.util.Iterator" %>
<%@page import="com.mba.manager.html.services.stockquantityanalysis.StockQuantityAnalysisObject" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            Map<String, StockQuantityAnalysisObject> c = (Map<String, StockQuantityAnalysisObject>)request.getSession().getAttribute("SQA_DAILY_OBJECT");

            if(c==null)
                {
        %>
            <h3>Sorry No Sales on that day...</h3>
        <%
                }
            else
                {
                Iterator<String> cset = c.keySet().iterator();
                if(c.size() == 0)
                {
                    %>
                    <h3>Sorry No Sales on that day...</h3>
                    <%

                }
                else
                {
                while(cset.hasNext())
                {
                    String key = cset.next();
                    StockQuantityAnalysisObject cc = c.get(key);
                    String itemId = cc.getItemid();
                    String itemName = cc.getItem_name();
                    String itemCategory= cc.getCategory();
                    String itemQuantity= cc.getQuantity();

                    %>
                    <center>
                    Item Id : <%=itemId%><br>
                    Item Name : <%=itemName%><br>
                    Item Category : <%=itemCategory%><br>
                    Item Quantity : <%=itemQuantity%><br><br><br></center>
                    <%
               }
                }
                }
                    %>

        <img src="../../images/StockQuantityAnalysis/myimg.jpg" alt="Image 0" />

    </body>
</html>
