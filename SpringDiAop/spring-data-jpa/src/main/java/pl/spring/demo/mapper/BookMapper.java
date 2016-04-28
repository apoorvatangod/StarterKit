package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

public class BookMapper {
	public BookTo mapToTo(BookEntity entity) {
		return new BookTo(entity.getId(), entity.getTitle(), convertEntityAuthorsToString(entity.getAuthors()));
	}

	public BookEntity mapToEntity(BookTo toObject) {
		List<AuthorTo> authors = convertEntityAuthorsToString(toObject.getAuthors());
		return new BookEntity(toObject.getId(), toObject.getTitle(), authors);
	}

	private String convertEntityAuthorsToString(List<AuthorTo> authorsTo) {
		String authors = "";
		for (AuthorTo author : authorsTo) {
			authors = authors + author.getFirstName() + " " + author.getLastName() + " ";
		}
		return authors;
	}

	private List<AuthorTo> convertEntityAuthorsToString(String authorsString) {
		String[] splitedAutothors = StringUtils.split(authorsString);
		List<AuthorTo> authors = new ArrayList<>();
		for (int i = 0; i < splitedAutothors.length - 1; i += 2) {
			authors.add(new AuthorTo(1L, splitedAutothors[i], splitedAutothors[i + 1]));
		}
		return authors;
	}
}
