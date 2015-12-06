'use strict';
App.controller('BankController', ['$scope','BankService', function($scope, BankService){
    var self = this;
    self.bank={bankId:null,bankName:''};
    self.banks = [];

    self.findAllBanks = function(){
        BankService.findAllBanks().then(
            function(d) {
                self.banks = d;
            },
            function(errResponse){
                console.error('Error while getting banks in bank_controller', errResponse);
            }
        )
    };

    self.findAllBanks();
}





]);
