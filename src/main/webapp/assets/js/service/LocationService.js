'use strict';

angular.module('iwbApplication').service('locationService', ['$http', '$cookies', function ($http, $cookies) {
    return {
        select: function (city) {
            if (city != '') {
                $cookies.city = city;
            }
        },
        geolocate: function (latitude, longitude) {
            return $http({
                method: "GET",
                url: 'https://maps.googleapis.com/maps/api/geocode/json',
                params: {
                    latlng: latitude + ',' + longitude,
                    result_type: 'locality',
                    key: 'AIzaSyBVAZ4XtYRtW35mHmbqFobtfNuj-A8xTCY'
                }
            });
        },
        tryHtml5Geolocation: function ($scope) {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(
                    function (position) {
                        console.log(position.coords.latitude + ',' + position.coords.longitude);
                        $http({
                            method: "GET",
                            url: 'https://maps.googleapis.com/maps/api/geocode/json',
                            params: {
                                latlng: position.coords.latitude + ',' + position.coords.longitude,
                                result_type: 'locality',
                                key: 'AIzaSyBVAZ4XtYRtW35mHmbqFobtfNuj-A8xTCY'
                            }})
                            .then(function (result) {
                                if (result.data.status != 'OK') {
                                    // failed...
                                    $scope.city = '';
                                    $scope.alerts = [
                                        {type: 'danger', msg: 'failed to locate you'}
                                    ];
                                } else {
                                    var acs = result.data.results[0].address_components;
                                    for (var index = 0; index < acs.length; index++) {
                                        if ($.inArray('locality', acs[index].types) >= 0) {
                                            $scope.city = acs[index].long_name;
                                            break;
                                        }
                                    }
                                    $scope.alerts = [
                                        {type: 'success', msg: 'we did it for you !' + $scope.city}
                                    ];
                                }
                            })
                    },
                    function (error) {
                        switch (error.code) {
                            case error.PERMISSION_DENIED:
                                $scope.alerts = [
                                    {type: 'danger', msg: 'you denied geolocation'}
                                ];
                                break;
                            case error.POSITION_UNAVAILABLE:
                                $scope.alerts = [
                                    {type: 'danger', msg: 'location is not available'}
                                ];
                                break;
                            case error.TIMEOUT:
                                $scope.alerts = [
                                    {type: 'danger', msg: 'geolocation timed out'}
                                ];
                                break;
                            case error.UNKNOWN_ERROR:
                                $scope.alerts = [
                                    {type: 'danger', msg: 'unknown error occurred during geolocation'}
                                ];
                                break;
                        }
                    });
            } else {
                $scope.alerts = [
                    {type: 'danger', msg: 'your browser does not supports geolocation'}
                ];
            }
        }
    }
}]);
