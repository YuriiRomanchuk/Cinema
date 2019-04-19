<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<fmt:setBundle basename="regexpValidator"/>

<!DOCTYPE html>

<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
</head>
<body>

<div class="col-6 col-sm-4">
    <c:set var='error' value="${Error}"/>
    <c:if test="${error !=null}">
        <h1>${error}</h1>
    </c:if>
    <div class="w-100 d-none d-md-block"></div>

    <%--  <div class="w-100 justify-content-center">
          <h1>Add room places</h1>
      </div>--%>
</div>

<h1>Add room places</h1>
<form id="form" method="post" action="admin-add-room-place" class="needs-validation" novalidate>
    <label for="row">Room:</label>
    <select class="custom-select mr-sm-2" id="room" name="room">
        <option selected>Choose...</option>
        <c:forEach var="room" items="${roomsDto}">
            <option value=${room.getId()}>${room.getName() }
            </option>
        </c:forEach>
    </select>

    <div class="form-group">
        <label for="row"><fmt:message key="local.room.places.rows"/></label>
        <input type="number" class="form-control" id="row" name="row"
               placeholder="Enter count of row" pattern="<fmt:message key="regexNumber"/>">
        <label for="place">Places:</label>
        <input type="number" class="form-control" id="place" name="place"
               placeholder="Enter count of places" pattern="<fmt:message key="regexNumber"/>">
        <label for="placesInRow">Places in row:</label>
        <input type="number" class="form-control" id="placesInRow" name="placesInRow"
               placeholder="Enter count of places in row"
               value=10 pattern="<fmt:message key="regexNumber"/>">
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
    <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>
</form>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

</body>
</html>
