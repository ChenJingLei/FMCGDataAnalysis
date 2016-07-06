/**
 * Created by cjl20 on 2016/7/6.
 */

/* Services */

var FMCGSystemServices = angular.module('FMCGSystemServices', ['ngResource']);

FMCGSystemServices.factory('Product', ['$resource',
    function($resource){
        return $resource('http://localhost:8088/product/:productId', {}, {
            query: {method:'GET', params:{productId:'getAll'}, isArray:true}
        });
    }]);