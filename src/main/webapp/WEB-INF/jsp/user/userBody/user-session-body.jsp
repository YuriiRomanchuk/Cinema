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
<c:set var='filmSessionDto' value="${filmSessionDto}"/>
<c:set var='currentFilm_id' value="${filterFilmId}"/>

<div class="col-6 col-sm-4">
    <c:set var='error' value="${Error}"/>
    <c:if test="${error !=null}">
        <h1>${error}"</h1>
    </c:if>
    <div class="w-100 d-none d-md-block"></div>

    <div class="w-100 justify-content-center">
        <h1>Film session</h1>
    </div>
</div>
<div class="w-100 d-none d-md-block"></div>

<form method="post" action="user-session">
    <div class="form-group field-middle_name row">
        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="film_filter">Film:</label>
            <select name="film_filter" id="film_filter" class="form-control" title="Film" required="required">
                <option selected>Choose...</option>
                <c:forEach var="film" items="${filmsDto}">
                    <c:choose>
                        <c:when test="${ (currentFilm_id !=null && film.getId() == currentFilm_id)}">
                            <option selected="selected" value=${film.getId()}>${film.getName()}</option>
                        </c:when>
                        <c:otherwise>
                            <option value=${film.getId()}>${film.getName() }
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="date_filter">Session date:</label>
            <input required type="date" class="form-control" id="date_filter" name="date_filter"
                   placeholder="Enter session date"
                   value=${filterDate}>
        </div>
    </div>
    <div class="help-block row"></div>

    <button type="submit" class="btn btn-primary my-sm-2">Find</button>

    <table class="table table-sm table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Date</th>
            <th>Room</th>
            <th>Film</th>
            <th>Action</th>
        </thead>
        <c:forEach var="filmSessionDto" items="${filmsSessionDto}" varStatus="loop">
            <tr>
                <td>
                    <input type="text" class="form-control" id="session_id_${loop.index}"
                           name="session_id_${loop.index}" size="1"
                           readonly
                           value=" ${filmSessionDto.getId()}">
                </td>
                <td>
                    <input type="text" class="form-control" id="session_date_${loop.index}"
                           name="session_date_${loop.index}"
                           placeholder="Enter film's name" readonly
                           value="${filmSessionDto.getDate()}">
                </td>
                <td>
                    <select class="custom-select mr-sm-2" id="session_room_${loop.index}"
                            name="session_room_${loop.index}">
                        <c:forEach var="room" items="${roomsDto}">
                            <option selected="selected" value=${room.getId()}>${room.getName()}
                            </option>
                        </c:forEach>
                    </select
                </td>
                <td>
                    <select class="custom-select mr-sm-2" id="session_film_${loop.index}"
                            name="session_film_${loop.index}">
                        <c:if test="${filmSessionDto.getFilmDto() !=null}">
                            <option selected="selected"
                                    value=${filmSessionDto.getFilmDto().getId()}>${filmSessionDto.getFilmDto().getName()}
                            </option>
                        </c:if>
                    </select>
                </td>

                <td>
                    <div class="form-group field-middle_name row mr-2">
                        <c:if test="${filmSessionDto.getId() > 0 and !isLastDay}">
                            <button onclick="form.action='show-user-session';" type="submit" name="show-user-session"
                                    value="${loop.index}"
                                    class="btn btn-success ml-3">
                                buy
                            </button>
                        </c:if>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
