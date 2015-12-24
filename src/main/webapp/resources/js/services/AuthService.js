'use strict';

app.service('authService', ['$http', function($http) {

    this.login = function(username, password) {
        return $http.post('/login', { username: username, password: password });
    };

    this.logout = function() {
        return $http.post('/logout');
    };

}]);