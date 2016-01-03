'use strict';

app.controller('ManageCategoriesController', ['$scope', '$http', 'categoryFactory', function ($scope, $http, categoryFactory) {

    //
    $scope.selectedCategory = {};

    // Get Categories
    categoryFactory.getAll().success(function(data) {
        $scope.categories = data;
    });

    // Edit Category
    $scope.clickEditCategory = function(id, name) {
        $scope.selectedCategory.id = id;
        $scope.selectedCategory.name = name;
    };

    // Remove Category
    $scope.clickRemoveCategory = function(id) {
        var check = prompt("Are you sure? (Y/N)");
        if (check == "Y" || check == "y") {
            $http.delete('/category/' + id).success(function (data) {
                categoryFactory.getAll().success(function(data) {
                    $scope.categories = data;
                    $('#editCategoryModal').modal('hide');
                });
            });
        }
    };

    // Update Category
    $scope.clickUpdateCategory = function() {
        $http.put('/category/' + $scope.selectedCategory.id, $scope.selectedCategory).success(function(data) {
            console.log(data);
            categoryFactory.getAll().success(function(categories) {
                $scope.categories = categories;
                $('#editCategoryModal').modal('hide');
            });
        });
    };

}]);