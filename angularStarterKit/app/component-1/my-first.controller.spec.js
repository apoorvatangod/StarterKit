describe('my-first.controller tests', function() {
	'use strict';

	var $scope,
  _movies_ = {
		data : [{
				id: 7,
				version: 0,
				genre: "Drama",
				year: 1994,
				title: "The Shawshank Redemption",
				director: "Frank Darabont",
				production: "USA",
				score : 8.8
		}]
	},
  fakeModal = {
    result: {
        then: function (confirmCallback, cancelCallback) {
            this.confirmCallBack = confirmCallback;
            this.cancelCallback = cancelCallback;
            return this;
        },
        catch: function (cancelCallback) {
            this.cancelCallback = cancelCallback;
            return this;
        },
        finally: function (finallyCallback) {
            this.finallyCallback = finallyCallback;
            return this;
        }
    },
    close: function (item) {
        this.result.confirmCallBack(item);
    },
    dismiss: function (item) {
        this.result.cancelCallback(item);
    },
    finally: function () {
        this.result.finallyCallback();
    }
};

  beforeEach(module('app.component1'));

	beforeEach(inject(function($controller, $rootScope){
		$scope = $rootScope.$new();
		$controller('MyFirstController', {
      $scope: $scope,
      $modal: fakeModal,
      movies : _movies_,
    });
	}));

	describe('test setEditedMovie', function() {
		it('should return id = 7', function() {
			// given
      $scope.editedMovie = {};

			// when
			$scope.setEditedMovie($scope.data.movies[0]);

			// then
      expect($scope.editedMovie.id).toBe(7);
		});
	});

  describe('test getMovieTitle', function() {
		it('should return The Shawshank Redemption', function() {
			// given

			// when
			var result = $scope.getMovieTitle($scope.data.movies[0]);

			// then
      expect(result).toBe("The Shawshank Redemption");
		});
	});

  describe('test isAnyMovieSelectedToEdit', function() {
    it('should return true', function() {
      // given
      $scope.editedMovie = {};

      // when
      $scope.setEditedMovie($scope.data.movies[0]);
      var result = $scope.isAnyMovieSelectedToEdit();

      // then
      expect(result).toBe(true);
    });
  });

  describe('test getMovieById', function() {
    it('should return USA', function() {
      // given

      // when
      var result = $scope.getMovieById(7);

      // then
      expect(result.production).toBe("USA");
    });
  });

  describe('test getMovieTitle', function() {
    it('should return The Shawshank Redemption', function() {
      // given

      // when
      var result = $scope.getMovieTitle($scope.data.movies[0]);

      // then
      expect(result).toBe("The Shawshank Redemption");
    });
  });

  describe('test getRowColor', function() {
    it('should return #f5f5f5', function() {
      // given
      $scope.editedMovie = {};

      // when
      $scope.setEditedMovie($scope.data.movies[0]);
      var result = $scope.getRowColor($scope.data.movies[0], $scope.editedMovie);

      // then
      expect(result).toBe("#f5f5f5");
    });

    it('should return #ffffff', function() {
      // given
      var otherMovie = {
            id: 8,
            version: 0,
            genre: "Drama",
            year: 1994,
            title: "The Shawshank Redemption",
            director: "Frank Darabont",
            production: "USA",
            score : 8.8
      };

      // when
      $scope.setEditedMovie(otherMovie);
      var result = $scope.getRowColor($scope.data.movies[0], otherMovie);
			
      // then
      expect(result).toBe("#ffffff");
    });
  });
});
