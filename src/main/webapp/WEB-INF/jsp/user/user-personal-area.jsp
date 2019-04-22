<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      User personal area
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
            <div class="col fluid bg-faded py-3">
                <div class="row w-100">
                    <div class="col">
                        <jsp:include page="/WEB-INF/jsp/user/userBody/user-buy-history-body.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
