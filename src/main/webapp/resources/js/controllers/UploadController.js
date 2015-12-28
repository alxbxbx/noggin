'use strict';

app.controller('UploadController', ['$scope', 'Upload', '$timeout', function ($scope, Upload, $timeout) {

    $scope.$watch('file', function() {
        $scope.upload($scope.file);
    });

    $scope.log = '';

    $scope.upload = function(file) {
        console.log(file);
        if (!file.$error) {
            Upload.upload({
                url: '/book',
                data: {
                    file: file
                }
            }).progress(function (event) {
                var progressPercentage = parseInt(100.0 * event.loaded / event.total);
                $scope.log = 'progress: ' + progressPercentage + '% ' +
                    event.config.data.file.name + '\n' + $scope.log;
            }).success(function (data, status, headers, config) {
                $timeout(function() {
                    $scope.log = 'file: ' + config.data.file.name + ', Response: ' + JSON.stringify(data) + '\n' + $scope.log;
                });
            });
        }
    };

}]);