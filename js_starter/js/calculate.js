'use strict';
var calculate = (function() {
  return {
    add : function(a, b) {
	if (isNaN(a) || isNaN(b)) {
		throw new Error('Numbers not valid');
	}
      return a + b;
    },
    substract : function(a, b) {
	if (isNaN(a) || isNaN(b)) {
		throw new Error('Numbers not valid');
	}
      return a - b;
    },
    multiply : function(a, b) {
	if (isNaN(a) || isNaN(b)) {
		throw new Error('Numbers not valid');
	}
      return a * b;
    },
    devide : function(a, b) {
	if (isNaN(a) || isNaN(b) || b === 0) {
		throw new Error('Numbers not valid');
	}
      return a / b;
    },
    factorial : function(a) {
	if (isNaN(a) || a < 0) {
		throw new Error('Number not valid');
	}
      if(a <= 1){
        return 1;
      }
      return a * this.factorial(a - 1);
    }
  };
})();
