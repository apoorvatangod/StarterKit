package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.filter.BookSearchCriteria;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.service.LibraryService;
import pl.spring.demo.to.BookTo;

@Service
@Transactional(readOnly = true)
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

	@Override
	public List<BookTo> findBooksBySearchCriteria(BookSearchCriteria searchCriteria) {
		if(searchCriteria.getAuthor() == null){
			searchCriteria.setAuthor("");
		}
		if(searchCriteria.getLibraryName() == null){
			searchCriteria.setLibraryName("");
		}
		if(searchCriteria.getTitle() == null){
			searchCriteria.setTitle("");
		}
		
		return BookMapper.map2To(bookRepository.findBookBySearchCriteria(searchCriteria.getAuthor(), searchCriteria.getTitle(), searchCriteria.getLibraryName()));
	}

    
}
