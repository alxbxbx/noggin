'use strict';

app.service('loginService', ['$http', function($http) {

    this.login = function() {
        $http.post('/login').success(function (msg) {
            console.log("MSG: " + msg);
        });
    };

}]);