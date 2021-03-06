<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html ng-app="noggin">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Noggin</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="/resources/css/libs/bootstrap-ubuntu.min.css">
		<link rel="stylesheet" type="text/css" href="/resources/css/app.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/libs/font-awesome.min.css">
	</head>
	<body ng-controller="AppController">

		<div ng-include="'/resources/html/modals/login.html'"></div>
		<div ng-include="'/resources/html/modals/change_password.html'"></div>
		<div ng-include="'/resources/html/modals/edit_user.html'"></div>
		<div ng-include="'/resources/html/modals/register.html'"></div>

        <div ng-include="'/resources/html/partials/main_menu.html'"></div>
	    
		<div class="container">

			<div ng-view></div>

            <div ng-include="'/resources/html/partials/footer.html'"></div>

    	</div>
		
		<script type="text/javascript" src="/resources/js/libs/angular.min.js"></script>
		<script type="text/javascript" src="/resources/js/libs/angular-route.min.js"></script>
		<script type="text/javascript" src="/resources/js/libs/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="/resources/js/libs/bootstrap.min.js"></script>

        <script type="text/javascript" src="/resources/js/libs/ng-file-upload-shim.min.js"></script>
        <script type="text/javascript" src="/resources/js/libs/ng-file-upload.min.js"></script>

		<script type="text/javascript" src="/resources/js/app.js"></script>

        <script type="text/javascript" src="/resources/js/services/AuthService.js"></script>

        <script type="text/javascript" src="/resources/js/factories/BookFactory.js"></script>
        <script type="text/javascript" src="/resources/js/factories/CategoryFactory.js"></script>
        <script type="text/javascript" src="/resources/js/factories/UserFactory.js"></script>
        <script type="text/javascript" src="/resources/js/factories/LanguageFactory.js"></script>

		<script type="text/javascript" src="/resources/js/controllers/AppController.js"></script>
		<script type="text/javascript" src="/resources/js/controllers/SearchController.js"></script>
        <script type="text/javascript" src="/resources/js/controllers/ManageBooksController.js"></script>
        <script type="text/javascript" src="/resources/js/controllers/ManageUsersController.js"></script>
        <script type="text/javascript" src="/resources/js/controllers/ManageCategoriesController.js"></script>
		<script type="text/javascript" src="/resources/js/controllers/ManageLanguagesController.js"></script>
		<script type="text/javascript" src="/resources/js/controllers/HomeController.js"></script>

        <script type="text/javascript" src="/resources/js/controllers/UploadController.js"></script>

	</body>
</html>