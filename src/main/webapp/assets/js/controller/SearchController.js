'use strict';

angular.module('iwbApplication').controller('searchController', ['$scope', '$location', '$routeParams', 'itemService',
    function ($scope, $location, $routeParams, itemService) {
        $scope.name = 'searchController';

        var aggregatedSearchQuery = '';

        $scope.searchQuery = $routeParams.q;
        if ($scope.searchQuery == undefined) {
            $scope.searchQuery = '';
        }
        aggregatedSearchQuery = $scope.searchQuery;


        $scope.searchBarcode = $routeParams.barcode;
        if ($scope.searchBarcode != undefined) {
            if ($scope.searchQuery.length > 0) {
                $scope.searchQuery = $scope.searchQuery + ' ';
            }
            aggregatedSearchQuery += $scope.searchBarcode;
        }
        $scope.items = [];

        $scope.search = function () {
            itemService.search(aggregatedSearchQuery)
                .then(function (result) {
                    $scope.items = result.data;
                });
        }

        $scope.view = function (itemId) {
            $location.url('view/' + itemId);
        }

        $scope.search();

    }]);
