'use strict';

app.factory('bookFactory', ['$http', function ($http) {

    var factory = {};

    factory.search = function () {
        return $http.get('search');
    };

    return factory;

}]);
