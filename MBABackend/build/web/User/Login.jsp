<%--
    Document   : Login
    Created on : 6 Mar, 2011, 6:02:48 PM
    Author     : VISHAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
           Object obj =  session.getAttribute("USERNAME");

           if(obj != null)
               {
                    response.sendRedirect("/MBA2/Manager/Services.jsp");
               }
        %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>BEST MART - Best Quality | Price | Service</title>
    <link type="text/css" href="menushopping.css" rel="stylesheet" />
    <link href="login-box.css" rel="stylesheet" type="text/css" />
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
        <li><a href="/MBA/User/Login.jsp"><span>Login</span></a></li>
        <li><a href="#"><span>Sign Up</span></a></li>
        <li><a href="/MBA/User/Discount.jsp"><span>Discounts & Offers</span></a></li>
        <li><a href="#"><span>About Us</span></a></li>
        <li><a href="/MBA/User/HelpNew.jsp"><span>Help</span></a></li>
        <li><a href="/MBA/User/Contact.jsp"><span>Contact Us</span></a></li>
        <li><a href="/MBA/User/Policy.jsp"><span>Privacy & Policy</span></a></li>
        <li class="parent"><a href="#"><span>Social Networking</span></a>
        <ul>
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Twitter</a></li>
                <li><a href="#">You Tube</a></li>
                </ul>
</li>
    </ul>
</div>
<!----><!-- Menu End -->



<!-- Main Frame Start -->
<div id="MainFrame">

<!-- Dynamic Start -->
<div id="Dynamic">
<%--<!-- Heading Table Start-->
<table width="750" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ItemHeading"><h2></h2></td>
  </tr>
</table>
<!-- Heading Table End -->--%>
<center>
<table width="473" border="0" cellspacing="0" cellpadding="0" align="center" >
  <tr>
    <td>
    <div id="login-box" align="center">

        <H2> Login :</H2>
<br />
<form action="/MBA/htmllogin">
<div id="login-box-name" style="margin-top:20px;">User Name:</div><div id="login-box-field" style="margin-top:20px;"><input type="text" name="username" class="form-login" title="Username" value="" size="30" maxlength="2048" /></div>
<div id="login-box-name">Password:</div><div id="login-box-field"><input name="password" type="password" class="form-login" title="Password" value="" size="30" maxlength="2048" /></div>
                <%
                    Object loginMsg = session.getAttribute("LOGINMSG");
                    if(loginMsg != null)
                        {
                            session.removeAttribute("LOGINMSG");
                %>

                         <%= loginMsg %>

                <%
                        }

                %>

<br />
<span class="login-box-options"><input type="checkbox" name="1" value="1"> Remember Me <a href="#" style="margin-left:30px; color: #FFF;">Forgot password?</a></span>
<br />
<br />
<!--<a href="#"><img src="images/login-btn.png" width="103" height="42" style="margin-left:90px;" /></a>-->
<input type="submit" name="Login" value="    Login    "/>
</form>





</div>
    </td>
  </tr>
</table>



                <!--This is Important-->
<script type="text/javascript" src="images/login/jquery-1.js"></script>
<script type="text/javascript" src="images/login/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="images/login/skin.css">

<script type="text/javascript">

jQuery(document).ready(function() {
    jQuery('#mycarousel').jcarousel();
});

