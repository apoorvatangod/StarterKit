'use strict';
var movies = (function() {
    //Sposob podgladniety z przkladowego rozwiazania - najprostszy sposob, brak rozsadnych alternatywnych sposobow - musialbym na sile wymyslac
    //przekombinowane i duzo mniej czytelne sposoby
    var addTableDataTag = function(data) {
            return '<td>' + data + '</td>';
        },
        addTableRowTag = function(data) {
            return '<tr>' + data + '</tr>';
        },
        handleResponse = function(array) {
            var tableData = '';
            for (var i = 0; i < array.length; i++) {
                tableData = tableData + addTableRowTag(addTableDataTag(array[i].title) + addTableDataTag(array[i].year) + addTableDataTag(array[i].genre) +
                addTableDataTag(array[i].production) + addTableDataTag(array[i].director) + addTableDataTag(array[i].score));
            }
            document.getElementById('movieTable').innerHTML = tableData;
        };

    return {
        getMovies: function() {
            var httpReq = new XMLHttpRequest();
            var url = 'movies.json'

            httpReq.onreadystatechange = function() {
                if (httpReq.readyState == 4 && httpReq.status == 200) {
                    var movieArray = JSON.parse(httpReq.responseText);
                    handleResponse(movieArray);
                }
            };
            httpReq.open('GET', url, true);
            httpReq.send();
        }
    };
})();
