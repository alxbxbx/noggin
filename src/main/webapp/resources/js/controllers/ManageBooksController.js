'use strict';

app.controller('ManageBooksController', ['$scope', 'bookFactory', function ($scope, bookFactory) {

    bookFactory.getAll().success(function(data) {
        $scope.books = data;
    });

}]);