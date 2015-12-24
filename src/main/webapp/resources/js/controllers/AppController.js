'use strict';

app.controller('AppController', ['$scope', 'userFactory', 'authService', function ($scope, userFactory, authService) {

    $scope.login = function(username, password) {
        authService.login(username, password).success(function (user) {
            if (!user) {
                alert("Failed to authenticate!");
            } else {
                sessionStorage.setItem("auth", "logged_in");
                sessionStorage.setItem("auth_username", user.username);
                $scope.auth = sessionStorage.getItem("auth");
                $scope.auth_username = sessionStorage.getItem("auth_username");
            }
        });
    };

}]);