'use strict';

app.service('loginService', ['$http', function($http) {

    this.post = function(username, password) {

        var data = {};
        data.username = username;
        data.password = password;

        var post = $http.post('/login');
        post.success(function (msg) {
            console.log(msg);
        });
    };

}]);