package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.filter.BookSearchCriteria;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class LibraryServiceTest {

    @Autowired
    private LibraryService libraryService;
    
    @Autowired
    private BookService bookService;

    @Test
    public void testShouldFindBooksBySearchCriteria() {
        // given
        BookSearchCriteria searchCriteria = new BookSearchCriteria("Trz", "Jan", "Library Tw") ;
        // when
        List<BookTo> bookEnties = libraryService.findBooksBySearchCriteria(searchCriteria);
        // then
        assertEquals(1, bookEnties.size());
    }
    
    @Test
    public void testShouldFindBooksBySearchCriteriaTwoCriteria() {
        // given
        BookSearchCriteria searchCriteria = new BookSearchCriteria("Trz", null, "Library Tw") ;
        // when
        List<BookTo> bookEnties = libraryService.findBooksBySearchCriteria(searchCriteria);
        // then
        assertEquals(1, bookEnties.size());
    }
    
    @Test
    public void testShouldFindBooksBySearchCriteriaOneCriteria() {
        // given
        BookSearchCriteria searchCriteria = new BookSearchCriteria(null, null, "Library Tw") ;
        // when
        List<BookTo> bookEnties = libraryService.findBooksBySearchCriteria(searchCriteria);
        // then
        assertEquals(1, bookEnties.size());
    }
    @Test
    public void testShouldFindBooksBySearchCriteriaNoCriteria() {
        // given
        BookSearchCriteria searchCriteria = new BookSearchCriteria(null, null, null) ;
        // when
        List<BookTo> bookEnties = libraryService.findBooksBySearchCriteria(searchCriteria);
        // then
        assertEquals(3, bookEnties.size());
    }
    
    @Test(expected = ObjectOptimisticLockingFailureException.class)
    @Ignore
    public void testShouldThrowOptimisticLockException(){
    	//given
    	BookTo bookTo = bookService.findAllBooks().get(0);
    	//when
    	bookTo.setTitle("test");
    	bookService.saveBook(bookTo);
    	bookTo.setTitle("inny tytul");
    	bookService.saveBook(bookTo);
    	//then
    }
}