</script>


    <div class=" jcarousel-skin-tango"><div style="position: relative; display: block;" class="jcarousel-container jcarousel-container-horizontal"><div style="overflow: hidden; position: relative;" class="jcarousel-clip jcarousel-clip-horizontal"><ul style="overflow: hidden; position: relative; top: 0px; left: 0px; margin: 0px; padding: 0px; width: 2928px;" id="mycarousel" class="jcarousel-list jcarousel-list-horizontal">

                <li jcarouselindex="1" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-1 jcarousel-item-1-horizontal"><a href="http://bigbazaar.futurebazaar.com/Catalog/Bags+%26+Luggage/Bags/Airbags/1003758/br"><img src="images/login/Deodrants.jpg" alt="Deodrants" title="Deodrants" border="0"></a></li>

		<li jcarouselindex="2" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-2 jcarousel-item-2-horizontal"><a href="http://bigbazaar.futurebazaar.com/Catalog/Toys+%26+Stationery/Toys+%26+Games/Board+Games/1003716"><img src="images/login/chyavanprash.jpg" alt="chyavanprash" title="chyavanprash" border="0"></a></li>

		<li jcarouselindex="3" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-3 jcarousel-item-3-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/Doormat1/onlyavailable"><img src="images/login/chips.jpg" alt="chips" title="chips" border="0"></a></li>

		<li jcarouselindex="4" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-4 jcarousel-item-4-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/Nirali"><img src="images/login/detergents.jpg" alt="detergents" title="detergents" border="0"></a></li>

		<li jcarouselindex="5" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-5 jcarousel-item-5-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/mattel/onlyavailable"><img src="images/login/hairoil.jpg" alt="hairoil" title="hairoil" border="0"></a></li>

                <li jcarouselindex="6" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-6 jcarousel-item-6-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/Ftwr26"><img src="images/login/Maggi.jpg" alt="Maggi" title="Maggi" border="0"></a></li>

		<li jcarouselindex="7" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-7 jcarousel-item-7-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/HomeAppliances26"><img src="images/login/biscuits.jpg" alt="biscuits" title="biscuits" border="0"></a></li>

		<li jcarouselindex="8" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-8 jcarousel-item-8-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/KitWr26"><img src="images/login/fruits.jpg" alt="fruits" title="fruits" border="0"></a></li>

		<li jcarouselindex="9" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-9 jcarousel-item-9-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/JnW26"><img src="images/login/jam.jpg" alt="jam" title="jam" border="0"></a></li>

		<li jcarouselindex="10" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-10 jcarousel-item-10-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/KitchenAppliances26"><img src="images/login/milk.jpg" alt="milk" title="milk" border="0"></a></li>

		<li jcarouselindex="11" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-11 jcarousel-item-11-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/LapnAcc26"><img src="images/login/mixture.jpg" alt="mixture" title="mixture" border="0"></a></li>

		<li jcarouselindex="12" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-12 jcarousel-item-12-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/Lugg26"><img src="images/login/tea.jpg" alt="tea" title="tea" border="0"></a></li>

		<li jcarouselindex="13" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-13 jcarousel-item-13-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/Mobile26"><img src="images/login/toothpaste.jpg" alt="toothpaste" title="toothpaste" border="0"></a></li>

		<li jcarouselindex="14" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-14 jcarousel-item-14-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/Stat26"><img src="images/login/vegetables.jpg" alt="vegetables" title="vegetables" border="0"></a></li>

		<li jcarouselindex="15" style="float: left; list-style: none outside none;" class="jcarousel-item jcarousel-item-horizontal jcarousel-item-15 jcarousel-item-15-horizontal"><a href="http://bigbazaar.futurebazaar.com/Search/Tow26"><img src="images/login/washing soap.jpg" alt="soap" title="soap" border="0"></a></li>

  </ul></div><div disabled="true" style="display: block;" class="jcarousel-prev jcarousel-prev-horizontal jcarousel-prev-disabled jcarousel-prev-disabled-horizontal"></div><div disabled="false" style="display: block;" class="jcarousel-next jcarousel-next-horizontal"></div></div></div>






</center>


</div>
<!-- Dynamic End -->

</div>
<!-- Main Frame End -->



<map name="Social">
  <area shape="rect" coords="626,58,675,106" href="http://www.facebook.com/" target="_blank">
  <area shape="rect" coords="689,58,734,106" href="http://www.twitter.com/" target="_blank">
  <area shape="rect" coords="748,59,797,105" href="http://www.youtube.com/" target="_blank">
</map>
</body>
</html>