<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/WebContent/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/WebContent/css/dashboard.css"/>"/>
    <meta charset="UTF-8"/>
    <title>Hello</title>
</head>

<body>

<jsp:include page="/WEB-INF/jsp/template/header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">Overview</a></li>
                <li><a href="#">Sales statistic</a></li>
                <li><a href="#">Users</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">Films catalog</a></li>
                <li><a href="admin-add-film" role="button"><fmt:message
                        key="local.add.film"/></a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="admin-add-room" role="button"><fmt:message
                        key="local.add.room"/></a></li>
                <li><a href="">Add rooms</a></li>
                <li><a href="">Edit rooms</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">Sessions</a></li>
                <li><a href="">Create Session</a></li>
            </ul>
        </div>
    </div>
</div>


</body>
</html>