'use strict';

angular.module('iwbApplication').controller('welcomeController', ['$scope', '$location',
    function ($scope, $location) {
        $scope.name = 'welcomeController';
        $scope.searchQuery = '';
        $scope.searchForItem = function (event) {
            if ($scope.searchQuery == undefined || $scope.searchQuery.trim() == '') {
                // we need something to search for
                return;
            }
            $location.search('q', $scope.searchQuery);
            $location.path("/search");
        }
    }]);