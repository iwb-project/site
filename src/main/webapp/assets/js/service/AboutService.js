'use strict';

angular.module('iwbApplication').service('aboutService', function($http) {
    return {
        query: function(){
            return $http({
                method: "GET",
                url: '/about'
            });
        }
    }
});