'use strict';

app.factory('userFactory', ['$http', function ($http) {

    var factory = {};

    factory.getAll = function () {
        return $http.get('/user');
    };

    return factory;

}]);