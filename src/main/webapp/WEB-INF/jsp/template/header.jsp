<%@ page import="com.cinema.config.UserAuthorization" %>
<%@ page import="com.cinema.model.entity.enums.Role" %>
<%@ page import="java.util.Map" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<%
    HttpSession currentSession = request.getSession();
    Map<String, UserAuthorization> usersAuthorization = (Map<String, UserAuthorization>) currentSession.getServletContext().getAttribute("usersAuthorization");
    UserAuthorization userAuthorization = usersAuthorization.get(currentSession.getId());

    Role role = Role.UNKNOWN;
    if (userAuthorization != null) {
        role = userAuthorization.getRole();
    }
%>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/WebContent/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/WebContent/css/dashboard.css"/>"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style id="holderjs-style" type="text/css"></style>
</head>

<body style="">

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid" style=" display: flex">
        <a class="navbar-brand" href="#">Cinema</a>
        <ul class="nav navbar-nav navbar-center">
            <li>
                <form role="form" method="post"
                      action="change_language">
                    <input type="hidden" name="language" value="en">
                    <button type="submit"><fmt:message
                            key="local.lang.eng"/></button>
                </form>
            </li>
            <li>
                <form role="form" method="post"
                      action="change_language">
                    <input type="hidden" name="language" value="uk">
                    <button type="submit"><fmt:message
                            key="local.lang.ukr"/></button>
                </form>
            </li>
        </ul>

        <%
            if (role == null || role.equals(Role.UNKNOWN)) {
        %>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="registration-form" role="button"><fmt:message
                    key="local.registration"/></a></li>
            <li><a href="login" role="button"><fmt:message key="local.log.in"/></a></li>
        </ul>
        <%
        } else {
        %>
        <ul class="nav navbar-nav navbar-rightLogout">
            <li><a href="logout" role="button"><fmt:message key="local.logout"/></a></li>
        </ul>
        <%
            }
        %>

    </div>
</div>

</body>
</html>

