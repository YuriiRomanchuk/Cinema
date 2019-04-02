<%@ page import="com.cinema.model.entity.enums.Role" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<f:view>
    <h:outputLabel value="Hello, world"/>
</f:view>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <meta charset="UTF-8"/>
        <title>Login in system</title>
    </head>
<body>


<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h1>Admin personal area</h1>
            <form method="post" action="admin-personal-area">
            <%--    <%
                    Role role = (Role) request.getSession().getAttribute("role");
                    if (role == null || role.equals(Role.UNKNOWN)){
                %> <button type="submit" class="btn btn-primary">Log in</button> <%
                }
                %>--%>
                <a href="${pageContext.request.contextPath}/main/" class="btn btn-primary">Main</a>
            </form>
        </div>
    </div>
</div>

</body>
</html>