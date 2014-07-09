'use strict';

angular.module('iwbApplication').controller('viewController', ['$scope', '$location', '$routeParams', 'itemService',
    function ($scope, $location, $routeParams, itemService) {
        $scope.name = 'viewController';
        $scope.itemId = $routeParams.itemId;
        $scope.item = {};


        $scope.view = function() {
            itemService.get($scope.itemId)
                .then(function (result) {
                    $scope.item = result.data;
                });
        }

        $scope.view();

    }]);