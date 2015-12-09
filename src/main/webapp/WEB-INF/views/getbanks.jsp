<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: gleb
  Date: 06.12.2015
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src='<c:url value="/static/js/app.js"/>'></script>
    <script src='<c:url value="/static/js/controller/bank_controller.js"/>'></script>
    <script src='<c:url value="/static/js/service/bank_service.js"/>'></script>
</head>
<body ng-app="App">
<div ng-controller="BankController as ctrl">
    <table>
        <thead>
        <tr>
            <th>bank ID</th>
            <th>bank name</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="b in ctrl.banks">
            <td><span ng-bind="b.bankId"></span></td>
            <td><span ng-bind="b.bankName"></span></td>
        </tr>
        </tbody>
    </table>
    <button type="button" ng-click="ctrl.findAllBanks()">Reset Form</button>
</div>

<div ng-controller="">
    <form:form  >
        <input type="text" placeholder="Bank name">


        </table>

    </div>


</body>
</html>
