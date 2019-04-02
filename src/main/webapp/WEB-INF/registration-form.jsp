<%--
  Created by IntelliJ IDEA.
  User: yromanchuk
  Date: 3/29/19
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <meta charset="UTF-8"/>
    <title>Registration form</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h1>Authorization</h1>
            <form method="post" action="${pageContext.request.contextPath}/WEB-INF/registration-form.jsp">
                <div class="form-group">
                    <label for="first_name">First name:</label>
                    <input type="text" class="form-control" id="first_name" name="first_name"
                           placeholder="Enter your first name">
                    <label for="second_name">Second name:</label>
                    <input type="text" class="form-control" id="second_name" name="second_name"
                           placeholder="Enter your second name">
                    <label for="middle_name">Middle_name:</label>
                    <input type="text" class="form-control" id="middle_name" name="middle_name"
                           placeholder="Enter your middle name">
                    <label for="login">Nickname:</label>
                    <input type="text" class="form-control" id="login" name="login"
                           placeholder="Enter your login">
                    <label for="password">Password:</label>
                    <input type="text" class="form-control" id="password" name="password"
                           placeholder="Enter your password">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control" id="email" name="email"
                           placeholder="Enter your email">
                    <label for="phone">Phone:</label>
                    <input type="text" class="form-control" id="phone" name="phone"
                           placeholder="Enter your phone">
                </div>
                <button type="submit" class="btn btn-primary">Apply</button>
                <a href="${pageContext.request.contextPath}/main/" class="btn btn-primary">Main</a>
            </form>
        </div>
    </div>
</div>

</body>
</html>
