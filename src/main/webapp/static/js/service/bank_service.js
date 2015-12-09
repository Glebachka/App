'use strict';
App.factory('BankService', ['$http', '$q', function ($http, $q) {
    return {
        findAllBanks: function (){
            return $http.get('http://localhost:8080/App/admin/bank/')
                .then(
                function (response) {
                    return response.data;
                },
                function (errorResponse) {
                    console.error("error in getbanks");
                    return $q.reject(errorResponse);
                }
            );
        },

        newBank: function(bank){
            return $http.post('http://http://localhost:8080/App/admin/bank/', bank).then(
                function(response){
                    return response.data;
                },
                function(errorResponse){
                    console.error("error in create new bank");
                    return $q.reject(errorResponse);
                }
            );
        },
        updateBank: function(bank, bankId){
            return $http.put('http://http://localhost:8080/App/admin/bank/'+bankId, bank).then(
                function(response){
                    return response.data;
                },
                function(errorResponse){
                    console.error("error in bank edit");
                    return $q.reject(errorResponse);
                }
            );
        },

        deleteBank: function(bankId){
            return $http.delete('http://localhost:8080/App/admin/bank/' + bankId).then (
                function (response) {
                    return response.data;
                },
                function(errResponse){
                    console.error('Error in delete bank');
                    return $q.reject(errResponse);
                }
            )
        }

    }
}]);
