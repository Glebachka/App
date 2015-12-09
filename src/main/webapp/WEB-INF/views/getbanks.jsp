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


    <form ng-submit="ctrl.submit()" name="bankForm">
        <input type="hidden" ng-model="ctrl.bank.bankId"/>

        <label >Name</label>
        <input type="text" ng-model="ctrl.bank.bankName"
               name="bankName" placeholder="enter Bank name" required ng-minlength="3"/>

        <div ng-show="bankForm.$dirty">
            <span ng-show="bankForm.bname.$error.required">Required feild.</span>
            <span ng-show="bankForm.bname.$error.minlength">minimum length is 3</span>
            <span ng-show="bankForm.bname.$invalid">bad credentails</span>
        </div>

        <input type="submit" value="{{!ctrl.bank.bankId ? 'new' : 'update'}}" ng-disabled="bankForm.$invalid">
        <button type="button" ng-click="ctrl.reset()" ng-disabled="bankForm.$pristine">Reset From</button>
    </form>

</div>
</body>
</html>
