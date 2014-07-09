'use strict';

angular.module('iwbApplication').service('locationService', function ($http) {
    return {
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
        tryHtml5Geolocation: function () {
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
                            .then(function(result) {
                                console.log(result)
                            })
                    },
                    function (error) {
                        switch (error.code) {
                            case error.PERMISSION_DENIED:
                                console.log("User denied the request for Geolocation.")
                                break;
                            case error.POSITION_UNAVAILABLE:
                                console.log("Location information is unavailable.")
                                break;
                            case error.TIMEOUT:
                                console.log("The request to get user location timed out.")
                                break;
                            case error.UNKNOWN_ERROR:
                                console.log("An unknown error occurred.")
                                break;
                        }
                    });
            } else {
                console.log("Geolocation is not supported by this browser.")
            }
        }
    }
});
