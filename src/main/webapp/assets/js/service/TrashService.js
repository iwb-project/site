'use strict';

angular.module('iwbApplication').service('trashService', function($http) {
    return {
        findAll: function(){
            return $http({
                method: 'GET',
                url: '/trashes/'
            });
        }
    }
});
