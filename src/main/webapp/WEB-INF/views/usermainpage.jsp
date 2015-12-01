<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: gleb
  Date: 27.11.2015
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<input type="hidden" id="currentUserRole" value="${currentUserRole}"/>
<input type="hidden" id="currentUser" value="${currentUser}"/>

<table class="table table-striped">
    <tr>
        <td class="col-sm-1">Link</td>
        <td class="col-sm-1">Description</td>
    </tr>
    <div>
        <c:forEach items="${links}" var="link">
            <tr>
                <td>${link.link}</td>
                <td>${link.linkDescription}</td>

                <td>
                    <a href="<c:url value='/${currentUserRole}/edit-${link.linkId}'/> ">${link.link}</a>
                </td>
                <td><a href="<c:url value='/${currentUserRole}/delete-${link.linkId}'/> ">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </div>
</table>

<div id="inputLink">
    <form:form method="POST" modelAttribute="link" commandName="newLink" action="addLink">

        <form:input type="hidden" path="linkId" id="linkId"/>

        <form:input type="hidden" path="user" id="${currentUser}"/>

        <label for="link">Add link:</label>
        <form:input type="text" path="link" id="link"/>

        <label for="linkDescription">Link description:</label>
        <form:input type="text" path="linkDescription" id="linkDescription"/>

        <input type="submit" class="btn btn-default" value="New Link"/>
    </form:form>
</div>
</body>
</html>
