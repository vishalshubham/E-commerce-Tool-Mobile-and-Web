<%-- 
    Document   : SignUp
    Created on : 27 Apr, 2011, 2:31:48 AM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mba.user.main.DateGetter" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>
            <h1>Sign Up for BEST MART : Best Service : Best Quality : Best Price</h1>

            <form action="http://localhost:8084/MBA/htmlnameupdate">
                <input type="text" id="name" name="name" />
                
                <input type="hidden" id="serviceid" name="serviceid" value="3"/>
                <input type="hidden" id="requestsourceid" name="requestsourceid" value="2"/>
                <%DateGetter d = new DateGetter();
                  String spacedate = d.getDate();%>
                <input type="hidden" id="date" name="date" value=<%=spacedate%>>
                <input type="submit"/>
            </form>
        </center>
    </body>
</html>
