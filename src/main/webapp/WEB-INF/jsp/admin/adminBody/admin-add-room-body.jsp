<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<fmt:setBundle basename="regexpValidator"/>

<!DOCTYPE html>

<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
</head>
<body>

<div class="col-6 col-sm-4">
    <c:set var='error' value="${Error}"/>
    <c:if test="${error !=null}">
        <h1>${error}"</h1>
    </c:if>
    <div class="w-100 d-none d-md-block"></div>
    <%--
        <div class="w-100 justify-content-center">
            <h1>Add room</h1>
        </div>--%>
</div>

<h1>Add room</h1>
<form id="form" method="post" action="admin-add-room" class="needs-validation" novalidate>
    <div class="form-group">
        <label for="name">Name:</label>
        <input required type="text" class="form-control" id="name" name="name"
               placeholder="Enter room's name" pattern="<fmt:message key="regexString"/>">
        <label for="name_english">Name(en):</label>
        <input required type="text" class="form-control" id="name_english" name="name_english"
               placeholder="Enter room's english name" pattern="<fmt:message key="regexStringEnglish"/>">
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
  <%--  <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>--%>
</form>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

</body>
</html>
