<%@ page import="com.cinema.model.dto.RoomDto" %>
<%@ page import="java.util.List" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Title</title>
</head>
<body>

<%
    String error = (String) request.getAttribute("Error");
    if (error != null) {
%> <h1><%=error%>
</h1> <%
    }
%>

<h1>Add room places</h1>
<form method="post" action="admin-add-room-place">

    <label for="row">Room:</label>
    <select class="custom-select mr-sm-2" id="room" name="room">
        <option selected>Choose...</option>

        <% List<RoomDto> rooms = (List<RoomDto>) request.getAttribute("roomsDto");
            for (RoomDto room : rooms) { %>
        <option value="<%=  room.getId()%>"><%=room.getName() %>
        </option>
        <%
            }
        %>
    </select>

    <div class="form-group">
        <label for="row"><fmt:message
                key="local.room.places.rows"/></label>
        <input type="number" class="form-control" id="row" name="row"
               placeholder="Enter count of row">
        <label for="place">Places:</label>
        <input type="number" class="form-control" id="place" name="place"
               placeholder="Enter count of places">
        <label for="placesInRow">Places in row:</label>
        <input type="number" class="form-control" id="placesInRow" name="placesInRow"
               placeholder="Enter count of places in row"
        value = 10>
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
    <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>
</form>

</body>
</html>
