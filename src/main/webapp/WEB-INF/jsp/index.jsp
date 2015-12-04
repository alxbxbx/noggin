<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html ng-app="noggin">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Noggin</title>
		<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="resources/css/main.css">
	</head>
	<body>
	
		<div class="fency-line"></div>
		
		<div class="container">
			<nav class="navbar navbar-default" role="navigation">
		        <div class="container-fluid">
		            <div class="navbar-header">
		                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		                    <span class="sr-only">Toggle navigation</span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                </button>
		                <a class="navbar-brand" href="#">Noggin - Book Store</a>
		            </div>
		            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		                <ul class="nav navbar-nav">
		                    <li><a href="#">About</a></li>
		                    <li><a href="#">Services</a></li>
		                    <li><a href="#">Contact</a></li>
		                </ul>
		            </div>
		        </div>
		    </nav>
		
			<div class="row">
				<div class="col-md-12">
					<h2>Browse Books</h2>
				</div>
			</div>
	        <div class="row">
	        	<div class="col-md-4">
	                <ul class="list-group">
					  	<li class="list-group-item">
						    <span class="badge">14</span>
					    	Category Name
						</li>
						<li class="list-group-item">
						    <span class="badge">14</span>
					    	Category Name
						</li>
						<li class="list-group-item">
						    <span class="badge">14</span>
					    	Category Name
						</li>
						<li class="list-group-item">
						    <span class="badge">14</span>
					    	Category Name
						</li>
						<li class="list-group-item">
						    <span class="badge">14</span>
					    	Category Name
						</li>
					</ul>
	            </div>
	            <div class="col-lg-8">
					<div class="panel panel-default">
						<table class="table" ng-controller="BooksController">
							<thead>
								<tr>
									<th>Title</th>
									<th>Author</th>
									<th>Release Date</th>
									<th>Size</th>
									<th>Download</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="5">
										<div class="input-group" style="width:100%">
	                        				<input type="text" class="form-control" placeholder="Enter book name..." ng-model="searchInput">
					                        <span class="input-group-btn">
					                            <button class="btn btn-default" type="button">
					                                <span class="glyphicon glyphicon-filter"></span>
					                        </button>
					                        </span>
					                    </div>
									</td>
								</tr>
								<tr ng-repeat="book in books | filter:searchInput">
									<td>{{ book.name }}</td>
									<td>Philip Jonson</td>
									<td>24.12.1998</td>
									<td>24MB</td>
									<td><a href="#" class="btn btn-primary btn-xs" role="button">Download</a></td>
								</tr>
							</tbody>
						</table>
						<dir-pagination-controls></dir-pagination-controls>
					</div>
	            </div>
	        </div>
	        <hr>
	        <footer>
	            <div class="row">
	                <div class="col-lg-12">
	                    <p>Copyright © Noggin, Developed by Filip and Aleksandar</p>
	                </div>
	            </div>
	        </footer>
    	</div>
		
		<script type="text/javascript" src="resources/js/angular.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="resources/js/main.js"></script>
	</body>
</html>