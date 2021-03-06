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
            $routeProvider.when('/edit/:itemId?', {templateUrl: 'fragments/edit.html', controller: 'editController'});
            $routeProvider.when('/trashes/', {templateUrl: 'fragments/trashes.html', controller: 'trashesController'});
            $routeProvider.when('/second-lives/', {templateUrl: 'fragments/second-lives.html', controller: 'secondLivesController'});
            $routeProvider.when('/about', {templateUrl: 'fragments/about.html', controller: 'aboutController'});
            $routeProvider.when('/404', {templateUrl: 'fragments/error.html', controller: 'errorController'});
            $routeProvider.when('/403', {templateUrl: 'fragments/error.html', controller: 'errorController'});
        $routeProvider.otherwise({redirectTo: '/404'});
    }])
    .config(function($sceProvider) {
        $sceProvider.enabled(false);
    });

iwbApplication.directive('ngVisible', function () {
    return function (scope, element, attr) {
        scope.$watch(attr.ngVisible, function (visible) {
            element.css('visibility', visible ? 'visible' : 'hidden');
        });
    };
});

iwbApplication.directive('ngRepeatDone', function() {
        return function(scope, element, attrs) {
            if (scope.$last){
                scope.$parent.$eval(attrs['ngRepeatDone']);
            }
        };
    });

$(document).ready(function () {
    $(".navbar-nav li a").click(function(event) {
        $(".navbar-collapse").collapse('hide');
    });
});


