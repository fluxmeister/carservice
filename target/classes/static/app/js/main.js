var carServiceApp = angular.module("carServiceApp", ['ngRoute']);


carServiceApp.controller("homeCtrl", function($scope){
	$scope.message = "CarService - Auto servis";
});


carServiceApp.controller("clientsCtrl", function($scope, $http, $location){

	$scope.clients = [];
	$scope.vehicles = [];

	$scope.newClient = {};
	$scope.newClient.name = "";
	$scope.newClient.surname = "";
	$scope.newClient.phone = "";
	
	
	$scope.searchParams = {};
	$scope.searchParams.name = {};
	$scope.searchParams.surname = {};
	$scope.searchParams.phone = {};
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var clientsUrl = "/api/clients";
	var vehiclesUrl = "/api/vehicles";
	
   
	var getClients = function(){
		var config = {params: {}};
		
		if($scope.searchParams.name != ""){
			config.params.name = $scope.searchParams.name;
		}
		if($scope.searchParams.surname != ""){
			config.params.surname = $scope.searchParams.surname;
		}
		if($scope.searchParams.phone != ""){
			config.params.phone = $scope.searchParams.phone;
		}
		config.params.pageNum = $scope.pageNum;
		
		$http.get(clientsUrl, config).then(
			function success(res){
				$scope.clients = res.data;
				$scope.totalPages = res.headers('totalPages');
			},
			function error(){
				alert("Unsuccessful getting clients data.");
			}
		);
	}
	getClients();
	
	var getClients = function(){
		$http.get(clientsUrl).then(
			function success(res){
				$scope.clients = res.data;
			},
			function error(){
				alert("Unsuccessful getting clients data.");
			}
		);
	}
	getClients();
	
	
	
     $scope.doAddClient = function(){
            $http.post(clientsUrl, $scope.newClient).then(
                function success(){
                    getClients();

                    $scope.newClient = {};
                    $scope.newClient.name = "";
                    $scope.newClient.surname = "";
                    $scope.newClient.phone = "";

                },
                function error(){
                    alert("Unsuccessful saving clients data!");
                }
            );
        }
	
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(clientsUrl + "/" + id);
		promise.then(
			function success(){
				getClients();
			},
			function error(){
				alert("Unsuccessful deleting client's data.")
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/clients/edit/" + id);
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getClients();
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getClients();
	}
	
    
});

carServiceApp.controller("editClientCtrl", function($scope, $http, $routeParams, $location){
	var vehiclesUrl = "/api/vehicles/" + $routeParams.id;
	var clientsUrl = "/api/clients";

	$scope.searchParams = {};
    $scope.searchParams.name = "";
    $scope.searchParams.surname = "";
    $scope.searchParams.phone = "";

    var getClients = function(){
    	$http.get(clientsUrl).then(
    		function success(res){
    			$scope.clients = res.data;
    		},
    		function error(){
    			alert("Unsuccessful getting clients data.")
    		}
    	);
    }
    getClients();

})




carServiceApp.controller("vehiclesCtrl", function($scope, $http, $location){
	
	$scope.clients = [];
	$scope.vehicles = [];
	
	$scope.newVehicle = {};
	$scope.newVehicle.year = "";
	$scope.newVehicle.fuel = "";
	$scope.newVehicle.brandMake = "";
	$scope.newVehicle.engine = "";
	$scope.newVehicle.plates = "";
	$scope.newVehicle.clientId = "";

	$scope.searchParams = {};
	$scope.searchParams.year = "";
	$scope.searchParams.fuel = "";
	$scope.searchParams.brandMake = "";
	$scope.searchParams.engine = "";
	$scope.searchParams.plates = "";
	$scope.searchParams.clientId = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	
	var vehiclesUrl = "/api/vehicles";
	var clientsUrl = "/api/clients";
	

	var getVehicles = function(){
		var config = {params: {}};		
		
		
		if($scope.searchParams.year != ""){
			config.params.year = $scope.searchParams.year;
		}
		if($scope.searchParams.fuel != ""){
			config.params.fuel = $scope.searchParams.fuel;
		}
		if($scope.searchParams.brandMake != ""){
			config.params.brandMake = $scope.searchParams.brandMake;
		}
		if($scope.searchParams.engine != ""){
			config.params.engine = $scope.searchParams.engine;
		}
		if($scope.searchParams.plates != ""){
			config.params.plates = $scope.searchParams.plates;
		}
		if($scope.searchParams.clientId != ""){
			config.params.clientId = $scope.searchParams.clientId;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		$http.get(vehiclesUrl, config).then(
			function success(res){
				$scope.vehicles = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Unsuccessful getting vehicles data.");
			}
		);
	}
	getVehicles();
	
	
	var getClients = function(){
		$http.get(clientsUrl).then(
			function success(res){
				$scope.clients = res.data;
			},
			function error(){
				alert("Unsuccessful getting clients data.");
			}
		);
	}
	getClients();
	
	
	$scope.doAdd = function(){
		$http.post(vehiclesUrl, $scope.newVehicle).then(
			function success(){
				getVehicles();
				
				$scope.newVehicle = {};
				$scope.newVehicle.year = "";
				$scope.newVehicle.fuel = "";
				$scope.newVehicle.brandMake = "";
				$scope.newVehicle.engine = "";
				$scope.newVehicle.plates = "";
				$scope.clientId = "";

			},
			function error(){
				alert("Unsuccessful saving vehicle's data!");
			}
		);
	}
	
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(vehiclesUrl + "/" + id);
		promise.then(
			function success(){
				getVehicles();
			},
			function error(){
				alert("Unsuccessful deleting vehicle's data.");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/vehicles/edit/" + id);
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getVehicles();
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getVehicles();
	}
	
//	$scope.doReserve = function(id){
//		var promise = $http.post(vozilaUrl + "/" + id);
//		promise.then(
//			function success(){
//				alert("Uspešno.")
//				getVozila();
//			},
//			function error(){
//				alert("Neuspešno.");
//				getVozila();
//			}
//		);
//	}
	
});


carServiceApp.controller("editVehicleCtrl", function($scope, $http, $routeParams, $location){
	
	var vehicleUrl = "/api/vehicles/" + $routeParams.id;
	var clientsUrl = "/api/clients";

	$scope.clients = [];
	$scope.vehicles = [];
	
	$scope.searchParams = {};
	$scope.searchParams.year = "";
	$scope.searchParams.fuel = "";
	$scope.searchParams.brandMake = "";
	$scope.searchParams.engine = "";
	$scope.searchParams.plates = "";
	$scope.searchParams.clientId = "";

	
	
	var getClients = function(){
		$http.get(clientsUrl).then(
			function success(res){
				$scope.clients = res.data;
			},
			function error(){
				alert("Unsuccessful getting clients data.");
			}
		);
	}
	getClients();
	
	
	var getVehicle = function(){
		$http.get(vehicleUrl).then(
			function success(res){
				$scope.vehicle = res.data;
			},
			function error(){
				alert("Unsuccessful getting vehicles data.");
			}
		);
	}
	//Ako bismo želeli da radimo kaskadiranje radi omogućavanja ng-selected odabira klijenta,
	//onda bismo ovo morali da prebacimo u success callback pod getKlijenti. Tu je izostavljen
	//taj mehanizam radi jednostavnosti.
	getVehicle();
	
/*	var getVozila = function(){
		var config = {params: {}};		
		
		
		if($scope.searchParams.godiste != ""){
			config.params.godiste = $scope.searchParams.godiste;
		}
		if($scope.searchParams.gorivo != ""){
			config.params.gorivo = $scope.searchParams.gorivo;
		}
		if($scope.searchParams.markaModel != ""){
			config.params.markaModel = $scope.searchParams.markaModel;
		}
		if($scope.searchParams.motTyp != ""){
			config.params.motTyp = $scope.searchParams.motTyp;
		}
		if($scope.searchParams.registracija != ""){
			config.params.registracija = $scope.searchParams.registracija;
		}
		if($scope.searchParams.klijentId != ""){
			config.params.klijentId = $scope.searchParams.klijentId;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		$http.get(vozilaUrl, config).then(
			function success(res){
				$scope.vozila = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Neupešno dobavljanje vozila.");
			}
		);
	}
	getVozila();*/
	

	
	$scope.doEdit = function(){
		$http.put(vehicleUrl, $scope.vehicles).then(
			function success(){
				getVehicles();
				
				$scope.vehicle = {};
				$scope.vehicle.year = "";
				$scope.vehicle.fuel = "";
				$scope.vehicle.brandMake = "";
				$scope.vehicle.engine = "";
				$scope.vehicle.plates = "";
				$scope.clientId = "";
				
				$location.path("/vehicles");
			},
			function error(){
				alert("Unsuccessful saving vehicles.");
			}
		);
	}
});






carServiceApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: 'homeCtrl'
		})
		.when('/vehicles', {
			templateUrl : '/app/html/vehicles.html',
			controller: 'vehiclesCtrl'
		})
		.when('/clients', {
			templateUrl : '/app/html/clients.html',
			controller: 'clientsCtrl'
		})
		.when('/vehicles/edit/:id', {
			templateUrl : '/app/html/edit-vehicles.html'
		})
		.when('/clients/edit/:id', {
			templateUrl : '/app/html/edit-clients.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
