'use strict';

app.controller('ManageUsersController', ['$scope', '$http', 'userFactory', 'categoryFactory',
    function ($scope, $http, userFactory, categoryFactory) {

    // Get All Users
    userFactory.getAll().success(function (users) {
        $scope.users = users;
    });

    // Get All Categories
    categoryFactory.getAll().success(function (categories) {
        $scope.categories = categories;
    });

    // Click Edit user
    $scope.clickEditUser = function(user) {
        $scope.user = user;
    };

    // Click Update User
    $scope.clickUpdateUser = function() {
        if (!$scope.user.firstName || !$scope.user.lastName || !$scope.user.type || !$scope.user.username) {
            alert('All fields are important!');
        }
        $http.put('/user', $scope.user).success(function (data) {
            userFactory.getAll().success(function (users) {
                $scope.users = users;
                $('#editUserModal').modal('hide');
            });
        });
    };

}]);