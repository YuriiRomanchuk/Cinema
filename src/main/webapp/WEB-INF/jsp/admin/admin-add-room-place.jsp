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
            <jsp:include page="/WEB-INF/jsp/template/admin-bar.jsp"/>

            <!-- center content -->
            <div class="col fluid bg-faded py-3">
                <div class="row my-3 offset-md-3">
                    <div class="col-sm-4">
                        <jsp:include page="/WEB-INF/jsp/admin/adminBody/admin-add-room-place-body.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
