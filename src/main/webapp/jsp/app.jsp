<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html ng-app="noggin">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Noggin</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="/resources/css/libs/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="/resources/css/app.css">
	</head>
	<body ng-controller="AppController">

		<div ng-include="'/resources/html/modals/login.html'"></div>

		<div ng-include="'/resources/html/modals/register.html'"></div>

		<div id="headground"></div>

        <div ng-include="'/resources/html/partials/main_menu.html'"></div>
	    
		<div class="container">

			<div ng-view></div>

            <div ng-include="'/resources/html/partials/footer.html'"></div>

    	</div>
		
		<script type="text/javascript" src="/resources/js/libs/angular.min.js"></script>
		<script type="text/javascript" src="/resources/js/libs/angular-route.min.js"></script>
		<script type="text/javascript" src="/resources/js/libs/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="/resources/js/libs/bootstrap.min.js"></script>

		<script type="text/javascript" src="/resources/js/app.js"></script>

		<script type="text/javascript" src="/resources/js/controllers/AppController.js"></script>
		<script type="text/javascript" src="/resources/js/controllers/SearchController.js"></script>

		<script type="text/javascript" src="/resources/js/factories/BookFactory.js"></script>
        <script type="text/javascript" src="/resources/js/factories/CategoryFactory.js"></script>
        <script type="text/javascript" src="/resources/js/factories/UserFactory.js"></script>

	</body>
</html>