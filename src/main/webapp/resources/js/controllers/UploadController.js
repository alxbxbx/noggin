'use strict';

app.controller('UploadController', ['$scope', 'Upload', '$timeout', 'categoryFactory', 'languageFactory',
    function ($scope, Upload, $timeout, categoryFactory, languageFactory) {

    categoryFactory.getAll().success(function (data) {
        $scope.categories = data;
    });

    languageFactory.getAll().success(function (data) {
        $scope.languages = data;
    });

    $scope.$watch('file', function() {
        $scope.upload($scope.file);
    });

    $scope.log = '';

    $scope.loadingBar = 0;

    $scope.upload = function(file, languageId, categoryId) {
        console.log(file);
        console.log("Language: " + $scope.upload_language);
        console.log("Category: " + $scope.upload_category);
        console.log("User: " + sessionStorage.getItem("auth_id"));
        if (!file.$error) {
            Upload.upload({
                url: '/book',
                data: {
                    file: file,
                    languageId: $scope.upload_language,
                    categoryId: $scope.upload_category,
                    userId: sessionStorage.getItem("auth_id")
                }
            }).progress(function (event) {
                $scope.loadingBar = parseInt(100.0 * event.loaded / event.total);
            }).success(function (data, status, headers, config) {
                $timeout(function() {
                    $scope.loadingBar = 100;
                    $scope.tempFile = data;
                });
            });
        }
    };

}]);