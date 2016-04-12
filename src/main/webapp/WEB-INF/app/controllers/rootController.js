(function() {
	"use strict";

	var rootController = function($scope, accountService, $location) {
		$scope.myName = accountService.getName();

		$scope.isLoggedIn = function() {
			return accountService.isLoggedIn();
		};
		$scope.aName = "Kamol";

		$scope.login = function() {
			accountService.userExists($scope.account, function(account) {
				accountService.login($scope.account);
				console.log("Logged in with the user: " + $scope.account.name);
				console.log('Login: ' + $scope.isLoggedIn());
				$location.url('/');
			}, function() {
				alert("Error loggin in user");
			});
		};

		$scope.logout = function() {
			accountService.logout();
			console.log('Login: ' + $scope.isLoggedIn());
			$location.url('/');
		};

		$scope.signup = function() {
			accountService.register($scope.account, function(returnedData) {
				accountService.login($scope.account);
				console.log("Sign up with user: " + returnedData.name);
				$location.url('/');
			}, function() {
				alert("Error registering user");
			});

		};

	};

	rootController.inject = [ '$scope', 'accountService', '$location' ];

	angular.module('siteBlog').controller('rootController', rootController);

}());