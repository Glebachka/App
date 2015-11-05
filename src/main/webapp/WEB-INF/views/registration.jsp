<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gleb
  Date: 03.11.2015
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration form</title>
  <style>
    .error{
      color : #ff0000;
    }
  </style>
</head>
<body>

<h2>Registration form</h2>
<form:form method="POST" modelAttribute="user">
  <form:input type="hidden" path="userId" id="userId"/>
  <table>

    <tr>
      <td><label for="userName">Name</label></td>
      <td><form:input path="userName" id="userName"/></td>
      <td><form:errors path="userName" cssClass="error"/></td>
    </tr>

    <tr>
      <td><label for="userSsn">SSN: </label> </td>
      <td><form:input path="userSsn" id="userSsn"/></td>
      <td><form:errors path="userSsn" cssClass="error"/></td>
    </tr>

    <tr>
      <td colspan="3">
        <c:choose>
          <c:when test="${edit}">
            <input type="submit" value="Update"/>
          </c:when>
          <c:otherwise>
            <input type="submit" value="Register"/>
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
