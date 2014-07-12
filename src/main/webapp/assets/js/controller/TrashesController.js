'use strict';

angular.module('iwbApplication').controller('trashesController', ['$scope', 'trashService',
    function ($scope, trashService) {
        $scope.name = 'trashesController';
        $scope.trashes = [];

        $scope.loadTrashes = function () {
            trashService.findAll()
                .then(function (result) {
                    $scope.trashes = result.data;
                });
        }

        $scope.loadTrashes();
    }]);
