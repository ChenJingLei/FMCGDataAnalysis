'use strict';

/* App Module */

var FMCGSystemApp = angular.module('FMCGSystemApp', [
  'ngRoute',
  'FMCGSystemControllers',
  'FMCGSystemServices'
]);

FMCGSystemApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
        templateUrl: 'partials/main.html',
      }).
      when('/product', {
        templateUrl: 'product/product-list.html',
        controller: 'ProductListCtrl'
      }).
      when('/product/:productId', {
        templateUrl: 'product/product-detail.html',
        controller: 'ProductDetailCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);
