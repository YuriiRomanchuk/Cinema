<%@ page import="com.cinema.model.dto.FilmDto" %>
<%@ page import="java.util.List" %>
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
<%
    String error = (String) request.getAttribute("Error");
    if (error != null) {
%> <h1><%=error%>
</h1> <%
    }
%>

<h1>All films</h1>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Released</th>
        <th>Running time(h)</th>
        <th>Description</th>
    </thead>
    <%
        List<FilmDto> films = (List<FilmDto>) request.getAttribute("filmsDto");
        for (FilmDto film : films) { %>
    <tr>
        <td>
            <%=film.getId()%>
        </td>
        <td>
            <%=film.getName()%>
        </td>
        <td>
            <%=film.getReleaseDate()%>
        </td>
        <td>
            <%=film.getRunningTime()%>
        </td>
        <td>
            <%=film.getDescription()%>
        </td>
    </tr>

    <%} %>
</table>

</body>
</html>
