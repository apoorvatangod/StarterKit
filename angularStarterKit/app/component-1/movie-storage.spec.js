describe('movieStorage test', function() {
	'use strict';

  var movieStorage,
  data = [
      {
          "id": 1,
          "version": 0,
          "genre": "Drama",
          "year": 1994,
          "title": "The Shawshank Redemption",
          "director": "Frank Darabont",
          "production": "USA",
          "score" : 8.8
      },
      {
          "id": 2,
          "version": 0,
          "genre": "Thriller",
          "year": 1972,
          "title": "The Godfather",
          "director": "Francis Ford Coppola",
          "production": "USA",
          "score" : 8.7
      },
      {
          "id": 3,
          "version": 0,
          "genre": "Drama",
          "year": 1999,
          "title": "The Green Mile",
          "director": "Frank Darabont",
          "production": "USA",
          "score" : 8.6
      }
    ];

   beforeEach(module('app.component1'));

   beforeEach(inject(function (_movieStorage_) {
   movieStorage = _movieStorage_;
   }));

   describe('get data method', function () {
     it('should return data length = 3', function () {
       // given

       // when
       movieStorage.store(data);
       var result = movieStorage.get();
       
       // then
       expect(result.length).toEqual(3);
     });
   });
});
