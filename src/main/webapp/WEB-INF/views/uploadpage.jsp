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

<%--<form:form method="POST" modelAttribute="photo" commandName="newPhoto" enctype="multipart/form-data">--%>
    <%--&lt;%&ndash;**?${_csrf.parameterName}=${_csrf.token}**&ndash;%&gt;--%>

    <%--<form:input type="hidden" path="photoId" id="photId"/>--%>

    <%--<form:input type="hidden" path="user" id="${currentUser}"/>--%>

    <%--<br>--%>
    <%--<form:input type="hidden" path="photoPath" id="${currentUser.userLogin}"/>--%>
    <%--<br>--%>
    <%--<label for="photoName">Name</label>--%>
    <%--<form:input type="text" path="photoName" id="photoName"/>--%>
    <%--<br>--%>
    <%--<label for="photoDescription">Photo description:</label>--%>
    <%--<form:input type="text" path="photoDescription" id="photoDescription"/>--%>
    <%--<br>--%>
    <%--<form:checkbox path="avatar" id="avatar" value="true"/>--%>

<%--<form method="POST" enctype="multipart/form-data" modelAttribute="photoBucket">--%>
<%--<br>--%>
    <%--<label>Picture</label>--%>
    <%--<input type="file", name="photoBucket">--%>
    <%--<br>--%>
    <%--<input type="submit" class="btn btn-default" value="UploadPhoto"/>--%>
    <%--&lt;%&ndash;<input type="hidden"&ndash;%&gt;--%>
           <%--&lt;%&ndash;name="${_csrf.parameterName}"&ndash;%&gt;--%>
           <%--&lt;%&ndash;value="${_csrf.token}"/>&ndash;%&gt;--%>
<%--</form>--%>
<%--</form:form>--%>


<div class="form-container">
    <h1>Spring 4 MVC File Upload Example </h1>
    <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="file">Upload a file</label>
                <div class="col-md-7">
                    <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="file" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" value="Upload" class="btn btn-primary btn-sm">
            </div>
        </div>
    </form:form>
    <a href="<c:url value='/welcome' />">Home</a>
</div>




</body>
</html>
