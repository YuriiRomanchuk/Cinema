<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:setBundle basename="messages" var="messages"/>
<!DOCTYPE html>

<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
</head>
<body>

<c:set var='error' value="${Error}"/>
<div class="col w-100">

    <c:if test="${error !=null}">
        <div id="my-alert" class="alert alert-danger alert-dismissible fade show w-100" role="alert">
                ${error}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="w-100 d-none d-md-block"></div>
    </c:if>
</div>

<h1><fmt:message key="local.admin.add.room" bundle="${messages}"/></h1>
<form id="form" method="post" action="admin-add-room" class="needs-validation" novalidate>
    <div class="form-group">
        <label for="name"><fmt:message key="local.admin.add.room.name" bundle="${messages}"/></label>
        <input required type="text" class="form-control" id="name" name="name"
               placeholder="<fmt:message key="local.admin.add.room.name.placeholder" bundle="${messages}"/>"
               pattern="<fmt:message key="regexStringNumber" bundle="${regexpValidator}"/>">
        <label for="name_english"><fmt:message key="local.admin.add.room.name.english" bundle="${messages}"/></label>
        <input required type="text" class="form-control" id="name_english" name="name_english"
               placeholder="<fmt:message key="local.admin.add.room.name.english.placeholder" bundle="${messages}"/>"
               pattern="<fmt:message key="regexStringNumberEnglish" bundle="${regexpValidator}"/>">
    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="local.admin.add.room.button.add"
                                                               bundle="${messages}"/></button>
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

    $(function () {
        window.setTimeout(function () {
            $('#my-alert').alert('close');
        }, 20000);
    });
</script>

</body>
</html>
