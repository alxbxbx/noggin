'use strict';

app.controller('SearchController', ['$scope', 'bookFactory', 'categoryFactory', 'loginService', function ($scope, bookFactory, categoryFactory, loginService) {

    $scope.books = [];
    $scope.categories = [];

    bookFactory.search().success(function (data) {
        $scope.books = data;
    });

    categoryFactory.getAll().success(function (data) {
        $scope.categories = data;
    });

    loginService.login();

}]);