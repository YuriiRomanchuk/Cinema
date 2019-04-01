<%--
  Created by IntelliJ IDEA.
  User: yromanchuk
  Date: 3/29/19
  Time: 10:55 AM
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
    <title>Hello</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h1>Authorization</h1>
            <form method="post" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                    <label for="nickname">Name:</label>
                    <input type="text" class="form-control" id="nickname" name="nickname"
                           placeholder="Enter your nickname">
                    <label for="password">Name:</label>
                    <input type="text" class="form-control" id="password" name="password"
                           placeholder="Enter your password">
                </div>
                <button type="submit" class="btn btn-primary">"Log in"</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
