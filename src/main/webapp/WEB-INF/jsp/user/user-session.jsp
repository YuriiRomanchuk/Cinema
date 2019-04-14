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
            <jsp:include page="/WEB-INF/jsp/template/user-bar.jsp"/>

            <!-- center content -->
            <div class="col fluid bg-faded py-3 h-100 justify-content-center">
                <div class="row my-3 justify-content-center">
                    <jsp:include page="/WEB-INF/jsp/user/userBody/user-session-body.jsp"/>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
