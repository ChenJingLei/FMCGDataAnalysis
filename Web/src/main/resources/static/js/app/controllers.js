'use strict';

/* Controllers */

var FMCGSystemControllers = angular.module('FMCGSystemControllers', []);

FMCGSystemControllers.controller('ProductListCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $http.get('http://localhost:8088/product/getAll').success(function (data) {
            $scope.products = data;
        });
    }]
);

FMCGSystemControllers.controller('ProductDetailCtrl', ['$scope', '$routeParams',
    function ($scope, $routeParams) {
        $scope.ProductId = $routeParams.ProductId;
    }]
);