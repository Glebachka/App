<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>All Users</title>
    <%--<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">--%>
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

<h2>List of Users</h2>
<table class="table table-striped">

    <td class="col-sm-1">Name</td>
    <td class="col-sm-1">Login</td>
    <c:choose>
        <c:when test="${user.userRoles == 'ROLE_ADMIN' || user.userRoles == 'ROLE_DBA'}">
            <td>Edit</td>
            <td>Delete</td>
        </c:when>
        <c:when test="${user.userRoles == 'ROLE_DBA'}">
            <td>role</td>
        </c:when>
    </c:choose>
    </tr>

    <c:forEach items="${users}" var="user">

        <tr>
            <td>${user.userName}</td>
            <td>${user.userLogin}</td>
            <c:choose>
                <c:when test="${user.userRoles == 'ROLE_ADMIN' || user.userRoles == 'ROLE_DBA'}">
                    <td><a href="<c:url value='/edit-${user.userLogin}-user'/> ">${user.userLogin}</a></td>
                    <td><a href="<c:url value='/delete-${user.userlogin}-user'/> ">Delete</a></td>
                </c:when>
                <c:when test="${user.userRoles == 'ROLE_DBA'}">
                    <td>${user.userRole}</td>
                </c:when>
            </c:choose>


        </tr>
    </c:forEach>

</table>
<br>
<a href="<c:url value='/new' /> ">Add new User</a>
<a href="<c:url value='/logout' /> ">LogOut</a>


</body>
</html>
