'use strict';

angular.module('iwbApplication').controller('locationController',
    ['$scope', 'locationService', '$modalInstance',
    function ($scope, locationService, $modalInstance) {
        $scope.name = 'locationController';

        $scope.alert = [];
        $scope.city = locationService.loadFromCookie();

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
