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
    <link rel="stylesheet" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <title>Title</title>
</head>

<body>

<div class="row w-100 justify-content-center">
    <div class="col-6 col-sm-4">
        <c:set var='error' value="${Error}"/>
        <c:if test="${error !=null}">
            <h1>${error}</h1>
        </c:if>
        <div class="w-100 d-none d-md-block"></div>

        <div class="w-100 justify-content-center">
            <h1>All films</h1>
        </div>
    </div>
</div>
<table id = "filmsTable" class="table table-sm table-striped">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Released</th>
        <th>Running time(h)</th>
        <th>Description</th>
    </thead>

    <c:forEach var="film" items="${filmsDto}">
        <tr>
            <td>${film.getId()}</td>
            <td>${film.getName()}</td>
            <td>${film.getReleaseDate()}</td>
            <td>${film.getRunningTime()}</td>
            <td>${film.getDescription()}</td>
        </tr>
    </c:forEach>
</table>

<script>
    $(document).ready(function () {
        $('#filmsTable').DataTable();
    });
</script>

</body>
</html>
