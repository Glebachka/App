<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>All Users</title>

    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
          integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
            integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
            crossorigin="anonymous"></script>
    <script src="<c:url value="/static/js/role.js" />"></script>

</head>


<body>
<input type="hidden" id="currentUserRole" value="${currentUserRole}"/>


<h2>List of Users</h2>

<div class="table-responsive">
    <table class="table table-striped">
        <div>
            <P4>Hello <c:out value="${currentUserName}"/>! You entered as <c:out value="${currentUserRole}"/></P4>
            </P4>

        </div>
        <tr>
            <td class="col-sm-1">Name</td>
            <td class="col-sm-1">Login</td>
            <c:if test="${currentUserRole == 'dba'}">
                <td>Edit</td>
                <td>Delete</td>
                <td>Role</td>
            </c:if>

        </tr>
        <div id="roleTD">
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.userName}</td>
                    <td>${user.userLogin}</td>
                    <c:if test="${currentUserRole == 'dba'}">
                        <td>
                            <a href="<c:url value='/${currentUserRole}/edit-${user.userLogin}-user'/> ">${user.userLogin}</a>
                        </td>
                        <td><a href="<c:url value='/${currentUserRole}/delete-${user.userLogin}-user'/> ">Delete</a>
                        </td>
                        <td>${user.userRoles}</td>
                    </c:if>
                </tr>
            </c:forEach>
        </div>
    </table>
</div>
<br>

<a class="btn btn-default" href="<c:url value='/${currentUserRole}/new' /> ">Add new User</a>
<a class="btn btn-default" href="<c:url value='/logout' /> ">LogOut</a>
<a class="btn btn-default" href="http://google.com">GOOGLE</a>

</body>
</html>
