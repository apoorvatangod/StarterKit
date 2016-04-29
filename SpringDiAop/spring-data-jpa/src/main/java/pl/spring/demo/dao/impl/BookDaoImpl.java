package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@Repository // TODO dodane 1.1
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();
	@Autowired
	private BookMapper bookMapper;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookTo> findAll() {
		List<BookTo> allFoundBooks = new ArrayList<>();
		for (BookEntity entity : ALL_BOOKS) {
			allFoundBooks.add(bookMapper.mapToBookToFromBookEntity(entity));
		}
		return allFoundBooks;
	}

	@Override
	public List<BookTo> findBookByTitle(String title) {
		List<BookTo> foundBooks = new ArrayList<>();
		if (title == null || title.length() == 0) {
			return new ArrayList<>();
		}
		for (BookEntity entity : ALL_BOOKS) {
			String entityTitlePartToCompare = "";
			if (entity.getTitle() != null && title.length() <= entity.getTitle().length()) {
				entityTitlePartToCompare = entity.getTitle().substring(0, title.length());
			}
			if (title.equalsIgnoreCase(entityTitlePartToCompare)) {
				foundBooks.add(bookMapper.mapToBookToFromBookEntity(entity));
			}
		}
		return foundBooks;
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		AuthorTo authorTo = separateAuthorNameFromString(author);
		if(authorTo.getFirstName().length() <= 1 && authorTo.getLastName().length() <= 1){
			return new ArrayList<BookTo>();
		}
		List<BookTo> foundBooks = new ArrayList<BookTo>();
		for(BookEntity entity : ALL_BOOKS){
			BookTo bookTo = bookMapper.mapToBookToFromBookEntity(entity);
			
			for(AuthorTo singleAuthor : bookTo.getAuthors()){
				boolean doesFirstNameMatch = authorTo.getFirstName().equalsIgnoreCase(singleAuthor.getFirstName());
				boolean doesLastNameMatch = authorTo.getLastName().equalsIgnoreCase(singleAuthor.getLastName());
				if(doesFirstNameMatch && doesLastNameMatch){
					foundBooks.add(bookTo);
				}
			}
		}
		return foundBooks;
	}

	@Override
	@NullableId
	public BookTo save(BookTo book) throws BookNotNullIdException {
		ALL_BOOKS.add(bookMapper.mapToBookEntityFromBookTo(book));
		return book;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "Wiliamszekspir"));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "HannaOżogowska"));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "Jan Parandowski"));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "Aleksander Fredro"));
	}
	private AuthorTo separateAuthorNameFromString(String author){
		String[] splitedAutothors = StringUtils.split(author);
		if(splitedAutothors.length != 2){
			return new AuthorTo(1L, "", "");
		}
		AuthorTo authorSplitted = new AuthorTo(1L, splitedAutothors[0], splitedAutothors[1]);
		return authorSplitted;
	}
}
