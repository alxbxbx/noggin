'use strict';

app.controller('SearchController', ['$scope', 'bookFactory', 'categoryFactory', function ($scope, bookFactory, categoryFactory) {

    $scope.books = [];
    $scope.categories = [];

    categoryFactory.getAll().success(function (data) {
        $scope.categories = data;
    });

}]);