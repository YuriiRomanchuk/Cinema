<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="validator.min.js"></script>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<fmt:setBundle basename="regexpValidator"/>

<!DOCTYPE html>

<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
</head>

<div class="col-6 col-sm-4">
    <c:set var='error' value="${Error}"/>
    <c:if test="${error !=null}">
        <h1>${error}"</h1>
    </c:if>
    <div class="w-100 d-none d-md-block"></div>

</div>

<h1>Add film</h1>
<form id="form" method="post" action="admin-add-film" class="needs-validation" novalidate>
    <div class="form-group">
        <label for="name">Name:</label>
        <input required type="text" pattern="<fmt:message key="regexString"/>" class="form-control" id="name"
               name="name"
               placeholder="Enter film's name">
        <label for="name_english">Name(en):</label>
        <input required type="text" pattern="<fmt:message key="regexStringEnglish"/>" class="form-control"
               id="name_english" name="name_english"
               placeholder="Enter film's english name">
        <label for="release_date">Year:</label>
        <input required type="date" pattern="<fmt:message key="regexNumber"/>" class="form-control" id="release_date"
               name="release_date"
               placeholder="Enter release date">
        <label for="description">Description:</label>
        <input required type="text" pattern="<fmt:message key="regexString"/>" class="form-control" id="description"
               name="description"
               placeholder="Enter description">
        <label for="description_english">Description(en):</label>
        <input required type="text" pattern="<fmt:message key="regexStringEnglish"/>" class="form-control"
               id="description_english"
               name="description_english"
               placeholder="Enter english description">
        <label for="running_time">Running time:</label>
        <input required type="number" pattern="<fmt:message key="regexNumber"/>" class="form-control" id="running_time"
               name="running_time"
               placeholder="Enter running time"
               value=2 readonly>
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
    <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>
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
