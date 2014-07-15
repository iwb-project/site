'use strict';

angular.module('iwbApplication').controller('locationController',
    ['$scope', '$modalInstance', 'locationService',
    function ($scope, $modalInstance, locationService) {
        $scope.name = 'locationController';

        $scope.alert = [];
        $scope.city = '';

        $scope.tryHtml5Geolocation = function() {
            locationService.tryHtml5Geolocation($scope);
        }

        $scope.ok = function () {
            locationService.select($scope.city);
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);
