'use strict';

angular.module('iwbApplication').controller('aboutController', ['$scope', 'aboutService',
    function ($scope, aboutService) {
        $scope.name = 'aboutController';
        $scope.about = {};

        loadAboutInformation();


        function loadAboutInformation() {
            aboutService.query()
                .then(function (result) {
                    $scope.about = result.data;
                });
        }

    }]);