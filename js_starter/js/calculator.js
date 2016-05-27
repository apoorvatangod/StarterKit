'use strict';
var calculator = (function() {
  var a = document.getElementById("firstNumber"),
  b = document.getElementById("secondNumber"),
  result = document.getElementById("result");
  return {
    add : function() {
      result.value = calculate.add(parseInt(a.value), parseInt(b.value));
    },
    substract : function() {
        result.value = calculate.substract(parseInt(a.value), parseInt(b.value));
    },
    multiply : function() {
      result.value = calculate.multiply(parseInt(a.value), parseInt(b.value));
    },
    devide : function() {
        result.value = calculate.devide(parseInt(a.value), parseInt(b.value));
    },
    factorial : function() {
      result.value = calculate.factorial(parseInt(a.value));
    }
  };
})();
