'use strict';

app.controller('ManageCategoriesController', ['$scope', '$http', 'categoryFactory', function ($scope, $http, categoryFactory) {

    $scope.selectedCategory = {};

    categoryFactory.getAll().success(function(data) {
        $scope.categories = data;
    });

    $scope.clickEditCategory = function(id, name) {
        $scope.selectedCategory.id = id;
        $scope.selectedCategory.name = name;
    };

    $scope.clickRemoveCategory = function(id) {
        var check = prompt("Are you sure? (Y/N)");
        if (check == "Y" || check == "y") {
            $http.delete('/category/' + id).success(function (data) {
                console.log(data);
            });
        }
        $('#editCategoryModal').modal('hide');
    };

    $scope.clickUpdateCategory = function() {
        $http.put('/category/' + $scope.selectedCategory.id, $scope.selectedCategory).success(function(data) {
            console.log(data);
            categoryFactory.getAll().success(function(categories) {
                $scope.categories = categories;
            });
            $('#editCategoryModal').modal('hide');
        });

    };

}]);