/*global angular*/
(function() {
	"use strict";
	var app = angular.module('siteBlog', [ 'ngRoute', 'ngResource' ]);

	app.config(function($routeProvider) {
		$routeProvider.when('/', {
			title : "Home Page",
			controller : 'rootController',
			templateUrl : 'include/views/home.html'
		})
		.when('/login', {
			title : "Login Page",
			controller : 'rootController',
			templateUrl : 'include/views/login.html'
		})
		.when('/signup', {
			title : "Singup Form",
			controller : 'rootController',
			templateUrl : 'include/views/signup.html'
		})
		.when('/customer', {
			title : "Customer page",
			controller : 'customerController',
			templateUrl : 'include/views/customer.html'
		})
		.otherwise({
			redirectTo : '/'
		});
	});

	app.run([ '$rootScope', '$route', function($rootScope, $route) {
		$rootScope.$on('$routeChangeSuccess', function() {
			document.title = $route.current.title;
		});
	} ]);

}());