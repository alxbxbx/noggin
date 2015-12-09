(function () {

    var app = angular.module('noggin', ['ngRoute']);

    app.factory('bookFactory', ['$http', function ($http) {

        var factory = {};

        factory.search = function () {
            return $http.get('search');
        };

        return factory;

    }]);

    app.factory('categoryFactory', ['$http', function ($http) {

        var factory = {};

        factory.getAll = function () {
            return $http.get('category');
        };

        return factory;

    }]);

    app.controller('IndexController', ['$scope', 'bookFactory', 'categoryFactory', function ($scope, bookFactory, categoryFactory) {

        $scope.books = [];
        $scope.categories = [];

        bookFactory.search().success(function (data) {
            $scope.books = data;
        });

        categoryFactory.getAll().success(function (data) {
            $scope.categories = data;
        });

    }]);

    app.controller('DashboardController', ['$scope', function ($scope) {

    }]);

    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
        when('/dashboard', {
            templateUrl: 'resources/html/dashboard.html',
            controller: 'DashboardController'
        }).
        otherwise({
            redirectTo: '/dashboard'
        });
    }]);

})();