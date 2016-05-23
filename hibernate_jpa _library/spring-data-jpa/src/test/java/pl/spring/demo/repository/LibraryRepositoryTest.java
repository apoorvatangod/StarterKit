package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;
    
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testShouldFindLibraryByName() {
        // given
        final String libraryName = "Library One";
        // when
        List<LibraryEntity> libraryEntities = libraryRepository.findLibraryByName(libraryName);
        // then
        assertEquals(1, libraryEntities.size());
      //TODO jak jest w LibraryEntity Lazy to nie dziala - jak zrobic by dzialala na lazy???? - trzeba dodac transakcje na cala metode testShouldFindLibraryByName
        assertEquals(2, libraryEntities.get(0).getBooks().size());
        assertEquals("Library One", libraryEntities.get(0).getName());
    }
    
    @Test
    public void testShouldFindLibraryByPrefix() {
        // given
        final String libraryName = "libra";
        // when
        List<LibraryEntity> libraryEntities = libraryRepository.findLibraryByName(libraryName);
        // then
        assertFalse(libraryEntities.isEmpty());
    }
    
    @Test
    public void testShouldDeleteLibraryAndBooks() {
        // given
        final String libraryName = "Library Two";
        // when
        libraryRepository.delete(libraryRepository.findLibraryByName(libraryName).get(0));
        List<LibraryEntity> libraryEntities = libraryRepository.findLibraryByName(libraryName);
        List<BookEntity> bookEnties = bookRepository.findBookByTitle("Trzecia książka");
        // then
        assertTrue(libraryEntities.isEmpty());
        assertTrue(bookEnties.isEmpty());
    }

}
