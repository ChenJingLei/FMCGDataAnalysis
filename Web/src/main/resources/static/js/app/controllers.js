'use strict';

/* Controllers */

var FMCGSystemControllers = angular.module('FMCGSystemControllers', []);

FMCGSystemControllers.controller('ProductListCtrl', ['$scope', 'Product',
    function ($scope, Product) {
        $scope.products = Product.query();

        $scope.getLocalTime = function (nS) {
            return new Date(nS).toLocaleString().replace(/年|月/g, "-").replace("日","");
        }
    }]
);

FMCGSystemControllers.controller('ProductDetailCtrl', ['$scope', '$routeParams', 'Product',
    function ($scope, $routeParams, Product) {
        $scope.products = Product.get({productId: $routeParams.productId}, function (product) {
            $scope.product = product;

            $scope.getLocalTime = function (nS) {
                return new Date(nS).toLocaleString().replace(/年|月/g, "-").replace("日","");
            }
        })
    }]
);