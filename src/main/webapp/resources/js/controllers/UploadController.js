'use strict';

app.controller('UploadController', ['$scope', '$http', 'Upload', '$timeout', 'categoryFactory', 'languageFactory',
    function ($scope, $http, Upload, $timeout, categoryFactory, languageFactory) {

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

    $scope.save = function() {
        if (!$scope.tempFile.title || !$scope.tempFile.keywords) {
            alert('Title and keywords are important!');
        }
        $http.post('/book/permanent', $scope.tempFile).success(function (data) {
            console.log('PERMANENT BOOK STORED SUCCESSFULLY!!!');
        });
    };

    $scope.cancel = function() {

    };

}]);