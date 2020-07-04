var carServiceApp = angular.module("carServiceApp", ['ngRoute']);


carServiceApp.controller("homeCtrl", function($scope){
	$scope.message = "CarService - Auto servis";
});


carServiceApp.controller("klijentiCtrl", function($scope, $http, $location){

	$scope.klijenti = [];
	$scope.vozila = [];

	$scope.newKlijent = {};
	$scope.newKlijent.ime = "";
	$scope.newKlijent.prezime = "";
	$scope.newKlijent.telefon = "";
	
	
	$scope.searchParams = {};
	$scope.searchParams.ime = {};
	$scope.searchParams.prezime = {};
	$scope.searchParams.telefon = {};
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var klijentiUrl = "/api/klijenti";
	var vozilaUrl = "/api/vozila";
	
   
	var getKlijenti = function(){
		var config = {params: {}};
		
		if($scope.searchParams.ime != ""){
			config.params.ime = $scope.searchParams.ime;
		}
		if($scope.searchParams.prezime != ""){
			config.params.prezime = $scope.searchParams.prezime;
		}
		if($scope.searchParams.telefon != ""){
			config.params.telefon = $scope.searchParams.telefon;
		}
		config.params.pageNum = $scope.pageNum;
		
		$http.get(klijentiUrl, config).then(
			function success(res){
				$scope.klijenti = res.data;
				$scope.totalPages = res.headers('totalPages');
			},
			function error(){
				alert("Neuspešno dobavljanje klijenata.");
			}
		);
	}
	getKlijenti();
	
	var getKlijenti = function(){
		$http.get(klijentiUrl).then(
			function success(res){
				$scope.klijenti = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje klijenata.");
			}
		);
	}
	getKlijenti();
	
	
	
     $scope.doAddKlijent = function(){
            $http.post(klijentiUrl, $scope.newKlijent).then(
                function success(){
                    getKlijenti();

                    $scope.newKlijent = {};
                    $scope.newKlijent.ime = "";
                    $scope.newKlijent.prezime = "";
                    $scope.newKlijent.telefon = "";

                },
                function error(){
                    alert("Neuspešno čuvanje klijenta!");
                }
            );
        }
	
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(klijentiUrl + "/" + id);
		promise.then(
			function success(){
				getKlijenti();
			},
			function error(){
				alert("Neuspešno brisanje klijenta.")
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/klijenti/edit/" + id);
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getKlijenti();
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getKlijenti();
	}
	
    
});

carServiceApp.controller("editKlijentCtrl", function($scope, $http, $routeParams, $location){
	var vozilaUrl = "/api/vozila/" + $routeParams.id;
	var klijentiUrl = "/api/klijenti";

	$scope.searchParams = {};
    $scope.searchParams.ime = "";
    $scope.searchParams.prezime = "";
    $scope.searchParams.telefon = "";

    var getKlijenti = function(){
    	$http.get(klijentiUrl).then(
    		function success(res){
    			$scope.klijenti = res.data;
    		},
    		function error(){
    			alert("Neuspešno dobavljanje klijenata.")
    		}
    	);
    }
    getKlijenti();

})




carServiceApp.controller("vozilaCtrl", function($scope, $http, $location){
	
	$scope.klijenti = [];
	$scope.vozila = [];
	
	$scope.newVozilo = {};
	$scope.newVozilo.godiste = "";
	$scope.newVozilo.gorivo = "";
	$scope.newVozilo.markaModel = "";
	$scope.newVozilo.motTyp = "";
	$scope.newVozilo.registracija = "";
	$scope.newVozilo.klijentId = "";

	$scope.searchParams = {};
	$scope.searchParams.godiste = "";
	$scope.searchParams.gorivo = "";
	$scope.searchParams.markaModel = "";
	$scope.searchParams.motTyp = "";
	$scope.searchParams.registracija = "";
	$scope.searchParams.klijentId = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	
	var vozilaUrl = "/api/vozila";
	var klijentiUrl = "/api/klijenti";
	

	var getVozila = function(){
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
	getVozila();
	
	
	var getKlijenti = function(){
		$http.get(klijentiUrl).then(
			function success(res){
				$scope.klijenti = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje klijenata.");
			}
		);
	}
	getKlijenti();
	
	
	$scope.doAdd = function(){
		$http.post(vozilaUrl, $scope.newVozilo).then(
			function success(){
				getVozila();
				
				$scope.newVozilo = {};
				$scope.newVozilo.godiste = "";
				$scope.newVozilo.gorivo = "";
				$scope.newVozilo.markaModel = "";
				$scope.newVozilo.motTyp = "";
				$scope.newVozilo.registracija = "";
				$scope.klijentId = "";

			},
			function error(){
				alert("Neuspešno čuvanje vozila!");
			}
		);
	}
	
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(vozilaUrl + "/" + id);
		promise.then(
			function success(){
				getVozila();
			},
			function error(){
				alert("Neuspešno brisanje vozila.");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/vozila/edit/" + id);
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getVozila();
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getVozila();
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


carServiceApp.controller("editVoziloCtrl", function($scope, $http, $routeParams, $location){
	
	var voziloUrl = "/api/vozila/" + $routeParams.id;
	var klijentiUrl = "/api/klijenti";

	$scope.klijenti = [];
	$scope.vozila = [];
	
	$scope.searchParams = {};
	$scope.searchParams.godiste = "";
	$scope.searchParams.gorivo = "";
	$scope.searchParams.markaModel = "";
	$scope.searchParams.motTyp = "";
	$scope.searchParams.registracija = "";
	$scope.searchParams.klijentId = "";

	
	
	var getKlijenti = function(){
		$http.get(klijentiUrl).then(
			function success(res){
				$scope.klijenti = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje klijenata.");
			}
		);
	}
	getKlijenti();
	
	
	var getVozilo = function(){
		$http.get(voziloUrl).then(
			function success(res){
				$scope.vozilo = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje vozila.");
			}
		);
	}
	//Ako bismo želeli da radimo kaskadiranje radi omogućavanja ng-selected odabira klijenta,
	//onda bismo ovo morali da prebacimo u success callback pod getKlijenti. Tu je izostavljen
	//taj mehanizam radi jednostavnosti.
	getVozilo();
	
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
		$http.put(voziloUrl, $scope.vozila).then(
			function success(){
				getVozila();
				
				$scope.vozilo = {};
				$scope.vozilo.godiste = "";
				$scope.vozilo.gorivo = "";
				$scope.vozilo.markaModel = "";
				$scope.vozilo.motTyp = "";
				$scope.vozilo.registracija = "";
				$scope.klijentId = "";
				
				$location.path("/vozila");
			},
			function error(){
				alert("Neuspešno čuvanje vozila.");
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
		.when('/vozila', {
			templateUrl : '/app/html/vozila.html',
			controller: 'vozilaCtrl'
		})
		.when('/klijenti', {
			templateUrl : '/app/html/klijenti.html',
			controller: 'klijentiCtrl'
		})
		.when('/vozila/edit/:id', {
			templateUrl : '/app/html/edit-vozila.html'
		})
		.when('/klijenti/edit/:id', {
			templateUrl : '/app/html/edit-klijenti.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
