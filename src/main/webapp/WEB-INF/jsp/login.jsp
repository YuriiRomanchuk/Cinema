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

<%--<!-- HEADER -->
<jsp:include page="/WEB-INF/template/header.jsp"/>--%>


<%
    String error = (String) request.getAttribute("Error");
    if (error != null) {
%> <h1><%=error%></h1> <%
    }
%>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h1>Authorization</h1>
            <form method="post" action="login">
                <div class="form-group">
                    <label for="email">Name:</label>
                    <input type="text" class="form-control" id="email" name="email"
                           placeholder="Enter your email">
                    <label for="password">Password:</label>
                    <input type="text" class="form-control" id="password" name="password"
                           placeholder="Enter your password">
                </div>
                <button type="submit" class="btn btn-primary">Log in</button>
                <a href="${pageContext.request.contextPath}/main/" class="btn btn-primary">Main</a>
            </form>
        </div>
    </div>
</div>

</body>
</html>