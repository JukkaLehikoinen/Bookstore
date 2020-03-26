package hh.hommia.Bookstore;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.hommia.Bookstore.webcontroller.BookController;


@SpringBootTest
class BookstoreApplicationTests {
	 @Autowired
	    private BookController controller;

	    @Test
	    public void contexLoads() throws Exception {
	        assertThat(controller).isNotNull();
	        assertThat(controller.bookRest().get(0).getTitle().equals("Lord of the rings"));
	        
	    }	
	}
