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
<h1>Registration</h1>
<form method="post" action="registration-form">
    <div class="form-group">
        <label for="firstName">First name:</label>
        <input type="text" class="form-control" id="firstName" name="firstName"
               placeholder="Enter your first name">
        <label for="lastName">Last name:</label>
        <input type="text" class="form-control" id="lastName" name="lastName"
               placeholder="Enter your last name">
        <label for="middleName">Middle_name:</label>
        <input type="text" class="form-control" id="middleName" name="middleName"
               placeholder="Enter your middle name">
        <label for="login">Nickname:</label>
        <input type="text" class="form-control" id="login" name="login"
               placeholder="Enter your login">
        <label for="password">Password:</label>
        <input type="text" class="form-control" id="password" name="password"
               placeholder="Enter your password">
        <label for="email">Email:</label>
        <input type="text" class="form-control" id="email" name="email"
               placeholder="Enter your email">
        <label for="phone">Phone:</label>
        <input type="text" class="form-control" id="phone" name="phone"
               placeholder="Enter your phone">
    </div>
    <button type="submit" class="btn btn-primary">Apply</button>
    <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>
</form>

</body>
</html>
