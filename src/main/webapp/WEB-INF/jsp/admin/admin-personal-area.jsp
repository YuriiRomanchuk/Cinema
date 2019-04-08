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

<%--<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h1>Admin personal area</h1>
            <form method="post" action="admin-personal-area">
                &lt;%&ndash;    <%
                        Role role = (Role) request.getSession().getAttribute("role");
                        if (role == null || role.equals(Role.UNKNOWN)){
                    %> <button type="submit" class="btn btn-primary">Log in</button> <%
                    }
                    %>&ndash;%&gt;
                <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>
            </form>
        </div>
    </div>
</div>--%>


</body>
</html>