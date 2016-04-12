<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html data-ng-app="siteBlog">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 with AngularJS</title>
<link rel="stylesheet"
	href="/public/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/public/lib/bootstrap/css/bootstrap-glyphicons.css" />
<link rel="stylesheet" href="/public/lib/includes/css/styles.css" />
<script src="/public/lib/includes/js/modernizr-2.6.2.min.js"></script>
</head>
<body >
	<div class="data-ng-cloak">
		<header data-ng-controller="rootController">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Brand</a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#/">Home <span
									class="sr-only">(current)</span></a></li>
							<li><a href="#/customer">Customers</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Dropdown <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="#">Separated link</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="#">One more separated link</a></li>
								</ul></li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
						<ul class="nav navbar-nav  pull-right">
							
							<li data-ng-hide="isLoggedIn()"><a href="#/login">Login</a></li>
							<li data-ng-hide="isLoggedIn()"><a href="#/signup">Sing up</a></li>
							<li class="dropdwon" data-ng-show="isLoggedIn()">

								<button style="margin-top: 8px;"
									class="btn btn-default dropdown-toggle" type="button"
									id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="true">

									<span class="glyphicon glyphicon-user"></span> My Account <span
										class="caret"></span>

								</button>
								<ul class="dropdown-menu">
									<li><a href="#"><span
											class="glyphicon glyphicon-wrench"></span> Settings</a></li>
									<li><a href="#"><span
											class="glyphicon glyphicon-refresh"></span> Update Profile</a></li>
									<li><a href="#"><span
											class="glyphicon glyphicon-briefcase"></span> Billing</a></li>
									<li class="divider"></li>
									<li><a href="#/" data-ng-click="logout()" > <span class="glyphicon glyphicon-off"></span>Sing
											out
									</a></li>
								</ul>
							</li>
						</ul>
						<!-- end of nav pull-right -->
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</header>

		<div class="container" data-ng-view></div>

	</div>


	<!-- Necessary Java Script Files -->

	<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="/public/lib/includes/js/jquery-1.8.2.min.js"><\/script>')
	</script>
	<script src="/public/lib/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script type="text/javascript" src="/public/lib/angular/angular.js"></script>
	<script type="text/javascript"
		src="/public/lib/angular/angular-route.js"></script>
	<script type="text/javascript"
		src="/public/lib/angular/angular-resource.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/include/module.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/include/controllers/rootController.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/include/controllers/customerController.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/include/services/customerService.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/include/services/accountService.js"></script>


</body>
</html>