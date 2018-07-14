<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="${contextPath}/resources/vendors/css/normalize.css" type="text/css">
        <link rel="stylesheet" href="${contextPath}/resources/vendors/css/grid.css" type="text/css">
        <link rel="stylesheet" href="${contextPath}/resources/css/style.css" type="text/css">
        <link rel="stylesheet" href="${contextPath}/resources/css/queries.css" type="text/css">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Omnifood is a premium food delivery service with the mission to bring affordable and healthy meals to as many peple as possible">
        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet">
        <meta name="theme-color" content="#ffffff">
        <title>Pizzeria A La Fer</title>
        
    </head>
    <body>
        <header>
            <nav>
                <div class="row"> <!-- row will always be centered-->
                    <img src="${contextPath}/resources/img/logo-white.png" alt="Omnifood Logo" class="logo">
                    <img src="${contextPath}/resources/img/logo.png" alt="Omnifood Logo" class="logo-black">
                    <ul class="main-nav js--main-nav"> <!-- each list element will be a link-->
                        <li><a href="${contextPath}/myOrders">My Orders</a></li>
                        <li><a href="${contextPath}/myProfile">Profile</a></li>
                        <li><a href=${contextPath}/getInfo">About Us</a></li>
                        <c:if test="${pageContext.request.userPrincipal.name == null}">
                            <li><a href="${contextPath}/login">Sign In</a></li>
                            <li><a href="${contextPath}/registration">Sign Up</a></li>
                        </c:if>
                    </ul>
                    <a class="mobile-nav-icon js--nav-icon"><i class="ion-navicon-round"></i></a>
                </div>
            </nav>
            <div class="hero-text-box">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                        <h1>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h1>
                </c:when>
                    <c:otherwise>
                        <h1>Zderite pizze</h1>
                    </c:otherwise>
                </c:choose>

                <a class="btn btn-full js--scroll-to-plans" href="#">I'm hungry</a>
                <a class="btn btn-ghost js--scroll-to-start" href="#">Show me more</a>
            
            </div>
        </header>
</body>
    
</html>