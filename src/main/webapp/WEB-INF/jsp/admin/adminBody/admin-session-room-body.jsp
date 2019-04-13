<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>

<html lang="${sessionScope.lang}">

<head>
    <title>Title</title>
</head>

<body>
<div class="w-100 justify-content-center">
    <h1>Session room</h1>
</div>

<form method="post" action="admin-session">
<table class="table table-border-collapse">
     <c:set var="countForColumns">1</c:set>
    <thead>
    <tr>
        <th>Row</th>
        <c:forEach var="roomPlace" items="${roomPlacesDto}">
        <c:if test="${roomPlace.getRow() == countForColumns}">
        <th> </th>
        </c:if>
        </c:forEach>
    </thead>

    <c:set var="firstEntry">true</c:set>
    <c:forEach var="roomPlace" items="${roomPlacesDto}">

        <c:if test="${roomPlace.getRow() != countForColumns}">
            </tr>
            <c:set var="firstEntry">true</c:set>
            <c:set var="countForColumns">${roomPlace.getRow()}</c:set>
        </c:if>

        <c:if test="${firstEntry == true}">
             <tr>
             <td>${countForColumns}</td>
            <c:set var="firstEntry">false</c:set>
        </c:if>
            <td> <button onclick="form.action='session-place';" type="submit" name="session-place"
                value="${roomPlace.getPlace()}"
                class="btn btn-primary">
                <c:choose>
                    <c:when test="${roomPlace.getPlace()< 10}">
                        0${roomPlace.getPlace()}
                    </c:when>
                    <c:otherwise>
                        ${roomPlace.getPlace()}
                    </c:otherwise>
                </c:choose>
        </button></td>
    </c:forEach>
</table>
</form>
</body>
</html>

