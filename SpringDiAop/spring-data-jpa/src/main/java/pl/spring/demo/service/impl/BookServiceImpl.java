package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Service//TODO dodane 1.1
public class BookServiceImpl implements BookService {
	@Autowired//TODO dodane 1.1
    private BookDao bookDao;

    @Override
    @Cacheable("booksCache")
    public List<BookTo> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return bookDao.findBookByTitle(title);
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return bookDao.findBooksByAuthor(author);
    }

    @Override
    public BookTo saveBook(BookTo book) throws BookNotNullIdException{//TODO dodane 1.3
        return bookDao.save(book);
    }
    
    /*public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }*/
}
