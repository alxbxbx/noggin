'use strict';

app.controller('ManageCategoriesController', ['$scope', '$http', 'categoryFactory', function ($scope, $http, categoryFactory) {

    // Get Categories
    categoryFactory.getAll().success(function(data) {
        $scope.categories = data;
    });

    // Click Add Category
    $scope.clickAddCategory = function() {
        $scope.category = {};
        $scope.category.name = '';
    };

    // Click Save Category
    $scope.clickSaveCategory = function() {
        if (!$scope.category.name.length) {
            alert('Name is not set!');
        } else {
            $http.post('/category', $scope.category).success(function (data) {
                categoryFactory.getAll().success(function(categories) {
                    $scope.categories = categories;
                    $('#addCategoryModal').modal('hide');
                });
            });
        }
    };

    // Click Edit Category
    $scope.clickEditCategory = function(category) {
        $scope.category = category;
        console.log(category);
    };

    // Click Update Category
    $scope.clickUpdateCategory = function() {
        $http.put('/category/' + $scope.category.id, $scope.category).success(function(data) {
            categoryFactory.getAll().success(function(categories) {
                $scope.categories = categories;
                $('#editCategoryModal').modal('hide');
            });
        });
    };

    // Click Remove Category
    $scope.clickRemoveCategory = function(category) {
        var check = prompt("Are you sure? (Y/N)");
        if (check.toLowerCase() == "y") {
            $http.delete('/category/' + category.id).success(function (data) {
                categoryFactory.getAll().success(function(data) {
                    $scope.categories = data;
                    $('#editCategoryModal').modal('hide');
                });
            });
        }
    };

}]);