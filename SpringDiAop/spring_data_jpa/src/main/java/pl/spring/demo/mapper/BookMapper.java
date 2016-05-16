package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class BookMapper {
	
	public BookTo mapToBookToFromBookEntity(BookEntity entity) {
		return new BookTo(entity.getId(), entity.getTitle(), convertStringAuthorsToList(entity.getAuthors()));
	}

	public BookEntity mapToBookEntityFromBookTo(BookTo toObject) {
		return new BookEntity(toObject.getId(), toObject.getTitle(), convertListAuthorsToString(toObject.getAuthors()));
	}

	private String convertListAuthorsToString(List<AuthorTo> authorsTo) {
		String authors = "";
		if(authorsTo != null){
			for (AuthorTo author : authorsTo) {
				authors = authors + author.getFirstName() + " " + author.getLastName() + " ";
			}
		}
		return authors;
	}

	private List<AuthorTo> convertStringAuthorsToList(String authorsString) {
		String[] splitedAutothors = StringUtils.split(authorsString);
		List<AuthorTo> authors = new ArrayList<>();
		for (int i = 0; i < splitedAutothors.length - 1; i += 2) {
			authors.add(new AuthorTo(1L, splitedAutothors[i], splitedAutothors[i + 1]));
		}
		return authors;
	}
}
