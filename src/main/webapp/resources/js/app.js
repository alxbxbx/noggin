'use strict';

// Angular Application Definition
var app = angular.module('noggin', ['ngRoute']);

// Angular Routes
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: '/resources/html/pages/search.html',
        controller: 'SearchController'
    })
    .otherwise({
        redirectTo: '/'
    });
}]);
