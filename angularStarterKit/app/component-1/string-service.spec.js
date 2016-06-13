describe('stringService test', function() {
	'use strict';

  var stringService, firstString, secondString;

   beforeEach(module('app.component1'));

   beforeEach(inject(function (_stringService_) {
   stringService = _stringService_;
   }));

   describe('addStrings', function () {
     it('should add two strings', function () {
       // given
			 firstString = "Hello";
			 secondString = " world!";
       // when
       var result = stringService.addStrings(firstString, secondString);

       // then
       expect(result).toBe("Hello world!")
     });
   });

	 describe('countChars', function () {
     it('should countChars', function () {
       // given
			 firstString = "Hello world!";
       // when
       var result = stringService.countChars(firstString);

       // then
       expect(result).toBe(12)
     });
   });

	 describe('createSubstring', function () {
     it('should createSubstring', function () {
       // given
			 firstString = "Hello world!";
       // when
       var result = stringService.createSubstring(firstString, 2, 8);

       // then
       expect(result).toBe("llo wo")
     });
   });

	 describe('reverseString', function () {
     it('should reverseString', function () {
       // given
			 firstString = "Hello world!";
       // when
       var result = stringService.reverseString(firstString);

       // then
       expect(result).toBe("!dlrow olleH")
     });
   });

	 describe('replaceWordInString', function () {
     it('should replaceWordInString', function () {
       // given
			 firstString = "Hello world!";
       // when
       var result = stringService.replaceWordInString(firstString, "world", "Poland");

       // then
       expect(result).toBe("Hello Poland!");
     });
   });

	 describe('convertToPokemonWrtiting', function () {
		 it('should convertToPokemonWrtiting', function () {
			 // given
			firstString = "Hello world";
			 // when
			 var result = stringService.convertToPokemonWrtiting(firstString);

			 // then
			 expect(result).toBe("hElLo wOrLd");
		 });
	 });

	 describe('isEqualIngoreCase', function () {
		 it('should return true when equal', function () {
			 // given
			firstString = "Hello world";
			secondString = "hello WorLd";
			 // when
			 var result = stringService.isEqualIngoreCase(firstString, secondString);

			 // then
			 expect(result).toBe(true);
		 });

		 it('should return false when not equal', function () {
			 // given
			firstString = "Hello world";
			secondString = "Hello vorld";
			 // when
			 var result = stringService.isEqualIngoreCase(firstString, secondString);

			 // then
			 expect(result).toBe(false);
		 });
	 });

	 describe('isFirstStringLongerThanSecond', function () {
		 it('should return true when first string is longer', function () {
			 // given
			firstString = "Hello world";
			secondString = "hello W";
			 // when
			 var result = stringService.isFirstStringLongerThanSecond(firstString, secondString);

			 // then
			 expect(result).toBe(true);
		 });

		 it('should return false when second string is longer', function () {
			 // given
			firstString = "Hello wo";
			secondString = "Hello vorld";
			 // when
			 var result = stringService.isFirstStringLongerThanSecond(firstString, secondString);

			 // then
			 expect(result).toBe(false);
		 });
	 });
});
