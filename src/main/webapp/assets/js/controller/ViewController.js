'use strict';

angular.module('iwbApplication').controller('viewController', ['$scope', '$location', '$routeParams', 'itemService',
    function ($scope, $location, $routeParams, itemService) {
        $scope.name = 'viewController';
        $scope.itemId = $routeParams.itemId;
        $scope.item = {};

        $scope.edit = function () {
            $location.url('edit/' + $scope.itemId);
        }

        $scope.load = function () {
            itemService.get($scope.itemId)
                .then(function (result) {
                    $scope.item = result.data;
                    if ($scope.item.barcode != undefined) {
                        // load the barcode
                        $('#itemBarcodeImage')[0].src = '/barcode/' + $scope.item.barcode;
                    }
                });
        }

        $scope.load();

    }]);
