(function() {
	var customerService = function() {
		var customers = [ {
			joined : '2000-12-02',
			name : 'John',
			city : 'Chandler',
			orderTotal : 9.9956
		}, {
			joined : '1965-01-25',
			name : 'Zed',
			city : 'Las Vegas',
			orderTotal : 19.99
		}, {
			joined : '1944-06-15',
			name : 'Tina',
			city : 'New York',
			orderTotal : 44.99
		}, {
			joined : '1995-03-28',
			name : 'Kamol',
			city : 'Roy',
			orderTotal : 101.50
		} ]; // End of Var Customers
		
		this.getCustomers = function() {
			return customers;
		}; // End of getCustomers
		
		this.getName = function() {
			return "My Name is Kamol";
		}
		
	}; // End of Test Service
	
	
	angular.module('siteBlog').service('customerService', customerService);
}());