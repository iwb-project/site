'use strict';

angular.module('iwbApplication').controller('applicationController',
    ['$scope', 'locationService', '$modal', '$log',
        function ($scope, locationService, $modal, $log) {
            $scope.name = 'applicationController';

            $scope.location = locationService.loadFromCookie();

            $scope.popup = function () {
                var modalInstance = $modal.open({
                    templateUrl: 'fragments/location.html',
                    controller: 'locationController',
                    windowClass: 'modal'
                });


                modalInstance.result.then(function (selectedItem) {
                    $scope.selected = selectedItem;
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            }

        }]);
