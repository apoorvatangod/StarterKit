package pl.spring.demo.web.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.controller.BookController;
import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "controller-test-configuration.xml")
public class BookControllerTest {

	private MockMvc mockMvc;

	@Autowired
	BookService bookService;
	
	@Before
	public void setup() {
		//Mockito.reset(service);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");
		mockMvc = MockMvcBuilders.standaloneSetup(new BookController()).setViewResolvers(viewResolver).build();
	}

	@Test
	public void testBookAdd() throws Exception {
		// given
		
		//when
		/*Mockito.doAnswer(new Answer<Void>() {
    	    public Void answer(InvocationOnMock invocation) {
				BookTo bookToSave = (BookTo)invocation.getArguments()[0];
				bookToSave = new BookTo(1L, "Title", "Author One", BookStatus.FREE);
    	    	return null;
    	    }
    	}).when(service.saveBook(Mockito.any(BookTo.class)));*/
		when(bookService.saveBook(Mockito.any(BookTo.class))).thenAnswer(new Answer<Void>() {
    	    public Void answer(InvocationOnMock invocation) {
				@SuppressWarnings("unused")
				BookTo bookToSave = (BookTo)invocation.getArguments()[0];
				bookToSave = new BookTo(1L, "Title", "Author One", BookStatus.FREE);
    	    	return null;
    	    }
    	});
		ResultActions resultActions = mockMvc.perform(post("/books/add"));
		// then
		resultActions.andExpect(view().name("addBook"))
		.andExpect(model().attributeExists(ModelConstants.NEW_BOOK))
		.andExpect(model().attribute(ModelConstants.NEW_BOOK, hasProperty("title", equalToIgnoringCase("Title"))));
	}
}
