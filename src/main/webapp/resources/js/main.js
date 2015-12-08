(function(){
	
	var app = angular.module('noggin', ['ngRoute']);
	
	app.factory('booksFactory', ['$http', function($http) {
		
		var factory = {};
		
		factory.search = function() {
			return $http.get('search');
		};
		
		return factory;
	
	}]);
	
	app.controller('BooksController', ['$scope', 'booksFactory', function($scope, booksFactory) {
		
		$scope.books = [];
		
		booksFactory.search().success(function(data) {
			
			$scope.books = data;
			
		});
		
	}]);
	
	app.controller('AdminDashboardController', ['$scope', function($scope) {
		
	}]);
	
	app.config(['$routeProvider', function($routeProvider) {
		$routeProvider.
		when('/dashboard', {
			templateUrl: 'resources/dashboard.html',
			controller: 'AdminDashboardController'
		}).
		otherwise({
			redirectTo: '/dashboard'
		});
	}]);
	
})();