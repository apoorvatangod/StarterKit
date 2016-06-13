angular.module('app.component1').controller('MyFirstController', function($scope, $http, $modal, movies, movieStorage){
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
   $scope.editedMovie = {};

   $scope.setEditedMovie = function(movie){
      if($scope.editedMovie.id === movie.id){
        $scope.editedMovie = {};
      }else{
        angular.copy(movie, $scope.editedMovie);
      }
   };

   $scope.isAnyMovieSelectedToEdit = function(){
      if(!$scope.editedMovie.id){
        return false;
      }
      return true;
   };

   $scope.addMovieModal = function(){
      var modalInstance = $modal.open({
          templateUrl: '/component-1/modal-dialog/modal-dialog-add.tpl.html',
          controller: 'AddMovieModalCtrl',
          size: 'lg'
      });

      modalInstance.result.then(function (newMovie) {
        $scope.data.movies.push(newMovie);
        movieStorage.store($scope.data.movies);
      });

    };

    $scope.editMovieModal = function(){
       var modalInstance = $modal.open({
           templateUrl: '/component-1/modal-dialog/modal-dialog-edit.tpl.html',
           controller: 'EditMovieModalCtrl',
           size: 'lg',
           resolve: {
               movie: function(){
                   return $scope.editedMovie;
               }
           }
       });

       modalInstance.result.then(function (movie) {
         var editedMovie = $scope.getMovieById(movie.id);
         angular.copy(movie, editedMovie);
         movieStorage.store($scope.data.movies);
       });
     };
     $scope.getMovieById = function(id){
       for (var i = 0, len = $scope.data.movies.length; i < len; i++) {
         if($scope.data.movies[i].id === id){
            return $scope.data.movies[i];
         }
       }
       return undefined;
     };

     $scope.getMovieTitle = function(movie){
       if(movie){
         return movie.title;
       }
       return "";
     };

     $scope.getRowColor = function(movie, editedMovie){
       if(movie.id !== editedMovie.id){
         return "#ffffff";
       }
       return "#f5f5f5";
     };

}).controller('AddMovieModalCtrl', function($scope, $modalInstance){
    'use strict';

    $scope.newMovie = {
      "title": undefined,
      "year": undefined,
      "genre": undefined,
      "production": undefined,
      "director": undefined,
      "score": undefined
    };
    $scope.save = function(){
      $modalInstance.close($scope.newMovie);
    };

    $scope.cancel = function(){
      $modalInstance.dismiss("cancel");
    };

})
.controller('EditMovieModalCtrl', function($scope, $modalInstance, movie){
    'use strict';
    $scope.editedMovie = movie;
    $scope.save = function(){
      $modalInstance.close($scope.editedMovie);
    };

    $scope.cancel = function(){
      $modalInstance.dismiss("cancel");
    };
})
.factory('movieStorage', function (){
  var storage = undefined;
  return {
    store: function (movies) { storage = movies; },
    get: function () { return storage; }
  };
})
.service('stringService', function (){
  this.addStrings = function(first, second){
    return first + second;
  };

  this.countChars = function(string){
    return string.length;
  };

  this.createSubstring = function(string, begin, end){
    return string.substring(begin, end);
  };

  this.reverseString = function(string){
    return string.split('').reverse().join('');
  };

  this.replaceWordInString = function(string, wordToBeReplaced, replacingWord){
    return string.replace(wordToBeReplaced, replacingWord);
  };

  this.convertToPokemonWrtiting = function(string){
    var secondString = "";
    for (var i = 0; i < string.length; i++) {
      if(i%2 === 0){
          secondString+=string[i].toLowerCase();
      }else{
        secondString+=string[i].toUpperCase();
      }
    }
    return secondString;
  };

  this.isEqualIngoreCase = function(firstString, secondString){
    for (var i = 0; i < firstString.length; i++) {
      if(firstString[i].toLowerCase() != secondString[i].toLowerCase() || firstString.length != secondString.length){
          return false;
      }
    }
    return true;
  };

  this.isFirstStringLongerThanSecond = function(firstString, secondString){
    return firstString.length > secondString.length;
  };

});
