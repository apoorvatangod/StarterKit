describe('my-second.controller tests', function() {
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
		},
		{
				id: 8,
				version: 0,
				genre: "Drama",
				year: 1994,
				title: "The Shawshank Redemption",
				director: "Frank Darabont",
				production: "USA",
				score : 8.8
		},
		{
				id: 9,
				version: 0,
				genre: "Thriller",
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

  beforeEach(module('app.component2'));

	beforeEach(inject(function($controller, $rootScope){
		$scope = $rootScope.$new();
		$controller('MySecondController', {
      $scope: $scope,
      $modal: fakeModal,
      movies : _movies_
    });
	}));

	describe('test setClickedMovie', function() {
		it('should return id = 7', function() {
			// given
      $scope.clickedMovie = {};

			// when
			$scope.setClickedMovie($scope.data.movies[0]);

			// then
      expect($scope.clickedMovie.id).toBe(7);
		});
	});

	describe('test filterByGenre', function() {
		it('should return size = 2', function() {
			// given

			// when
			 var result = $scope.filterByGenre("Drama");

			// then
      expect(result.length).toBe(2);
		});
	});

	describe('test getRowColor', function() {
    it('should return #f5f5f5', function() {
      // given
      $scope.clickedMovie = {};

      // when
      $scope.setClickedMovie($scope.data.movies[0]);
      var result = $scope.getRowColor($scope.data.movies[0], $scope.clickedMovie);

      // then
      expect(result).toBe("#f5f5f5");
    });

    it('should return #ffffff', function() {
      // given

      // when
      var result = $scope.getRowColor($scope.data.movies[0], $scope.data.movies[1]);
      // then
      expect(result).toBe("#ffffff");
    });
  });

});
