'use strict';

app.controller('AppController', ['$scope', 'authService', function ($scope, authService) {

    $scope.login = function(username, password) {
        authService.login(username, password);
    };

}]);