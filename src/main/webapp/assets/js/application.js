'use strict';

var iwbApplication = angular.module('iwbApplication', [
    'ngRoute',
    'ngResource',
    'ngCookies',
    'flow', // file upload
    'ui.bootstrap'
]);

iwbApplication.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.when('/', {templateUrl: 'fragments/home.html', controller: 'welcomeController'});
            $routeProvider.when('/search', {templateUrl: 'fragments/search.html', controller: 'searchController'});
            $routeProvider.when('/view/:itemId', {templateUrl: 'fragments/view.html', controller: 'viewController'});
            $routeProvider.when('/edit/:itemId', {templateUrl: 'fragments/edit.html', controller: 'viewController'});
            $routeProvider.when('/trashes/', {templateUrl: 'fragments/trashes.html', controller: 'trashesController'});
            $routeProvider.when('/about', {templateUrl: 'fragments/about.html', controller: 'aboutController'});
        $routeProvider.otherwise({redirectTo: '/'});
    }])
    .config(function($sceProvider) {
        $sceProvider.enabled(false);
    });


