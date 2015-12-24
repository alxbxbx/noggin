'use strict';

app.service('authService', ['$http', function($http) {

    this.login = function() {

        var data = {};
        data.username = "firge";
        data.password = "firge";

        $http.post('/login', data).success(function (user) {
            if (!user) {
                alert("Failed to authenticate!");
            } else {
                sessionStorage.setItem("auth_username", user.username);
            }
        });
    };

    this.logout = function() {
        var data = {};
        $http.post('/logout', data).success(function (user) {
            
        });
        sessionStorage.removeItem("auth_username");
    };

}]);