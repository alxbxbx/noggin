(function(){
	
	var app = angular.module('noggin', []);
	
	app.factory('booksFactory', ['$http', function($http) {
		
		var factory = {};
		
		factory.search = function() {
			return $http.get('search');
		};
		
		return factory;
	
	}]);
	
	app.controller('BooksController', ['$scope', 'booksFactory', function($scope, booksFactory) {
		
		$scope.books = [];
		init();
		function init(){
			booksFactory.search().success(function(data) {
				$scope.books = data;
				console.log($scope.books);
			});
		}
		
	}]);
	
})();