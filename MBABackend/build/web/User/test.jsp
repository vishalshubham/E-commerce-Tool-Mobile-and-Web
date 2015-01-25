<%-- 
    Document   : test
    Created on : 22 Feb, 2011, 7:55:18 PM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <% String[] arr = new String[]{"ABC","PQR", "XYZ", "LMV"};
           String[] dest = new  String[]{"ABC","PQR"};
        %>



        <h1>Hello <%= request.getParameter("userId") %>

        </h1>


        <h1>444444444444444Hello 

        </h1>

        <a href="/LOGIN"> TEST </a>
        

        <%
            for(String str : arr)
                {

                %>

                 <a href="<%= dest[0]  %>"> <%= str %>  </a>

                 <%
                 }
         %>




    </body>
</html>
