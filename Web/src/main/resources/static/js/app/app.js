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
      when('/add', {
        templateUrl: 'partials/add.html',
        controller: 'PhoneDetailCtrl'
      }).
      when('/find', {
        templateUrl: 'partials/find.html',
        controller: 'PhoneListCtrl'
      }).
      otherwise({
        // redirectTo: '/main'
      });
  }]);
