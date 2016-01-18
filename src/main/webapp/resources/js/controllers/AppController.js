'use strict';

app.controller('AppController', ['$location', '$scope', '$http', 'userFactory', 'authService', 'categoryFactory',
    function ($location, $scope, $http, userFactory, authService, categoryFactory) {

    $scope.login = function (username, password) {
        authService.login(username, password).success(function (user) {
            if (!user) {
                alert("Failed to authenticate!");
            } else {
                sessionStorage.setItem("auth", JSON.stringify(user));
                $('#loginModal').modal('hide');
                setAuthToScope();
            }
        });
    };

    $scope.logout = function () {
        authService.logout().success(function () {
            sessionStorage.removeItem("auth");
            $scope.auth = null;
            $location.url('/');
        });
    }

    function setAuthToScope() {
        if (sessionStorage.getItem("auth")) {
            $scope.auth = JSON.parse(sessionStorage.getItem("auth"));
        }
    }

    setAuthToScope();

    /**
     * User Managment
     */

    $scope.user = {};
    $scope.user.subscribeToAll = false;
    $scope.user.total = 9;

    $scope.$watch('user.subscribeToAll', function () {
        if ($scope.user.subscribeToAll == true) {
            $scope.user.total = 18;
        } else {
            $scope.user.total = 9;
        }
    });

    // Get All Users
    userFactory.getAll().success(function (users) {
        $scope.users = users;
    });

    // Get All Categories
    categoryFactory.getAll().success(function (categories) {
        $scope.categories = categories;
    });

    // Click Edit user
    $scope.clickEditUser = function (user) {
        $scope.user = user;
        if (!$scope.user.category) {
            $scope.user.subscribeToAll = true;
        } else {
            $scope.user.subscribeToAll = false;
        }
    };

    // Click Update User
    $scope.clickUpdateUser = function () {
        if (!$scope.user.firstName || !$scope.user.lastName || !$scope.user.type || !$scope.user.username) {
            alert('All fields are important!');
        }

        if ($scope.user.subscribeToAll) {
            $scope.user.category = null;
        }

        $scope.readyUser = {};
        $scope.readyUser.id = $scope.user.id;
        $scope.readyUser.firstName = $scope.user.firstName;
        $scope.readyUser.lastName = $scope.user.lastName;
        $scope.readyUser.category = $scope.user.category;
        $scope.readyUser.username = $scope.user.username;
        $scope.readyUser.password = $scope.user.password;
        $scope.readyUser.type = $scope.user.type;

        console.log($scope.readyUser);

        $http.put('/user', $scope.readyUser).success(function (data) {
            userFactory.getAll().success(function (users) {
                $scope.users = users;
                $('#editUserModal').modal('hide');
            });
        });
    };

    // Click Add User
    $scope.clickAddUser = function () {
        $scope.user.username = '';
        $scope.user.password= '';
        $scope.user.passwordAgain = '';
        $scope.user.firstName = '';
        $scope.user.lastName = '';
        $scope.user.category = $scope.categories[0];
    };

    // Click Save User
    $scope.clickSaveUser = function () {

        var error = 0;

        // Check username
        if (!$scope.user.username) { error++; }

        // Check first name
        if (!$scope.user.firstName) { error++; }

        // Check last name
        if (!$scope.user.lastName) { error++; }

        // Check passwords
        if (!$scope.user.password || !$scope.user.passwordAgain) { error++; }

        // Check if passwords match
        if ($scope.user.password != $scope.user.passwordAgain) { error++; }

        // Check categories
        if ($scope.user.subscribeToAll) {
            $scope.user.category = null;
        }

        // Show error if exists
        if (error > 0) {
            alert('All fields are required and passwords must match!');
        }

        // Submit data otherwise
        else {

            $scope.readyUser = {};
            $scope.readyUser.firstName = $scope.user.firstName;
            $scope.readyUser.lastName = $scope.user.lastName;
            $scope.readyUser.category = $scope.user.category;
            $scope.readyUser.username = $scope.user.username;
            $scope.readyUser.password = $scope.user.password;
            $scope.readyUser.type = 0;

            if ($scope.user.subscribeToAll) {
                $scope.readyUser.category = null;
            }

            $http.post('/user', $scope.readyUser).success(function (data) {
                userFactory.getAll().success(function (users) {
                    $scope.users = users;
                    $('#registerModal').modal('hide');
                });
            });
        }

    };

    // Click Remove
    $scope.clickRemoveUser = function (user) {
        var check = prompt("Are you sure? (Y/N)");
        if (check.toLowerCase() == "y") {
            $http.delete('/user/' + user.id).success(function (data) {
                userFactory.getAll().success(function (users) {
                    $scope.users = users;
                });
            });
        }
    };

    $scope.clickEditPassword = function (user) {
        $scope.user = user;
        $scope.user.oldPassword = '';
        $scope.user.newPassword = '';
        $scope.user.newPasswordAgain = '';
    };

    $scope.clickUpdatePassword = function (user) {

        var error = 0;

        // Check passwords
        if (!$scope.user.oldPassword || !$scope.user.newPassword || !$scope.user.newPasswordAgain) { error++; }

        if ($scope.newPassword != $scope.newPasswordAgain) { error++; }

        // Show error if exists
        if (error > 0) {
            alert('All fields are required and passwords must match!');
        }

        // Submit data otherwise
        else {

            $scope.readyUser = {};
            $scope.readyUser.id = $scope.user.id;
            $scope.readyUser.firstName = $scope.user.firstName;
            $scope.readyUser.lastName = $scope.user.lastName;
            $scope.readyUser.category = $scope.user.category;
            $scope.readyUser.username = $scope.user.username;
            $scope.readyUser.password = $scope.user.newPassword;
            $scope.readyUser.type = $scope.user.type;

            $http.put('/user', $scope.readyUser).success(function (data) {
                userFactory.getAll().success(function(users) {
                    $scope.users = users;
                    $('#changePasswordModal').modal('hide');
                });
            });
        }
    };

}]);