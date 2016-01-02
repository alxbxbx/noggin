'use strict';

app.controller('ManageBooksController', ['$scope', '$http', 'bookFactory', 'languageFactory', 'categoryFactory',
    function ($scope, $http, bookFactory, languageFactory, categoryFactory) {

    // Get Books
    bookFactory.getAll().success(function(data) {
        $scope.books = data;
    });

    // Get Categories
    languageFactory.getAll().success(function(data) {
        $scope.languages = data;
    });

    // Get Languages
    categoryFactory.getAll().success(function(data) {
        $scope.categories = data;
    });

    // Select Book
    $scope.clickEditBook = function(bookByReference) {
        $scope.book = bookByReference;
        console.log("Selected book:");
        console.log(bookByReference);
    };

    // Save Book
    $scope.clickUpdateBook = function() {
        if (!$scope.book.title || !$scope.book.keywords) {
            alert("Title and keywords are required!");
        } else {
            console.log("Preparing to update book:");
            console.log($scope.book);
            $http.put('/book/' + $scope.book.id, $scope.book).success(function (data) {
                bookFactory.getAll().success(function(data) {
                    $scope.books = data;
                });
                console.log("Book updated successfully.");
                $('#editBookModal').modal('hide');
            });
        }
    };

    // Remove Book
    $scope.clickRemoveBook = function() {

    };

}]);