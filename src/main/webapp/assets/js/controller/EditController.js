'use strict';

angular.module('iwbApplication').controller('editController',
    ['$scope', '$location', '$routeParams', 'itemService', 'materialService', '$cookies',
        function ($scope, $location, $routeParams, itemService, materialService, $cookies) {
            $scope.name = 'editController';
            $scope.itemId = $routeParams.itemId;

            $scope.launchIntentForScanning = function (event, targetId) {
                console.log('scanning...');
                $cookies.itemBeingAdded = JSON.stringify($scope.item);
                var callbackUrl = encodeURIComponent($location.absUrl().replace(/\/#\/.*/, '/#/edit/?bfs=true&barcode={CODE}'));
                window.location.assign('http://zxing.appspot.com/scan?ret=' + callbackUrl);
            }

            $scope.addComponent = function () {
                $scope.item.components.push({});
            }

            $scope.removeComponent = function (index) {
                $scope.item.components.splice(index, 1);
            }

            $scope.loadItem = function () {
                itemService.get($scope.itemId)
                    .then(function (result) {
                        $scope.item = result.data;
                    });
            }

            $scope.storeItem = function () {
                if ($scope.item._id == null) {
                    console.log('storing new item...');
                    itemService.create($scope.item)
                        .then(function (result) {
                            if (result.data.error != null) {
                                alert(result.data.error);
                            } else {
                                $routeParams.itemId = result.data.itemId;
                                $location.url('view/' + result.data.itemId);
                            }
                        });
                } else {
                    console.log('updating item...');
                    itemService.update($scope.itemId, $scope.item)
                        .then(function (result) {
                            if (result.data.error != null) {
                                alert(result.data.error);
                            } else {
                                $location.url('view/' + result.data.itemId);
                            }
                        });
                }
            }

            $scope.materials = [];
            $scope.loadMaterials = function () {
                $scope.materials = materialService.findAll()
                    .then(function (result) {
                        $scope.materials = result.data;
                    });
            }

            $scope.loadMaterials();

            if ($scope.itemId != undefined) {
                console.log('loading existing item');
                $scope.loadItem();
            } else {
                if ($routeParams.bfs == 'true') {
                    console.log('back from scanning...');
                    $scope.item = JSON.parse($cookies.itemBeingAdded);
                    $scope.item.barcode = $routeParams.barcode;
                    $cookies.itemBeingAdded = null;
                } else {
                    console.log('new fresh item...');
                    // new empty
                    $scope.item = {
                        _id: null,
                        name: "",
                        barcode: "",
                        materialId: null,
                        components: []
                    };
                    console.log('w/ name ' + ($location.search()).name);
                    $scope.item.name = ($location.search()).name;
                    console.log('w/ barcode ' + ($location.search()).barcode);
                    $scope.item.barcode = ($location.search()).barcode;
                }
            }

        }]);
