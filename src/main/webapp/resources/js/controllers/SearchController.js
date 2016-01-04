'use strict';

app.controller('SearchController', ['$scope', '$http', 'bookFactory', 'categoryFactory',
    function ($scope, $http, bookFactory, categoryFactory) {

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
            id: "MUST",
            name: "must"
        },
        {
            id: "SHOULD",
            name: "should"
        },
        {
            id: "MUST_NOT",
            name: "must not"
        }
    ];

    // Set Default Conditions
    $scope.searchKeywordsCondition = $scope.conditions[0];
    $scope.searchTitleCondition = $scope.conditions[2];
    $scope.searchTextCondition = $scope.conditions[2];
    $scope.searchAuthorCondition = $scope.conditions[2];

    // Define Types
    $scope.types = [
        {
            id: "Regular",
            name: "regular"
        },
        {
            id: "Range",
            name: "range"
        },
        {
            id: "Fuzzy",
            name: "fuzzy"
        },
        {
            id: "Phrase",
            name: "phrase"
        },
        {
            id: "Prefix",
            name: "prefix"
        }
    ];

    // Set Default Types
    $scope.searchKeywordsType = $scope.types[0];
    $scope.searchTitleType = $scope.types[0];
    $scope.searchTextType = $scope.types[0];
    $scope.searchAuthorType = $scope.types[0];

    $scope.clickSearch = function() {

        var searchData = {
            text: $scope.searchText,
            textST: $scope.searchTextType.id,
            textCT: $scope.searchTextCondition.id,
            keywords: $scope.searchKeywords,
            keywordsST: $scope.searchKeywordsType.id,
            keywordsCT: $scope.searchKeywordsCondition.id,
            title: $scope.searchTitle,
            titleST: $scope.searchTitleType.id,
            titleCT: $scope.searchTitleCondition.id,
            author: $scope.searchAuthor,
            authorST: $scope.searchAuthorType.id,
            authorCT: $scope.searchAuthorCondition.id
        };

        console.log(searchData);

        $http.get('/search', searchData).success(function (data) {
            console.log(data);
        });
    }

}]);