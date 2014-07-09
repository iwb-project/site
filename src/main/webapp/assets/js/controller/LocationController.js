'use strict';

angular.module('iwbApplication').controller('locationController',
    ['$scope', '$location', '$routeParams', 'locationService',
    function ($scope, $location, $routeParams, locationService) {
        $scope.name = 'locationController';

        $scope.geolocate = function() {
            locationService.tryHtml5Geolocation();
        }

    }]);
