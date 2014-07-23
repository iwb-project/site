'use strict';

angular.module('iwbApplication').service('materialService', function($http) {
    return {
        findAll: function(query){
            return $http({
                method: "GET",
                url: '/materials/',
                isArray: true
            });
        }
    }
});
