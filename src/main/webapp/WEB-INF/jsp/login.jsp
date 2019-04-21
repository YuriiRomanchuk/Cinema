<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<fmt:setBundle basename="regexpValidator"/>

<t:genericpage>
    <jsp:attribute name="title">
      Template Page
    </jsp:attribute>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/jsp/template/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="row h-100">
            <div class="col fluid bg-faded py-3">
                <div class="row w-100 justify-content-center">
                    <div class="col-sm-4">
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

                            <div class="w-100 justify-content-center">
                                <h1>Authorization</h1>
                            </div>
                        </div>

                        <form id="form" method="post" action="login" class="needs-validation" novalidate>
                            <div class="form-group">
                                <label for="email">Name:</label>
                                <input required="email" pattern="<fmt:message key="regexEmail"/>" class="form-control" id="email" name="email"
                                       placeholder="Enter your email">
                                <label for="password">Password:</label>
                                <input required type="password" pattern="<fmt:message key="regexStringNumber"/>" class="form-control"
                                       id="password" name="password"
                                       placeholder="Enter your password">
                            </div>
                            <button type="submit" class="btn btn-primary">Log in</button>
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

                            $(function () {
                                window.setTimeout(function () {
                                    $('#my-alert').alert('close');
                                }, 20000);
                            });
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>

