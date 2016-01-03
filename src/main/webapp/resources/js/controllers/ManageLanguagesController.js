'use strict';

app.controller('ManageLanguagesController', ['$scope', '$http', 'languageFactory', function ($scope, $http, languageFactory) {

    // Get Languages
    languageFactory.getAll().success(function(data) {
        $scope.languages = data;
    });

    // Click Add Language
    $scope.clickAddLanguage = function() {
        $scope.language = {};
        $scope.language.name = '';
    };

    // Click Save Language
    $scope.clickSaveLanguage = function() {
        if (!$scope.language.name.length) {
            alert('Name is not set!');
        } else {
            $http.post('/language', $scope.language).success(function (data) {
                languageFactory.getAll().success(function(languages) {
                    $scope.languages = languages;
                    $('#addLanguageModal').modal('hide');
                });
            });
        }
    };

    // Click Edit Language
    $scope.clickEditLanguage = function(language) {
        $scope.language = language;
    };

    // Click Update Language
    $scope.clickUpdateLanguage = function() {
        $http.put('/language/' + $scope.language.id, $scope.language).success(function(data) {
            languagFactory.getAll().success(function(languages) {
                $scope.languages = languages;
                $('#editLanguageModal').modal('hide');
            });
        });
    };

    // Click Remove Language
    $scope.clickRemoveLanguage = function(language) {
        var check = prompt("Are you sure? (Y/N)");
        if (check.toLowerCase() == "y") {
            $http.delete('/language/' + language.id).success(function (data) {
                languageFactory.getAll().success(function(languages) {
                    $scope.languages = languages;
                    $('#editLanguageModal').modal('hide');
                });
            });
        }
    };

}]);