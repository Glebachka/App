<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>All Users</title>
        <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    </head>


<body>

    <h2>List of Users</h2>
    <table class="table table-striped">
    <tr>
        <td>Name</td>
        <td>SSN</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>

        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userName}</td>
            <td>${user.userSsn}</td>
            <td><a href="<c:url value='/edit-${user.userSsn}-user'/> ">${user.userSsn}</a></td>
            <td><a href="<c:url value='/delete-${user.userSsn}-user'/> ">Delete</a></td>
        </tr>



    </c:forEach>
</table>
<br>
<a href="<c:url value='/new' /> ">Add new User</a>
    <a href="<c:url value='/login' /> ">Login</a>



</body>
</html>
