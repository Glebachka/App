<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: gleb
  Date: 02.12.2015
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Photo Upload</title>

</head>
<body>
<input type="hidden" id="currentUserRole" value="${currentUserRole}"/>
<input type="hidden" id="currentUser" value="${currentUser}"/>

<form:form method="POST" modelAttribute="photoPhotoBucket" commandName="photoPhotoBucket" enctype="multipart/form-data">

    <%--&lt;%&ndash;**?${_csrf.parameterName}=${_csrf.token}**&ndash;%&gt;--%>

    <form:input type="hidden" path="photo.photoId" id="photId"/>
    <form:input type="hidden" path="photo.user" id="${currentUser}"/>

    <br>
    <form:input type="hidden" path="photo.photoPath" id="${currentUser.userLogin}"/>
    <br>
    <label for="photoName">Name</label>
    <form:input type="text" path="photo.photoName" id="photoName"/>
    <br>
    <label for="photoDescription">Photo description:</label>
    <form:input type="text" path="photo.photoDescription" id="photoDescription"/>
    <br>
    <form:checkbox path="photo.photoAvatar" id="photoAvatar" value="true"/>

    <form:input type="file" path="photoBucket" id="photoBucket" class="form-control input-sm"/>
    <%--<form:errors path="file" class="help-inline"/>--%>

    <input type="submit" value="Upload" class="btn btn-primary btn-sm">

</form:form>


</body>
</html>
