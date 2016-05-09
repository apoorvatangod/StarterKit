package pl.spring.demo.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        cacheManager.getCache("booksCache").clear();
    }

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(7, allBooks.size());
    }

    @Test
    @Ignore
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException(){
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");

        
    }
    @Test
    public void testShouldReturnSevenAsNewBookId(){
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(null);
        // when
        bookService.saveBook(bookToSave);
        // then
        Long id = bookToSave.getId();
        assertEquals(Long.valueOf(7), id);
        
    }
    @Test
    public void testShouldFindOneBookByTitle(){
        // given
    	String title = "rom";
        // when
        List<BookTo> foundBooks = bookService.findBooksByTitle(title);
        // then
        assertEquals(1, foundBooks.size());
        
    }
    @Test
    public void testShouldNotFindAnyBookByTitle(){
        // given
    	String title = "Bob";
        // when
        List<BookTo> foundBooks = bookService.findBooksByTitle(title);
        // then
        assertTrue(foundBooks.isEmpty());
        
    }
    @Test
    public void testShouldFindOneBookByAuthor(){
        // given
    	String authorName = "Edmun Niziursk";
        // when
        List<BookTo> foundBooks = bookService.findBooksByAuthor(authorName);
        // then
        assertEquals(1, foundBooks.size());
        
    }
    @Test
    public void testShouldNotFindAnyBookByAuthor(){
        // given
    	String authorName = "John Smith";
        // when
        List<BookTo> foundBooks = bookService.findBooksByAuthor(authorName);
        // then
        assertTrue(foundBooks.isEmpty());
        
    }
}
