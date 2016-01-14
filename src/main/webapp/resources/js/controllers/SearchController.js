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

    // Define variables
    $scope.searchText = "";
    $scope.searchKeywords = "";
    $scope.searchTitle = "";
    $scope.searchAuthor = "";

    $scope.clickSearch = function() {

        var searchData = {
            text: $scope.searchText,
            textST: $scope.searchTextType.id,
            textSC: $scope.searchTextCondition.id,
            keywords: $scope.searchKeywords,
            keywordsST: $scope.searchKeywordsType.id,
            keywordsSC: $scope.searchKeywordsCondition.id,
            title: $scope.searchTitle,
            titleST: $scope.searchTitleType.id,
            titleSC: $scope.searchTitleCondition.id,
            author: $scope.searchAuthor,
            authorST: $scope.searchAuthorType.id,
            authorSC: $scope.searchAuthorCondition.id
        };

        $http({
            url: '/search',
            method: 'GET',
            params: searchData
        }).success(function (data) {
            $scope.books = data;
        });

    }

}]);