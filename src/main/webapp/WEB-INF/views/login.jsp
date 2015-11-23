<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>App Login Page</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
          crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
          integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
            integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
            crossorigin="anonymous"></script>


</head>
<body>
<div class="container">
    <c:url var="loginUrl" value="/login"/>
    <form action="${loginUrl}" method="post" class="form-signin">

        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p>Invalid username and password.</p>
            </div>
        </c:if>

        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <p>You have been logged out successfully.</p>
            </div>
        </c:if>

        <div class="input-group input-sm">
            <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
            <input type="text" class="form-control" id="username" name="userLogin"
                   placeholder="Enter Username" required>
        </div>

        <div class="input-group input-sm">
            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
            <input type="password" class="form-control" id="password" name="password"
                   placeholder="Enter Password" required>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="form-actions">
            <input type="submit"
                   class="btn btn-block btn-primary btn-default" value="Log in">
        </div>
    </form>
</div>


</body>
</html>
