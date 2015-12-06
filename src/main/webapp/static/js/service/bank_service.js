'use strict';
App.factory('BankService', ['$http', '$q', function ($http, $q) {
    return {
        findAllBanks: function (){
            console.log("u herer in 5 col.!")
            return $http.get('http://localhost:8080/App/admin/bank')
                .then(
                function (response) {
                    return response.data;
                },
                function (errorResponse) {
                    console.error("error in getbanks");
                    return $q.reject(errorResponse);
                }
            );
        }
    }
}]);