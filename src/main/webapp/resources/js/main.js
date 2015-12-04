(function(){
	
	var app = angular.module('noggin', []);
	
	app.controller('BooksController', function($scope, $http) {
		
		books = "[" +
				"	{" +
				"		\"name\": \"Aca Samuraj\"" +
				"	}," +
				"	{" +
				"		\"name\": \"Filip Doktor\"" +
				"	}," +
				"	{" +
				"		\"name\": \"Nikola Vorinski\"" +
				"	}," +
				"	{" +
				"		\"name\": \"Sasa Ferenc\"" +
				"	}," +
				"	{" +
				"		\"name\": \"Stefan Vuckovic\"" +
				"	}," +
				"	{" +
				"		\"name\": \"Black Painter\"" +
				"	}," +
				"	{" +
				"		\"name\": \"Doktori S Nega\"" +
				"	}" +
				"]";
		
		$scope.books = JSON.parse(books);
		
		console.log($scope.books);
	});
	
})();