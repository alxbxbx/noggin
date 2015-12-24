'use strict';

app.controller('AppController', ['$scope', 'userFactory', function ($scope, userFactory) {

    $scope.login = function(username, password) {
        userFactory.getAll.success(function(users) {
            // for loop
        });
    };

}]);