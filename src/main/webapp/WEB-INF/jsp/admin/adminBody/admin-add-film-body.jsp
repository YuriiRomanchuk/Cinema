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

<%
    String error = (String) request.getAttribute("Error");
    if (error != null) {
%> <h1><%=error%>
</h1> <%
    }
%>

<h1>Add film</h1>
<form method="post" action="admin-add-film">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" name="name"
               placeholder="Enter film's name">
        <label for="nameEnglish">Name(en):</label>
        <input type="text" class="form-control" id="nameEnglish" name="nameEnglish"
               placeholder="Enter film's english name">
        <label for="release_date">Year:</label>
        <input type="date" class="form-control" id="release_date" name="release_date"
               placeholder="Enter release date">
        <label for="description">Description:</label>
        <input type="text" class="form-control" id="description" name="description"
               placeholder="Enter description">
        <label for="descriptionEnglish">Description(en):</label>
        <input type="text" class="form-control" id="descriptionEnglish"
               name="descriptionEnglish"
               placeholder="Enter english description">
        <label for="runningTime">Running time:</label>
        <input type="text" class="form-control" id="runningTime"
               name="descriptionEnglish"
               placeholder="Enter running time"
               value=2 readonly>
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
    <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>
</form>

</body>
</html>