'use strict';

app.service('sessionService', ['$http', function ($http) {

    this.get = function(key, value) {
        return sessionStorage.set(key, value);
    };

    this.set = function(key) {
        return sessionStorage.get(key);
    };

    this.destroy = function(key) {
        return sessionStorage.removeItem(key);
    };

}]);