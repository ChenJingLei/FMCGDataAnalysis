'use strict';

/* Controllers */

var FMCGSystemControllers = angular.module('FMCGSystemControllers', []);

FMCGSystemControllers.controller('ProductListCtrl', ['$scope', 'Product',
    function ($scope, Product) {
        $scope.products = Product.query();
    }]
);

FMCGSystemControllers.controller('ProductDetailCtrl', ['$scope', '$routeParams', 'Product',
    function ($scope, $routeParams,Product) {
        $scope.products = Product.get({productId:$routeParams.productId},function (product) {
            $scope.product = product;
        })
    }]
);