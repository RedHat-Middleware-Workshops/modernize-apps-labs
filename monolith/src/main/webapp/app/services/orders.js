'use strict';

angular.module("app")

.factory('orders', ['$http', '$q', 'COOLSTORE_CONFIG', 'Auth', '$location', function($http, $q, COOLSTORE_CONFIG, $auth, $location) {
    var factory = {}, orders,baseUrl;

    if(!!COOLSTORE_CONFIG.MONOLITH) {
        baseUrl='/services';
    } else {
        if ($location.protocol() === 'https') {
            baseUrl = (COOLSTORE_CONFIG.SECURE_API_ENDPOINT.startsWith("https://") ? COOLSTORE_CONFIG.SECURE_API_ENDPOINT : "https://" + COOLSTORE_CONFIG.SECURE_API_ENDPOINT + '.' + $location.host().replace(/^.*?\.(.*)/g,"$1")) + '/services';
        } else {
            baseUrl = (COOLSTORE_CONFIG.API_ENDPOINT.startsWith("http://") ? COOLSTORE_CONFIG.API_ENDPOINT : "http://" + COOLSTORE_CONFIG.API_ENDPOINT + '.' + $location.host().replace(/^.*?\.(.*)/g,"$1")) + '/services';
        }
    }

    factory.getOrders = function() {
        
        var deferred = $q.defer();
		$http({
			   method: 'GET',
			   url: baseUrl + '/orders'
		   }).then(function(resp) {
			    orders = resp.data;
			   	deferred.resolve(resp.data);
		   }, function(err) {
			   	deferred.reject(err);
		   });
		return deferred.promise;
    };

    return factory;
}]);
