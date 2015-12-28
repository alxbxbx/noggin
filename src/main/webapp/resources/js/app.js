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
        .when('/manage/books', {
            templateUrl: '/resources/html/pages/manage_books.html',
            controller: 'ManageBooksController'
        })
        .when('/manage/users', {
            templateUrl: '/resources/html/pages/manage_users.html',
            controller: 'ManageUsersController'
        })
        .when('/manage/categories', {
            templateUrl: '/resources/html/pages/manage_categories.html',
            controller: 'ManageCategoriesController'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);
