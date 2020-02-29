package hh.hommia.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import hh.hommia.Bookstore.domain.Category;
import hh.hommia.Bookstore.domain.CategoryRepository;
import hh.hommia.Bookstore.domain.Book;
import hh.hommia.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner BookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("adding couple of awesome books");
			crepository.save(new Category("Vocabulary"));
			crepository.save(new Category("Adventure"));
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Comic"));
			//System.out.println(crepository.findByName("adventure") + " " +crepository.findByName("vocabulary"));
			repository.save(new Book("Lord of the rings","J.R.R. Tolkien",1954,"978-951-0-3337-2",9.90,crepository.findByName("Adventure").get(0)));
			repository.save(new Book("Javas from Star Wars","George Lucas",1980,"8642587-11",12.80,crepository.findByName("Vocabulary").get(0)));
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
