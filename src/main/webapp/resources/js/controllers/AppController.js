'use strict';

app.controller('AppController', ['$scope', 'userFactory', 'authService', function ($scope, userFactory, authService) {

    $scope.login = function(username, password) {
        authService.login(username, password).success(function (user) {
            if (!user) {
                alert("Failed to authenticate!");
            } else {
                sessionStorage.setItem("auth", "true");
                sessionStorage.setItem("auth_username", user.username);
                sessionStorage.setItem("auth_id", user.id);
                sessionStorage.setItem("auth_type", user.type);
                $('#loginModal').modal('hide');
                setAuthToScope();
            }
        });
    };

    $scope.logout = function() {
        authService.logout().success(function (user) {
            if (!user) {
                alert("Failed to logout!");
            } else {
                sessionStorage.removeItem("auth");
                sessionStorage.removeItem("auth_id");
                sessionStorage.removeItem("auth_username");
                sessionStorage.removeItem("auth_type");
            }
        });
    }

    function setAuthToScope() {
        if (sessionStorage.getItem("auth") == "true") {
            $scope.auth = sessionStorage.getItem("auth");
            $scope.auth_username = sessionStorage.getItem("auth_username");
            $scope.auth_id = sessionStorage.getItem("auth_id");
            $scope.auth_type = sessionStorage.getItem("auth_type");
        }
    }

    setAuthToScope();

}]);