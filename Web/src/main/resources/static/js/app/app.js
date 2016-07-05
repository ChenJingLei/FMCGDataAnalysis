'use strict';

/* App Module */

var FMCGSystemApp = angular.module('FMCGSystemApp', [
  'ngRoute',
  'FMCGSystemControllers'
]);

FMCGSystemApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
        templateUrl: 'partials/main.html',
      }).
      when('/product', {
        templateUrl: 'product/productlist.html',
        controller: 'ProductListCtrl'
      }).
      when('/product/:ProductId', {
        templateUrl: 'product/productdetail.html',
        controller: 'ProductDetailCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);
