<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      Admin personal area
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
                        <div class="row w-100 justify-content-center">
                            <div class="col-6 col-sm-4">
                                <c:if test="${error !=null}">
                                    <div id="my-alert" class="alert alert-danger alert-dismissible fade show w-100"
                                         role="alert">
                                            ${error}
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="w-100 d-none d-md-block"></div>
                                </c:if>

                                <div class="w-100 justify-content-center">
                                    <h1>Films sale</h1>
                                </div>
                            </div>
                        </div>

                   <%--     <form method="post" action="admin-personal-area">
                            <div class="form-group field-middle_name row">
                                <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
                                    <label for="date_filter">Date:</label>
                                    <input required type="date" class="form-control" id="date_filter" name="date_filter"
                                           placeholder="Enter session date"
                                           value=${filterDate}>
                                </div>
                            </div>
                            <div class="help-block row"></div>
                            <button type="submit" class="btn btn-primary my-sm-2">Show</button>
                        </form>--%>

                        <table id="filmsTable" class="table table-sm table-striped">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Sale</th>
                            </thead>

                            <c:forEach var="filmSale" items="${filmSaleDto}">
                                <tr>
                                    <td>${filmSale.getId()}</td>
                                    <td>${filmSale.getName()}</td>
                                    <td>${filmSale.getReleaseDate()}</td>
                                </tr>
                            </c:forEach>
                        </table>

                        <script>
                            $(document).ready(function () {
                                $('#filmsTable').DataTable();
                            });

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
