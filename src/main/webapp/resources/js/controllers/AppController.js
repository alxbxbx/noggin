'use strict';

app.controller('AppController', ['$scope', 'userFactory', 'authService', function ($scope, userFactory, authService) {

    $scope.login = function(username, password) {
        authService.login(username, password).success(function (user) {
            if (!user) {
                alert("Failed to authenticate!");
            } else {
                sessionStorage.setItem("auth", "logged_in");
                sessionStorage.setItem("auth_username", user.username);
                sessionStorage.setItem("auth_type", user.type);
                $scope.auth = sessionStorage.getItem("auth");
                $scope.auth_username = sessionStorage.getItem("auth_username");
                $scope.auth_type = sessionStorage.getItem("auth_type");
                $('#loginModal').modal('hide');
            }
        });
    };

    $scope.logout = function() {
        authService.logout().success(function (user) {
            if (!user) {
                alert("Failed to logout!");
            } else {
                sessionStorage.removeItem("auth");
                sessionStorage.removeItem("auth_username");
                sessionStorage.removeItem("auth_type");
            }
        });
    }

    // After page refresh, make sure $scope sees auth data
    if (sessionStorage.getItem("auth") && sessionStorage.getItem("auth_username")) {
        $scope.auth = sessionStorage.getItem("auth");
        $scope.auth_username = sessionStorage.getItem("auth_username");
    }

}]);