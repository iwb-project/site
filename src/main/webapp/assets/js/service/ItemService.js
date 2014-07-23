'use strict';

angular.module('iwbApplication').service('itemService', function ($http) {
    return {
        search: function (query) {
            return $http({
                method: "GET",
                url: '/items/search',
                params: {q: query},
                isArray: true
            });
        },
        get: function (itemId) {
            return $http({
                method: "GET",
                url: '/items/' + itemId
            });
        },
        create: function (item) {
            return $http({
                method: "POST",
                url: '/items/',
                data: item
            });
        },
        update: function (itemId, item) {
            return $http({
                method: "PUT",
                url: '/items/' + itemId,
                data: item
            });
        }
    }
});
