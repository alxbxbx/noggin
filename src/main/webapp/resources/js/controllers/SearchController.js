'use strict';

app.controller('SearchController', ['$scope', 'bookFactory', 'categoryFactory', function ($scope, bookFactory, categoryFactory) {

    bookFactory.getAll().success(function (data) {
        $scope.books = data;
    });

    categoryFactory.getAll().success(function (data) {
        $scope.categories = data;
    });

}]);