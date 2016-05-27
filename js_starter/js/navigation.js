'use strict';
var navigation = (function() {
  var sectionCalculator = document.getElementById("calculatorSection"),
  sectionHttpRequest = document.getElementById("http");
  return {
    init : function() {
      sectionHttpRequest.style.display = "none";
    },
    showCalculator : function() {
        sectionCalculator.style.display = "block";
        sectionHttpRequest.style.display = "none";
    },
    showHttpRequest : function() {
        sectionCalculator.style.display = "none";
        sectionHttpRequest.style.display = "block";
    }
  };
})();
