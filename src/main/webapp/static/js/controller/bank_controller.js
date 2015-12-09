'use strict';
App.controller('BankController', ['$scope', 'BankService', function ($scope, BankService) {
    var self = this;
    self.bank = {bankId: null, bankName: ''};
    self.banks = [];

    self.findAllBanks = function () {
        BankService.findAllBanks().then(
            function (d) {
                self.banks = d;
            },
            function (errResponse) {
                console.error('Error while getting banks in bank_controller', errResponse);
            }
        )
    };

    self.newBank = function (bank) {
        BankService.newBank(bank).then(
            self.findAllBanks(), function (erroResponse) {
                console.error('Error in create bank in controller. ');
            }
        );

    };

    self.updateBank = function (bank, bankId) {
        BankService.updateBank(bank, bankId).then(
            self.findAllBanks(), function (errResponse) {
                console.error('Error while update bank in controller');
            }
        );
    };

    self.findAllBanks();

    self.submit = function () {
        if (self.bank.bankId == null) {
            console.log("Create new bank", self.bank);
            self.newBank(self.bank);
        } else {
            self.updateBank(self.bank, self.bank.bankId);

        }
        self.reset();
    };

    self.update = function (bankId) {
        for (var i = 0; i < self.banks.length; i++) {
            if (self.banks[i].bankId == bankId) {
                self.bank = angular.copy(self.banks[i]);
                break;
            }
        }
    };

    self.delete = function (bankId) {
        for (var i = 0; i < self.banks.length; i++) {
            if (self.banks[i].bankId == bankId) {
                self.reset();
                break;
            }
        }
        self.deleteBank(bankId);
    };

    self.reset = function () {
        self.bank = {bankId: null, bankName: ''};
        $scope.bankForm.$setPristine();
    };
}]);
