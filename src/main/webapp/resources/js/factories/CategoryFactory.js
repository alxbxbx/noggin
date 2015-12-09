'use strict';

app.factory('categoryFactory', ['$http', function ($http) {

    var factory = {};

    factory.getAll = function () {
        return $http.get('category');
    };

    return factory;

}]);