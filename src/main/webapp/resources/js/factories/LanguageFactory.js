'use strict';

app.factory('languageFactory', ['$http', function ($http) {

    var factory = {};

    factory.getAll = function () {
        return $http.get('/language');
    };

    return factory;

}]);