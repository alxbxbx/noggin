'use strict';

app.controller('ManageUsersController', ['$scope', 'userFactory', function ($scope, userFactory) {

    userFactory.getAll().success(function (users) {
       $scope.users = users;
    });

}]);