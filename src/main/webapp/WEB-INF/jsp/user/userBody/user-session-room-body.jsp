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
<div class="col-6 col-sm-4">
    <div class="w-100 justify-content-center">
        <h1>Session room</h1>
    </div>
</div>
<div class="w-100 d-none d-md-block"></div>

<div class="form-group field-middle_name row w-100">
    <div class="col-sm-2 col-md-2 col-xs-2 no-padding">
        <label for="session_id">id:</label>
        <input required type="text" class="form-control" id="session_id" name="session_id" size="1"
               readonly
               value=${filmSessionDto.getId()}>
    </div>
    <div class="col-sm-2 col-md-2 col-xs-2 no-padding">
        <label for="session_film">film id:</label>
        <input required type="text" class="form-control" id="session_film" name="session_film" size="1"
               readonly
               value=${filmSessionDto.getFilmDto().getId()}>
    </div>
    <div class="col-sm-3 col-md-3 col-xs-3 no-padding">
        <label for="session_room">room id:</label>
        <input required type="text" class="form-control" id="session_room" name="session_room" size="1"
               readonly
               value=${filmSessionDto.getRoomDto().getId()}>
    </div>
    <div class="col-sm-5 col-md-5 col-xs-5 no-padding">
        <label for="session_date">date:</label>
        <input required type="text" class="form-control" id="session_date" name="session_date"
               readonly
               value="${sessionDate}">
    </div>
</div>
<div class="w-100 d-none d-md-block"></div>

<form method="post" action="user-session">
    <table class="table table-border-collapse">
        <c:set var="countForColumns">1</c:set>
        <thead>
        <tr>
            <th>Row</th>
            <c:forEach var="roomPlace" items="${roomPlacesDto}">
            <c:if test="${roomPlace.getRow() == countForColumns}">
            <th></th>
            </c:if>
            </c:forEach>
        </thead>

        <c:set var="firstEntry">true</c:set>
        <c:set var="placeIsBusy">false</c:set>
        <c:forEach var="roomPlace" items="${roomPlacesDto}">

        <c:forEach var="purchasedPlace" items="${purchasedSessionTicketsDto}">
            <c:if test="${roomPlace.getPlace()==purchasedPlace.getPlace()}">
                <c:set var="placeIsBusy">true</c:set>
            </c:if>
        </c:forEach>

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
            <td>
                <c:choose>
                <c:when test="${placeIsBusy}">
                    <button disabled onclick="form.action='buy-place';" type="submit" name="buy-place"
                </c:when>
                <c:otherwise>
                     <button onclick="form.action='buy-place';" type="submit" name="buy-place"
                </c:otherwise>
                </c:choose>
                    <%--   <button disabled onclick="form.action='buy-place';" type="submit" name="session-place"--%>
                        value="${roomPlace.getId()}_${roomPlace.getPlace()}_${roomPlace.getRow()}_${filmSessionDto.getId()}_${filmSessionDto.getFilmDto().getId()}_${filmSessionDto.getRoomDto().getId()}_${sessionDate}"
                        <c:choose>
                            <c:when test="${placeIsBusy}">
                                class= "btn btn-danger">
                                <c:set var="placeIsBusy">false</c:set>
                            </c:when>
                            <c:otherwise>
                                class= "btn btn-primary">
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${roomPlace.getPlace()< 10}">
                                0${roomPlace.getPlace()}
                            </c:when>
                            <c:otherwise>
                                ${roomPlace.getPlace()}
                            </c:otherwise>
                        </c:choose>
                </button>
            </td>
            </c:forEach>
    </table>
</form>
</body>
</html>

