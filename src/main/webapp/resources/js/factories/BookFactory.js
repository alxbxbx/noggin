'use strict';

app.factory('bookFactory', ['$http', function ($http) {

    var factory = {};

    factory.getAll = function () {
        return $http.get('/book');
    };

    return factory;

}]);
