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
		
		<div class="modal fade" id="loginModal" tabindex="-1" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Login</h4>
					</div>
					<div class="modal-body">
						<form class="form-login" action="login" method="post">
							<div class="input-group">
								<span class="input-group-addon" id="login-username-addon">
									<span class=" glyphicon glyphicon-user"></span>
								</span>
								<input type="username" class="form-control" describedby="login-username-addon" placeholder="Username">
							</div>
							<div class="input-group">
								<span class="input-group-addon" id="login-password-addon">
									<span class=" glyphicon glyphicon-lock"></span>
								</span>
								<input type="password" class="form-control" describedby="login-password-addon" placeholder="Password">
							</div>
							<button type="submit" class="btn btn-default">login?</button>
						</form>
					</div>
					<div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				        <button type="button" class="btn btn-primary">Authenticate</button>
					</div>
		    	</div>
		  	</div>
		</div>
		
		<div id="headground"></div>
		
		<nav class="navbar navbar-default navbar-static-top" role="navigation">
	        <div class="container">
	            <div class="navbar-header">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                    <span class="sr-only">Toggle navigation</span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-education" aria-hidden="true"></span> &nbsp;NOGGIN</a>
	            </div>
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav">
	                    <li><a href="#">About</a></li>
	                    <li><a href="#">Services</a></li>
	                    <li><a href="#">Contact</a></li>
	                </ul>
	                <ul class="nav navbar-nav navbar-right">
		            	<li><a href="../navbar-fixed-top/" data-toggle="modal" data-target="#loginModal"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> Log in</a></li>
			        </ul>
	            </div>
	        </div>
	    </nav>
	    
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h2>Online Books <br/><small>Share your knowledge, it's a way to become immortal</small></h2>
				</div>
			</div>
	        <div class="row makeitboxy">
	        	<div class="col-md-4">
	        		<div class="panel panel-default">
						<div class="panel-heading">Categories</div>
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
	            </div>
	            <div class="col-lg-8">
					<div class="panel panel-default">
						<div class="panel-heading">Available Books</div>
						<table class="table table-striped" ng-controller="BooksController">
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
									<td style="width:100px;text-align:right;">
										<a href="#" class="btn btn-primary btn-xs" role="button">
											<span class="glyphicon glyphicon-download-alt"></span> Download
										</a>
									</td>
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