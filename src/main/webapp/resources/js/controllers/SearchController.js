'use strict';

app.controller('SearchController', ['$scope', 'bookFactory', 'categoryFactory', function ($scope, bookFactory, categoryFactory) {

    $scope.books = [];
    $scope.categories = [];

    bookFactory.search().success(function (data) {
        $scope.books = data;
    });

    categoryFactory.getAll().success(function (data) {
        $scope.categories = data;
    });

}]);