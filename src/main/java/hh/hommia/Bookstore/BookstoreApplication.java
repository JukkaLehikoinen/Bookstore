package hh.hommia.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.hommia.Bookstore.domain.User;
import hh.hommia.Bookstore.domain.UserRepository;
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
	public CommandLineRunner BookDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("adding couple of awesome books");
			crepository.save(new Category("Vocabulary"));
			crepository.save(new Category("Adventure"));
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Comic"));
			//System.out.println(crepository.findByName("adventure") + " " +crepository.findByName("vocabulary"));
			repository.save(new Book(repository.count(),"Lord of the rings","J.R.R. Tolkien",1954,"978-951-0-3337-2",9.90,crepository.findByName("Adventure").get(0)));
			repository.save(new Book(repository.count(),"Javas from Star Wars","George Lucas",1980,"8642587-11",12.80,crepository.findByName("Vocabulary").get(0)));
			
			
			// Create users: admin/admin user/user
						User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER","user@user.fi");
						User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN","admin@admin.fi");
						urepository.save(user1);
						urepository.save(user2);
						
			
			
			log.info("fetch all books");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
