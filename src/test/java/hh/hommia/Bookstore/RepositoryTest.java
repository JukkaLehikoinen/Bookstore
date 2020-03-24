package hh.hommia.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.hommia.Bookstore.domain.Book;
import hh.hommia.Bookstore.domain.BookRepository;
import hh.hommia.Bookstore.domain.CategoryRepository;
import hh.hommia.Bookstore.domain.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {
	

	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository crepository;

	@Autowired
	private UserRepository urepository;

  

    @Test
    public void findByISBNandreturnAuthor() {
        List<Book> books = repository.findByIsbn("978-951-0-3337-2");
    	assertThat(books.get(0).getAuthor()).isEqualTo("J.R.R. Tolkien");
    	
    	repository.save(new Book(repository.count(),"Ronja ryövärintytär","Astrid Ingrid",1954,"9565417-44-35",29.90,crepository.findByName("Adventure").get(0)));       
        assertThat(repository.findByIsbn("9565417-44-35").get(0).getId().equals(7));
    	
    }
    @Test
    public void CategoryCheck() {
    	
    	assertThat(crepository.count()).isEqualTo(4);
    	crepository.deleteAll();
    	assertThat(crepository.count()).isEqualTo(0);
    }
    
    @Test
    public void userCheck() {
   	    	assertThat(urepository.findByUsername("user").getEmail().contentEquals("user@user.fi"));
        }    

}