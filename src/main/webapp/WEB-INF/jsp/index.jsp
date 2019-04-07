<%@ page import="com.cinema.config.UserAuthorization" %>
<%@ page import="com.cinema.model.entity.enums.Role" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/WebContent/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/WebContent/css/dashboard.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <meta charset="UTF-8"/>
    <title>Hello</title>
</head>

<%
    HttpSession currentSession = request.getSession();
    Map<String, UserAuthorization> usersAuthorization = (Map<String, UserAuthorization>) currentSession.getServletContext().getAttribute("usersAuthorization");
    UserAuthorization userAuthorization = usersAuthorization.get(currentSession.getId());

    Role role = Role.UNKNOWN;
    if (userAuthorization != null) {
        role = userAuthorization.getRole();
    }
%>

<body>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <form class="form" role="form">
                <h3>Hello!</h3>

                <div style="overflow: hidden;">
                    <div style="width: 1000%;">
                        <div style="float: left; width: 200px; height: 200px;">
                            <li>Administration
                                <ul>
                                    <%
                                        if (role == null || role.equals(Role.UNKNOWN)) {
                                    %>
                                    <li><a href="registration-form">Registration</a></li>
                                    <li><a href="login">Login</a></li>
                                    <%
                                    } else {
                                        if (role.equals(Role.ADMIN)) {
                                    %>
                                    <li><a href="admin-personal-area">Personal area</a></li>
                                    <% } else { %>
                                    <li><a href="user-personal-area">Personal area</a></li>
                                    <% }%>
                                    <li><a href="logout">Logout</a></li>
                                    <% }
                                    %>
                                </ul>
                            </li>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>
