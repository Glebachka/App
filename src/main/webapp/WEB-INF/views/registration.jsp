<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration form</title>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>

<h2>Registration form</h2>
<form:form method="POST" modelAttribute="user">
    <form:input type="hidden" path="userId" id="userId"/>
    <table>

        <tr>
            <td><label for="userName">Name: </label></td>
            <td><form:input path="userName" id="userName"/></td>
            <td><form:errors path="userName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="userLogin">Login: </label></td>
            <td><form:input path="userLogin" id="userLogin"/></td>
            <td><form:errors path="userLogin" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="userPassword">Password: </label></td>
            <td><form:input path="userPassword" id="userPassword"/></td>
            <td><form:errors path="userPassword" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="userRoles">Role: </label></td>
            <td><form:select path="userRoles" items="${roles}" multiple="true" itemValue="userRoleId" itemLabel="role"
                             id="userRoleJSPId"/></td>
            <td><form:errors path="userRoles" cssClass="error"/></td>
        </tr>

        <br>
        <tr>
            <td colspan="3">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" class="btn btn-default" value="Update"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" class="btn btn-default" value="Register"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Go back to <a href="<c:url value='/list' />">List of All Employees</a>
</body>
</html>
