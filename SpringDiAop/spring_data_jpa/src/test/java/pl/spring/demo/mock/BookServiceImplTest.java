package pl.spring.demo.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldSaveBook() {
        // given
        BookTo book = new BookTo(null, "title", 
        		new ArrayList<>(Arrays.asList(new AuthorTo(1L,"author", "1"))));
        Mockito.when(bookDao.save(book)).thenReturn(new BookTo(1L, "title", 
        		new ArrayList<>(Arrays.asList(new AuthorTo(1L,"author", "1")))));
        // when
        BookTo result = bookService.saveBook(book);
        // then
        Mockito.verify(bookDao).save(book);
        assertEquals(1L, result.getId().longValue());
    }
}
