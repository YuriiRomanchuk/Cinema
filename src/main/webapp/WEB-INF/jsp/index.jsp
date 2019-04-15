<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Index
    </jsp:attribute>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/jsp/template/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="row h-100">
            <div class="col fluid bg-faded py-3">
                <div class="row my-3 offset-md-3 " style="
                        margin-left: 0px;">
               <div class="col">
                    <jsp:include page="/WEB-INF/jsp/user/userBody/user-session-body.jsp"/>
                </div>

            </div>
        </div>
        </div>
    </jsp:body>
</t:genericpage>
