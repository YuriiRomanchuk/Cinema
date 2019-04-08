<%@ page import="com.cinema.config.UserAuthorization" %>
<%@ page import="com.cinema.model.entity.enums.Role" %>
<%@ page import="java.util.Map" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession currentSession = request.getSession();
    Map<String, UserAuthorization> usersAuthorization = (Map<String, UserAuthorization>) currentSession.getServletContext().getAttribute("usersAuthorization");
    UserAuthorization userAuthorization = usersAuthorization.get(currentSession.getId());

    Role role = Role.UNKNOWN;
    if (userAuthorization != null) {
        role = userAuthorization.getRole();
    }
%>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/WebContent/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/WebContent/css/dashboard.css"/>"/>
    <meta charset="UTF-8"/>
    <title>Hello</title>
</head>

<body>

<jsp:include page="/WEB-INF/jsp/template/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/template/admin-bar.jsp"/>

<div class="container h-100" >
    <div class="row h-100 justify-content-center">
        <div class="col-sm-4">

            <%
                String error = (String) request.getAttribute("Error");
                if (error != null) {
            %> <h1><%=error%>
        </h1> <%
            }
        %>
            <h1>Add film</h1>
            <form method="post" action="admin-add-room">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" name="name"
                           placeholder="Enter film's name">
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
                <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>
            </form>
        </div>
    </div>
</div>


</body>
</html>