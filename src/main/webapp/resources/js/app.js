'use strict';

// Angular Application Definition
var app = angular.module('noggin', ['ngRoute', 'ngFileUpload']);

// Angular Routes
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/resources/html/pages/home.html',
            controller: 'HomeController'
        })
        .when('/search', {
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
        .when('/manage/languages', {
            templateUrl: '/resources/html/pages/manage_languages.html',
            controller: 'ManageLanguagesController'
        })
        .when('/upload', {
            templateUrl: '/resources/html/pages/upload.html',
            controller: 'UploadController'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);

app.run(['$rootScope', '$location', '$http', function ($rootScope, $location, $http) {
    $rootScope.$on('$routeChangeStart', function (event) {
        $http.get('/login-check').success(function (data) {
            if (data == false && window.location.hash != '#/') {
                event.preventDefault();
                $location.path('/');
            }
        });
    });
}]);
