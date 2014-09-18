'use strict';

angular.module('iwbApplication').controller('secondLivesController', ['$scope', 'trashService', 'materialService',
    function ($scope, trashService, materialService) {
        $scope.name = 'secondLivesController';
        $scope.trashes = [];
        $scope.materials = [];

        $scope.loadTrashes = function () {
            trashService.findAll()
                .then(function (result) {
                    $scope.trashes = result.data;
                });
        }

        $scope.loadMaterials = function () {
            materialService.findAll()
                .then(function (result) {
                    $scope.materials = result.data;
                });
        }

        $scope.fixDropdowns = function() {
            console.log("fixed " + $('.dropdown-toggle').length)
            $('.dropdown-toggle').click(function(e) {
                e.preventDefault();
                e.stopPropagation();

                return false;
            });
        }
        $scope.loadTrashes();
        $scope.loadMaterials();

    }]);
