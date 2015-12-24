'use strict';

app.factory('userFactory', ['$http', function ($http) {

    var factory = {};

    factory.getAll = function (username) {
        return $http.get('/user');
    };

    return factory;

}]);