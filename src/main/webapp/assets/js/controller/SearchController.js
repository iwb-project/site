'use strict';

angular.module('iwbApplication').controller('searchController', ['$scope', '$location', '$routeParams', 'itemService',
    function ($scope, $location, $routeParams, itemService) {
        $scope.name = 'searchController';
        $scope.searchQuery = $routeParams.q;
        $scope.items = [];

        $scope.search = function() {
            itemService.search($scope.searchQuery)
                .then(function (result) {
                    $scope.items = result.data;
                });
        }

        $scope.view = function(itemId) {
            $location.url('view/' + itemId);
        }

        $scope.search();

    }]);