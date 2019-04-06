<%@ page import="com.cinema.config.UserAuthorization" %>
<%@ page import="java.util.Map" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    HttpSession currentSession = request.getSession();
    <String, UserAuthorization> usersAuthorization = (Map<String, UserAuthorization>) currentSession.getServletContext().getAttribute("usersAuthorization");
    UserAuthorization userAuthorization = usersAuthorization.get(currentSession.getId());

    Role role = Role.UNKNOWN;
    String email;
    if (userAuthorization != null) {
        role = userAuthorization.getRole();
        email = userAuthorization.getEmail();
    }
%>

<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <div class="row">
            <div class="col-md text-center">
                <c:choose>
                    <p class="text-center">> ${sessionScope.user.firstNameEn} ${sessionScope.user.lastNameEn}</p>
                </c:choose>

                <a class="btn btn-info" href="${pageContext.request.contextPath}/controller?action=logout" role="button">></a>
                <form role="form" class="btn btn-info btn-sm" method="post"
                      action="${pageContext.request.contextPath}/controller?action=change_language">
                    <input type="hidden" name="language" value="en_US">
                    <button type="submit" class="btn btn-info btn-sm">></button>
                </form>
                <form role="form" class="btn btn-info btn-sm" method="post"
                      action="${pageContext.request.contextPath}/controller?action=change_language">
                    <input type="hidden" name="language" value="uk_UA">
                    <button type="submit" class="btn btn-info btn-sm">></button>
                </form>
            </div>
            <br/>
        </div>
    </c:when>

</c:choose>