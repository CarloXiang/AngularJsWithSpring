(function() {
	"use strict";
	var accountService = function($resource) {
		this.getName = function() {
			return "Kamol";
		}

		// Should not use 'session' for set item in localStorage. This
		// reference to another local object

		this.login = function(data) {
			localStorage.setItem("loginData", data);
		}
		this.logout = function(data) {
			localStorage.removeItem("loginData");
		}

		this.isLoggedIn = function() {
			return localStorage.getItem("loginData") !== null;
		}

		this.register = function(account, success, failure) {
			var Account = $resource("/rest/accounts");
			// In {}, parameter can be passed.
			Account.save({}, account, success, failure);
		}

		this.userExists = function(account, success, failure) {
			var Account = $resource("/rest/accounts");
			var data = Account.get({
				name : account.name
			}, function() {
				var accounts = data.accounts;
				console.log("Accounts: " + accounts);
				if (accounts.leangth !== 0) {
					console.log("User exists: " + accounts[0]);
					success(accounts[0]);
				} else {
					failure();
				}
			}, failure);
		};

	};

	angular.module('siteBlog').service('accountService', accountService);

}());