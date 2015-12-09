'use strict';

var app = angular.module('noggin', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'resources/html/search.html',
        controller: 'SearchController'
    })
    .otherwise({
        redirectTo: '/'
    });
}]);