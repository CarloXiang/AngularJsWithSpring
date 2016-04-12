(function(){
	var customerController = function($scope, customerService) {
		$scope.sortBy = 'name';
		$scope.reverse = false;
		$scope.customers = null;

		function init() {
			$scope.customers = customerService.getCustomers();
		}

		init();

		$scope.doSort = function(propName) {
			$scope.sortBy = propName;
			$scope.reverse = !$scope.reverse;
		};
	};
	
	customerController.$inject = ['$scope', 'customerService'];
	
	
	angular.module('siteBlog').controller('customerController', customerController);
//	angular.module('siteBlog').controller('rootController', rootController);
	
}());