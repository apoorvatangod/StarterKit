package pl.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

/**
 * Book controller
 * 
 * @author mmotowid
 *
 */
@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService service;
	
	/*@RequestMapping
	public String list(Model model) {
		return ViewNames.BOOKS;
	}*/
	/**
	 * Method collects info about all books
	 */
	@RequestMapping(value = "/all")
	public ModelAndView allBooks() {
		// TODO: implement method gathering and displaying all books
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("bookList", service.findAllBooks());
		modelAndView.setViewName(ViewNames.BOOKS);
		return modelAndView;
	}

	@RequestMapping(value = "/book")
	public ModelAndView bookDetails(@RequestParam("id") Long id){
		// TODO: here implement methods which displays book info based on query arguments
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("book", service.findBooksById(id).get(0));
		modelAndView.setViewName(ViewNames.BOOK);
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	// TODO: Implement GET / POST methods for "add book" functionality
	public String addBook(Model model) {
		model.addAttribute("newBook", new BookTo());
		return ViewNames.ADD_BOOK;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addBook(@ModelAttribute("newBook") BookTo newBook){
		ModelAndView modelAndView = new ModelAndView();
		service.saveBook(newBook);
		modelAndView.setViewName(ViewNames.ADD_BOOK);
		return modelAndView;
		
	}
	
	/*public ModelAndView addBookDisplay(@ModelAttribute("newBook") BookTo newBook){
	ModelAndView modelAndView = new ModelAndView();
	if(newBook != null && newBook.getAuthors() != null && newBook.getTitle() != null){
		service.saveBook(newBook);
	}
	modelAndView.setViewName(ViewNames.ADD_BOOK);
	return modelAndView;
	
	}*/
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String findBook(Model model){
		model.addAttribute("searchParams", new BookTo());
		return ViewNames.FIND_BOOK;
	}
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView findBook(@ModelAttribute("searchParams") BookTo searchParams){
		ModelAndView modelAndView = new ModelAndView(ViewNames.FOUND_BOOKS);
		modelAndView.addObject("bookList", service.findBooksByTitleAndAuthor(searchParams.getTitle(), searchParams.getAuthors()));
		
		return modelAndView;
	}
	/*@RequestMapping(value = "/find")
	public ModelAndView findBook(@ModelAttribute("searchParams") BookTo searchParams){
		ModelAndView modelAndView = new ModelAndView(ViewNames.FIND_BOOK);//TODO zrobic query
		List<BookTo> foundBooksByAuthor = service.findAllBooks();
		List<BookTo> foundBooksByTitle = service.findAllBooks();
		List<BookTo> foundBooks = service.findAllBooks();
		if(searchParams.getAuthors() != null){
			foundBooksByAuthor = service.findBooksByAuthor(searchParams.getAuthors());
		}
		if(searchParams.getTitle() != null){
			foundBooksByTitle = service.findBooksByTitle(searchParams.getTitle());
		}
		foundBooks.retainAll(foundBooksByAuthor);
		foundBooks.retainAll(foundBooksByTitle);
		if(foundBooks.size() != service.findAllBooks().size()){
			modelAndView = new ModelAndView(ViewNames.FOUND_BOOKS);
			modelAndView.addObject("bookList", foundBooks);
		}
		
		return modelAndView;
	}*/
	
	//domyslna metoda to GET
	@RequestMapping(value = "/delete")
	public String deleteBook(@RequestParam("id") Long id) {
		service.deleteBook(id);
		return ViewNames.DELETE_BOOK;
	}

	/**
	 * Binder initialization
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}
