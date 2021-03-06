<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>


<t:genericpage>
    <jsp:attribute name="title">
        Admin session room
    </jsp:attribute>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/jsp/template/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="row h-100">
            <!-- center content -->
            <div class="col fluid bg-faded py-3 h-100 justify-content-center">
                <div class="row my-3 justify-content-center">
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
                            <h1>Session room</h1>
                        </div>
                    </div>

                    <div class="w-100 d-none d-md-block"></div>

                    <div class="form-group field-middle_name row w-100">
                        <div class="col-sm-2 col-md-2 col-xs-2 no-padding">
                            <label for="session_id">id:</label>
                            <input required type="text" class="form-control" id="session_id" name="session_id" size="1"
                                   readonly
                                   value=${filmSessionDto.getId()}>
                        </div>
                        <div class="col-sm-2 col-md-2 col-xs-2 no-padding">
                            <label for="session_film">film id:</label>
                            <input required type="text" class="form-control" id="session_film" name="session_film" size="1"
                                   readonly
                                   value=${filmSessionDto.getFilmDto().getId()}>
                        </div>
                        <div class="col-sm-3 col-md-3 col-xs-3 no-padding">
                            <label for="session_room">room id:</label>
                            <input required type="text" class="form-control" id="session_room" name="session_room" size="1"
                                   readonly
                                   value=${filmSessionDto.getRoomDto().getId()}>
                        </div>
                        <div class="col-sm-5 col-md-5 col-xs-5 no-padding">
                            <label for="session_date">date:</label>
                            <input required type="text" class="form-control" id="session_date" name="session_date" size="2"
                                   readonly
                                   value="${sessionDate}">
                        </div>
                    </div>
                    <div class="w-100 d-none d-md-block"></div>
                    <form method="post" action="session">
                        <table class="table table-border-collapse">
                            <c:set var="countForColumns">1</c:set>
                            <thead>
                            <tr>
                                <th>Row</th>
                                <c:forEach var="roomPlace" items="${roomPlacesDto}">
                                <c:if test="${roomPlace.getRow() == countForColumns}">
                                <th></th>
                                </c:if>
                                </c:forEach>
                            </thead>

                            <c:set var="firstEntry">true</c:set>
                            <c:set var="placeIsBusy">false</c:set>
                            <c:forEach var="roomPlace" items="${roomPlacesDto}">

                            <c:forEach var="purchasedPlace" items="${purchasedSessionTicketsDto}">
                                <c:if test="${roomPlace.getPlace()==purchasedPlace.getPlace()}">
                                    <c:set var="placeIsBusy">true</c:set>
                                </c:if>
                            </c:forEach>

                            <c:if test="${roomPlace.getRow() != countForColumns}">
                                </tr>
                                <c:set var="firstEntry">true</c:set>
                                <c:set var="countForColumns">${roomPlace.getRow()}</c:set>
                            </c:if>

                            <c:if test="${firstEntry == true}">
                            <tr>
                                <td>${countForColumns}</td>
                                <c:set var="firstEntry">false</c:set>
                                </c:if>
                                <td>
                                    <button disabled onclick="form.action='session-place';" type="submit" name="session-place"
                                            value="${roomPlace.getPlace()}"
                                            <c:choose>
                                                <c:when test="${placeIsBusy}">
                                                    class= "btn btn-danger">
                                                    <c:set var="placeIsBusy">false</c:set>
                                                </c:when>
                                                <c:otherwise>
                                                    class= "btn btn-primary">
                                                </c:otherwise>
                                            </c:choose>

                                            <c:choose>
                                                <c:when test="${roomPlace.getPlace()< 10}">
                                                    0${roomPlace.getPlace()}
                                                </c:when>
                                                <c:otherwise>
                                                    ${roomPlace.getPlace()}
                                                </c:otherwise>
                                            </c:choose>
                                    </button>
                                </td>
                                </c:forEach>
                        </table>
                    </form>

                    <script>
                        $(function () {
                            window.setTimeout(function () {
                                $('#my-alert').alert('close');
                            }, 20000);
                        });
                    </script>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
