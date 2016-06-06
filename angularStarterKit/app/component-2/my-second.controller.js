angular.module('app.component1').controller('MySecondController', function($scope, $http, movies, movieStorage){
   'use strict';
   $scope.data = {
     movies : []
   };
   if(movieStorage.get() === undefined){
     angular.copy(movies.data, $scope.data.movies);
     movieStorage.store($scope.data.movies);
   }
   else{
     angular.copy(movieStorage.get(), $scope.data.movies);
   }
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

   $scope.getRowColor = function(movie, clickedMovie){
     if(movie.id !== clickedMovie.id){
       return "#ffffff";
     }
     return "#f5f5f5";
   };
})
