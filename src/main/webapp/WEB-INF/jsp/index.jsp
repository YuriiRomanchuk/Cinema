<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
        <!-- left sidebar -->
    <%--        <jsp:include page="/WEB-INF/jsp/template/admin-bar.jsp"/>--%>

        <!-- center content -->
        <div class="col fluid bg-faded py-3">
            <div class="row my-3 offset-md-3">
                <div class="col-sm-4">
                        <%--<h1>Authorization</h1>
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
                            <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>
                        </form>--%>
                </div>

            </div>
        </div>
        </div>
    </jsp:body>
</t:genericpage>
