'use strict';

app.controller('UploadController', ['$window', '$scope', '$http', 'Upload', '$timeout', 'categoryFactory', 'languageFactory',
    function ($window, $scope, $http, Upload, $timeout, categoryFactory, languageFactory) {

    // Get Categories
    categoryFactory.getAll().success(function (data) {
        $scope.categories = data;
    });

    // Get Languages
    languageFactory.getAll().success(function (data) {
        $scope.languages = data;
    });

    // Watch File (Drag & Drop)
    $scope.$watch('file', function() {
        $scope.upload($scope.file);
    });

    // Define Loading Bar
    $scope.loadingBar = 0;

    // Upload File
    $scope.upload = function(file, languageId, categoryId, userId) {
        if (!file.$error) {
            Upload.upload({
                url: '/book',
                data: {
                    file: file,
                    languageId: $scope.upload_language,
                    categoryId: $scope.upload_category,
                    userId: 1
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

    // Save Book
    $scope.save = function() {
        if (!$scope.tempFile.title || !$scope.tempFile.keywords) {
            alert('Title and keywords are important!');
        }
        $http.post('/book/permanent', $scope.tempFile).success(function (data) {
            $window.location.href = "/#/manage/books";
        });
    };

    // Cancel Book
    $scope.cancel = function() {
        $window.location.href = "/";
    };

}]);