'use strict';

angular.module('iwbApplication').service('itemService', function($http) {
    return {
        search: function(query){
            return $http({
                method: "GET",
                url: '/items/search',
                params: {q: query},
                isArray: true
            });
        },
        get: function(itemId){
            return $http({
                method: "GET",
                url: '/items/' + itemId
            });
        }
    }
});