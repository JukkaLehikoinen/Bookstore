package hh.hommia.Bookstore.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hh.hommia.Bookstore.domain.Book;
import hh.hommia.Bookstore.domain.BookRepository;


@Controller
public class BookController {
	@Autowired
	private BookRepository repository; 
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
		public String listingBooks(Model model) {	
			model.addAttribute("books", repository.findAll());
			//System.out.println(model);
			return "booklist";
	}
	
	 @RequestMapping(value = "/addbook")
	 	public String addBook(Model model){
		 	model.addAttribute("books", new Book());
		 	return "addbook";
    }   
	
	 @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Book book){
	        repository.save(book);
	        return "redirect:booklist";
	    }    

	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    	repository.deleteById(bookId);
	        return "redirect:../booklist";
	    }
	 @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	 public String editBook(Model model,@PathVariable("id") Long bookId) {
	     	//model.addAttribute("books", repository.findById(bookId));
		 	model.addAttribute("books", repository.findAll());
		 	//System.out.println(model);
	        return "editbook";
	 	}
	 @RequestMapping(value = "/modify", method = RequestMethod.POST)
	    public String modifyBook(@RequestParam(name= "title",required=false) String title,@RequestParam(name= "author",required=false) String author, @RequestParam(name= "year",required=false) int year,@RequestParam(name= "isbn",required=false) String isbn,@RequestParam(name= "price",required=false) double price,@RequestParam(name= "id") Long id, Model model) {
		 	repository.deleteById(id);
		 	repository.save(new Book(title,author, year,isbn,price,null));  // Null on kategoria!!!!!
		 	model.addAttribute("books", repository.findAll());
		 	//System.out.println(title + " " + author + " " + year + " " + isbn + " " + price + " ID:"+id);
	        return "redirect:booklist";
	    }    
	
}



