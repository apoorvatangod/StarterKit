/*package pl.spring.demo.alternatywnaWersja.aop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.spring.demo.alternatywnaWersja.aop.CollectionAware;
import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;
@Repository//TODO dodane 1.1
public class BookDaoImpl implements BookDao, CollectionAware<BookTo> {

    private final Set<BookEntity> ALL_BOOKS = new HashSet<>();
    @Autowired
    private BookMapper bookMapper;

    public BookDaoImpl() {
        addTestBooks();
    }

    @Override
    public List<BookTo> findAll() {
    	List<BookTo> allFoundBooks = new ArrayList<>();
		for(BookEntity entity : ALL_BOOKS){
			allFoundBooks.add(bookMapper.mapToBookToFromBookEntity(entity));
		}
        return allFoundBooks;
    }

    @Override
    public List<BookTo> findBookByTitle(String title) {
        return null;
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return null;
    }

    @Override
    @NullableId
    public BookTo save(BookTo book) throws BookNotNullIdException{
        ALL_BOOKS.add(bookMapper.mapToBookEntityFromBookTo(book));
        return book;
    }
    

    private void addTestBooks() {
        ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "Wiliamszekspir"));
        ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole","HannaOżogowska"));
        ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "Jan Parandowski"));
        ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju","Edmund Niziurski"));
        ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
        ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "Aleksander Fredro"));
    }

	@Override
	public Collection<BookEntity> getCollection() {
		return this.ALL_BOOKS;
	}
}*/
