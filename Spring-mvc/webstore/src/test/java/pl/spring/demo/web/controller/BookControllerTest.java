package pl.spring.demo.web.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.controller.BookController;
import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "controller-test-configuration.xml")
@WebAppConfiguration
public class BookControllerTest {

	@Autowired
	private BookService bookService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		Mockito.reset(bookService);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		BookController bookController = new BookController();
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).setViewResolvers(viewResolver).build();
		// Due to fact, that We are trying to construct real Bean - Book
		// Controller, we have to use reflection to mock existing field book service
		ReflectionTestUtils.setField(bookController, "service", bookService);
	}

	@Test
	public void testAddBookPage() throws Exception {
		// given
		BookTo testBook = new BookTo(1L, "Test title", "Test Author", BookStatus.FREE);
		Mockito.when(bookService.saveBook(Mockito.any())).thenReturn(testBook);

		// TODO: please take a look how we pass @ModelAttribute as a request attribute
		ResultActions resultActions = mockMvc.perform(post("/books/add").flashAttr("newBook", testBook));
		// then
		
		//////////WAZNE!!!! Kolejnosc dodawania w POM musi byc: hamcrest,mockito,junit !!!!!!!!!!!!!!!!!!!!!!!!!! 
		//nie trzeba dodatkowo w dependencymanager ani z wersjami, tylko kolejnosc w dependencies TODO
		resultActions.andExpect(view().name(ViewNames.ADD_BOOK))
				.andExpect(model().attribute(ModelConstants.NEW_BOOK, hasProperty("title", equalToIgnoringCase(testBook.getTitle()))))
				.andExpect(model().attribute(ModelConstants.NEW_BOOK, hasProperty("authors", equalToIgnoringCase(testBook.getAuthors()))))
				.andExpect(model().attribute(ModelConstants.NEW_BOOK, hasProperty("id", equalTo(testBook.getId()))));
		
		/* lub to - wersja alternatywna
		 import org.mockito.ArgumentMatcher;
		 .andExpect(view().name(ViewNames.ADD_BOOK))
				.andExpect(model().attribute("newBook", new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(Object argument) {
						BookTo book = (BookTo) argument;
						return null != book && testBook.getTitle().equals(book.getTitle());
					}
				}))*/

	}
	@Test
	public void testFindBookPage() throws Exception {
		// given
		BookTo testBook = new BookTo(1L, "Test title", "Test Author", BookStatus.FREE);
		Mockito.when(bookService.findBooksByTitleAndAuthor(Mockito.anyString(), Mockito.anyString()))
		.thenReturn(new ArrayList<BookTo> (Arrays.asList(testBook)));

		ResultActions resultActions = mockMvc.perform(post("/books/find").flashAttr("searchParams", testBook));
		// then
		resultActions.andExpect(view().name(ViewNames.FOUND_BOOKS))
		.andExpect(model().attribute(ModelConstants.BOOK_LIST,  Matchers.<BookTo>hasItem(allOf(
			    hasProperty("id", equalTo(testBook.getId())),
			    hasProperty("title", equalToIgnoringCase(testBook.getTitle())),
			    hasProperty("authors", equalToIgnoringCase(testBook.getAuthors()))))));
						
		/* lub to - wersja alternatywna
		 .andExpect(model().attribute(ModelConstants.BOOK_LIST, new ArgumentMatcher<Object>() {
			@Override
			public boolean matches(Object argument) {
				@SuppressWarnings("unchecked")
				List<BookTo> books = (List<BookTo>) argument;
				return null != books && testBook.getTitle().equals(books.get(0).getTitle()) &&
						testBook.getAuthors().equals(books.get(0).getAuthors());
			}
		}))*/				
	}
}