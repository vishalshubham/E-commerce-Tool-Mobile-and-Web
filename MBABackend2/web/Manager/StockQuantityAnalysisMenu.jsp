<%-- 
    Document   : StockQuantityAnalysis
    Created on : 2 May, 2011, 2:37:15 PM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mba.manager.main.DateGetter" %>
<%@page import="com.mba.manager.Item.ItemCategoryGetter" %>
<%@page import="java.util.ArrayList" %>
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
                ArrayList<String> ct = ItemCategoryGetter.getCategoryList();
        %>

        <%
            for(int i=0; i < ct.size(); i++ )
                {
                    %>
                    <center>
                    <form action="/MBA2/htmlstockquantityanalysisdaily">
                       <input type="hidden" id="serviceid" name="serviceid" value="30"/>
                       <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
                       <input type="hidden" id="category" name="category" value="<%= ct.get(i) %>"/>
                       <input type="hidden" id="username" name="username" value="<%=session.getAttribute("USERNAME")%>"/>
                       <%DateGetter d = new DateGetter();
                         String spacedate = d.getDate();%>
                       <input type="hidden" id="date" name="date" value=<%=spacedate%>>
                        <br>
                        <input type="submit" name="Login" value="<%= ct.get(i) %>" />
                    </form>
                    </center>
                    <%
                }
        %>



    </body>
</html>
