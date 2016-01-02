'use strict';

app.controller('ManageBooksController', ['$scope', '$http', 'bookFactory', function ($scope, $http, bookFactory) {

    $scope.book = {};

    bookFactory.getAll().success(function(data) {
        $scope.books = data;
    });

    $scope.clickEditBook = function(id, title, author, keywords, publicationYear) {
        $scope.book.id = id;
        $scope.book.title = title;
        $scope.book.author = author;
        $scope.book.keywords = keywords;
        $scope.book.publicationYear = publicationYear;
    };

    $scope.clickUpdateBook = function() {
        if (!$scope.book.title || !$scope.book.keywords) {
            alert("Title and keywords are required!");
        } else {
            $http.put('/book/' + $scope.book.id, $scope.book).success(function (data) {
                bookFactory.getAll().success(function(data) {
                    $scope.books = data;
                });
            });
        }
    };

    $scope.clickRemoveBook = function() {

    };

}]);