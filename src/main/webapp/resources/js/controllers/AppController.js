'use strict';

app.controller('AppController', ['$scope', 'userFactory', 'authService', function ($scope, userFactory, authService) {

    $scope.login = function(username, password) {
        authService.login(username, password);
    };

}]);