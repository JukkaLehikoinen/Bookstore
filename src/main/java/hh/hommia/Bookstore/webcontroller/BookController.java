package hh.hommia.Bookstore.webcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import hh.hommia.Bookstore.domain.CategoryRepository;
import hh.hommia.Bookstore.domain.Book;
import hh.hommia.Bookstore.domain.BookRepository;


@Controller
public class BookController {
	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository crepository;
	
	
	 @RequestMapping(value="/")
	    public String gotoLogin() {	
	        return "login";
	    }
	

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
		public String listingBooks(Model model) {	
			model.addAttribute("books", repository.findAll());
		//	System.out.println();
			return "booklist";
	}
	
		//RESTFUL
		 @RequestMapping(value="/books", method = RequestMethod.GET)
	    public @ResponseBody List<Book> bookRest() {	
	        return (List<Book>) repository.findAll();
	    }    

		// RESTful service to get student by id
	    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long BookId) {	
	    	return repository.findById(BookId);
	    }       
	
	 @RequestMapping(value = "/addbook")
	 	public String addBook(Model model){
		 	model.addAttribute("books", new Book());
		 	model.addAttribute("categories", crepository.findAll());
		 	return "addbook";
    }   
	
	 @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Book book){
	        repository.save(book);
	       
	        return "redirect:booklist";
	    }    

	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	 @PreAuthorize("hasAuthority('ADMIN')")
	    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    	repository.deleteById(bookId);
	        return "redirect:../booklist";
	    }
	 @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	 @PreAuthorize("hasAuthority('ADMIN')")
	 public String editBook(Model model,@PathVariable("id") Long bookId) {
	     	//model.addAttribute("books", repository.findById(bookId));
		 	model.addAttribute("books", repository.findAll());
		 	model.addAttribute("categories", crepository.findAll());
		 	String hmm = repository.findById(bookId).get().getCategory().getName();
		 	System.out.println(hmm);
	        return "editbook";
	 	}
	 @RequestMapping(value = "/modify", method = RequestMethod.POST)
	 @PreAuthorize("hasAuthority('ADMIN')")
	    public String modifyBook(@RequestParam(name= "category",required=false) Long cat,@RequestParam(name= "title",required=false) String title,@RequestParam(name= "author",required=false) String author, @RequestParam(name= "year",required=false) int year,@RequestParam(name= "isbn",required=false) String isbn,@RequestParam(name= "price",required=false) double price,@RequestParam(name= "id") Long id, Model model) {
		 	//repository.deleteById(id);
		 	model.addAttribute("books", repository.findAll());
		 //	System.out.println(crepository.findById(cat).get());
		 	
		 	repository.save(new Book(id,title,author, year,isbn,price,crepository.findById(cat).get()));
	        return "redirect:booklist";
	    }    
	
}



