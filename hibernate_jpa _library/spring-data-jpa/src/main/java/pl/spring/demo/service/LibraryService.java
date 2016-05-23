package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.filter.BookSearchCriteria;
import pl.spring.demo.to.BookTo;

public interface LibraryService {

    List<BookTo> findBooksBySearchCriteria(BookSearchCriteria searchCriteria);
}
