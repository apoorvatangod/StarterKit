angular.module('app.component1').controller('MyFirstController', function($scope, $http, $modal, movies){
   'use strict';
   $scope.data = {
     movies : []
   };
   angular.copy(movies.data, $scope.data.movies);

   $scope.editedMovie = "";

   $scope.setEditedMovie = function(movie){
      if($scope.editedMovie === movie){
        $scope.editedMovie = "";
      }else{
        $scope.editedMovie = movie;
      }
   };

   $scope.isAnyMovieSelectedToEdit = function(){
      if($scope.editedMovie === ""){
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
         $scope.data.movies.push(movie);
       });

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
