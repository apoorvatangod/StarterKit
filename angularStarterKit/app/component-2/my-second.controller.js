angular.module('app.component1').controller('MySecondController', function($scope, $http, movies){
   'use strict';
   $scope.data = {
     movies : []
   };
   angular.copy(movies.data, $scope.data.movies);
   $scope.clickedMovie = "";

   $scope.setClickedMovie = function(movie){
      if($scope.clickedMovie === movie){
        $scope.clickedMovie = "";
      }else{
        $scope.clickedMovie = movie;
      }
   } ;

   $scope.filterByGenre = function(genre){
    var filteredMovies = [];
    for (var i = 0, len = $scope.data.movies.length; i < len; i++) {
      if($scope.data.movies[i].genre === genre){
          filteredMovies.push($scope.data.movies[i]);
      }
    }
    return filteredMovies;
   };
})
