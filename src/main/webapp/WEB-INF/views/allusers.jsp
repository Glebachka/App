<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sport is our life</title>
        <style>
            tr:first-child{
                font-weight: bold;
                background-color: #C6C9C4;
            }
        </style>

    </head>


<body>

    <h2>List of Users</h2>
<table>
    <tr>
        <td>Name</td><td>SSN</td><td></td>
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



</body>
</html>
