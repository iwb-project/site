'use strict';

angular.module('iwbApplication').controller('errorController', ['$scope', '$location', '$routeParams',
    function ($scope, $location, $routeParams) {
        $scope.name = 'errorController';
        $scope.errorCode = $location.url();
    }]);
