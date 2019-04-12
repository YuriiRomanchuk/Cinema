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

<html lang="en">
<head>
    <title>Title</title>
</head>

<body>
<c:set var='filmSessionDto' value="${filmSessionDto}"/>

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

<form method="post" action="admin-session">
    <div class="form-group field-middle_name row">
        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="film_filter">Film:</label>
            <select name="film_filter" id="film_filter" class="form-control" title="Film" required="required">
                <option selected>Choose...</option>
                <c:forEach var="film" items="${filmsDto}">
                    <option value=${film.getId()}>${film.getName() }
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="date_filter">Session date:</label>
            <input type="date" class="form-control" id="date_filter" name="date_filter"
                   placeholder="Enter release date"
                   value=${currentDate}>
        </div>
    </div>
    <div class="help-block row"></div>

    <button type="submit" class="btn btn-primary">Find</button>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Date</th>
            <th>Room</th>
            <th>Film</th>
            <th>Action</th>
        </thead>
        <% int counter = 1;%>
        <c:forEach var="filmSessionDto" items="${filmsSessionDto}">
            <tr>
                <td>
                        ${filmSessionDto.getId()}
                </td>
                <td>
                    <input type="text" class="form-control" id="session_date_<%=counter%>" name="session_date"
                           placeholder="Enter film's name" readonly
                           value="${filmSessionDto.getDate()}">
                </td>
                <td>
                    <select class="custom-select mr-sm-2" id="session_room_ <%=counter%>" name="room">
                        <option selected>Choose...</option>
                        <c:forEach var="room" items="${roomsDto}">
                            <option selected="selected" value=${room.getId()}>${room.getName()}
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select class="custom-select mr-sm-2" id="session_film_<%=counter%>" name="film">
                        <option selected>Choose...</option>

                        <c:forEach var="film" items="${filmsDto}">
                            <option value=${film.getId()}>${film.getName()}
                            </option>
                        </c:forEach>

                        <c:if test="${filmSessionDto.getFilmDto() !=null}">
                            <option selected="selected"
                                    value=${filmSessionDto.getFilmDto().getId()}>${filmSessionDto.getFilmDto().getName()}
                            </option>
                        </c:if>
                    </select>
                </td>

                <td>
                    <button type="submit" name="show-session" value="<%=counter%>"
                            class="btn btn-primary">
                        show
                    </button>
                    <button type="submit" name="add-session" value="<%=counter%>"
                            class="btn btn-primary">
                        add
                    </button>
                    <button type="submit" name="delete-session" value="<%=counter%>"
                            class="btn btn-primary">
                        delete
                    </button>
                </td>
            </tr>

            <%counter++;%>
        </c:forEach>
    </table
</form>
</body>
</html>
