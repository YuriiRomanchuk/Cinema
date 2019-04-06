<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <meta charset="UTF-8"/>
    <title>Login in system</title>
</head>
<%--<%
    HttpSession currentSession = request.getSession();
    <String, UserAuthorization> usersAuthorization = (Map<String, UserAuthorization>) currentSession.getServletContext().getAttribute("usersAuthorization");
    UserAuthorization userAuthorization = usersAuthorization.get(currentSession.getId());

    Role role = Role.UNKNOWN;
    String email;
    if (userAuthorization != null) {
        role = userAuthorization.getRole();
        email = userAuthorization.getEmail();
    }
%>--%>


<div class="container">
    <div class="row">
        <div class="col-md text-right">
            <div class="menu-bar">
                <a class="btn btn-info" href="registration-form" role="button"><fmt:message
                        key="local.registration"/></a>
                <a class="btn btn-info" href="login" role="button"><fmt:message key="local.log.in"/></a>

                <div class="col-md text-left">
                    <a class="btn btn-info" href="index" role="button"><fmt:message key="local.main"/></a>
                </div>
            </div>

            <div class="col-md text-center">
                <form role="form" class="btn btn-info btn-sm" method="post"
                      action="change_language">
                    <input type="hidden" name="language" value="en">
                    <button type="submit" class="btn btn-info btn-sm"><fmt:message
                            key="local.lang.eng"/></button>
                </form>
                <form role="form" class="btn btn-info btn-sm" method="post"
                      action="change_language">
                    <input type="hidden" name="language" value="ua">
                    <button type="submit" class="btn btn-info btn-sm"><fmt:message
                            key="local.lang.ukr"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
</html>



