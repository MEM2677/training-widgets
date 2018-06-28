'use strict';


// Declare app level module which depends on views, and components
var myApp = angular.module('myApp',[]);

myApp.controller('NumberController', ['$scope', '$http', function($scope, $http) {

  $scope.fetchNewNumber = function() {
    $http({
      method: 'GET',
      url: '/training-widgets/legacyapi/rs/en/number.json'
    }).then(function successCallback(response) {
      console.log('fetched new number');
      $scope.number = response.data.response.result.number.output;
    }, function errorCallback(response) {

    });
  }

  $scope.fetchNewNumber();

  $scope.number = null;
}]);
