'use strict';

app.controller('AppController', ['$scope', 'userFactory', 'authService', function ($scope, userFactory, authService) {

    $scope.login = function(username, password) {
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

    $scope.logout = function() {
        authService.logout().success(function (user) {
            if (!user) {
                alert("Failed to logout!");
            } else {
                sessionStorage.removeItem("auth");
            }
        });
    }

    function setAuthToScope() {
        if (sessionStorage.getItem("auth")) {
            $scope.auth = JSON.parse(sessionStorage.getItem("auth"));
        }
    }

    setAuthToScope();

}]);