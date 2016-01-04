'use strict';

app.controller('SearchController', ['$scope', 'bookFactory', 'categoryFactory', function ($scope, bookFactory, categoryFactory) {

    // Get Books
    bookFactory.getAll().success(function (data) {
        $scope.books = data;
    });

    // Get Categories
    categoryFactory.getAll().success(function (data) {
        $scope.categories = data;
    });

    // Define Conditions
    $scope.conditions = [
        {
            id: 0,
            name: "must"
        },
        {
            id: 1,
            name: "should"
        },
        {
            id: 2,
            name: "mustNot"
        }
    ];

    // Set Default Conditions
    $scope.searchKeywordsCondition = $scope.conditions[0];
    $scope.searchTitleCondition = $scope.conditions[0];
    $scope.searchTextCondition = $scope.conditions[0];

    // Define Types
    $scope.types = [
        {
            id: 0,
            name: "regular"
        },
        {
            id: 1,
            name: "range"
        },
        {
            id: 2,
            name: "fuzzy"
        }
    ];

    // Set Default Types
    $scope.searchKeywordsType = $scope.types[0];
    $scope.searchTitleType = $scope.types[0];
    $scope.searchTextType = $scope.types[0];

}]);