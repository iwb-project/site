'use strict';

var iwbApplication = angular.module('iwbApplication', [
    'ngRoute',
    'ngResource'
]);

iwbApplication.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.when('/', {templateUrl: 'fragments/home.html', controller: 'welcomeController'});
            $routeProvider.when('/search', {templateUrl: 'fragments/search.html', controller: 'searchController'});
            $routeProvider.when('/view/:itemId', {templateUrl: 'fragments/view.html', controller: 'viewController'});
            $routeProvider.when('/edit/:itemId', {templateUrl: 'fragments/edit.html', controller: 'viewController'});
            $routeProvider.when('/trashes/', {templateUrl: 'fragments/trashes.html', controller: 'trashesController'});
            $routeProvider.when('/about', {templateUrl: 'fragments/about.html', controller: 'aboutController'});
//        $routeProvider.when('/packages/id/:packageId', {templateUrl: 'fragments/package.html', controller: PackageController});
//        $routeProvider.when('/packages', {templateUrl: 'fragments/packages.html', controller: PackagesController});
        $routeProvider.otherwise({redirectTo: '/'});
    }])
    .config(function($sceProvider) {
        $sceProvider.enabled(false);
    });;
