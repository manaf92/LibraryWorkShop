package se.lexicon.LibraryWorkShop.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.LibraryWorkShop.models.entity.Book;
import se.lexicon.LibraryWorkShop.models.entity.Details;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext

class BookDAORepositoryTest {

    List<Book> books;
    Book b1 ;
    @Autowired
    private BookDAORepository testObject ;
    @Autowired
    private TestEntityManager em;
    @BeforeEach
    void setUp() {
        books = new ArrayList<>(Arrays.asList(
                new Book(0,"book98","Java",30),
                new Book(0,"book100","HTML&CSS",45),
                new Book(0,"book50","C#",15)
        ));
        books.forEach(d-> em.persist(d));
        b1= books.get(0);

    }

    @Test
    void findById() {
        String  expectedIsbn = "book98";
        String actualIsbn = testObject.findById(b1.getBookId()).getIsbn();
        assertEquals(expectedIsbn,actualIsbn);
    }

    @Test
    void findAll() {
        int expectedCount = books.size();
        int actualCount = testObject.findAll().size();
        assertEquals(expectedCount,actualCount);
    }

    @Test
    void creat() {
        Book d4 = new Book(0,"book98","JavaScript",29);
        testObject.creat(d4);
        int expectedCount = books.size()+1;
        int actualCount = testObject.findAll().size();
        assertEquals(expectedCount,actualCount);
    }

    @Test
    void update() {
        Book updatedBook = new Book(b1.getBookId(),"book98","SQL",29);
        testObject.update(updatedBook);
        String expectedTitle = "SQL";
        String actualTitle = testObject.findById(b1.getBookId()).getTitle();
        assertEquals(expectedTitle,actualTitle);
    }

    @Test
    void delete() {
        int expectedCount = books.size()-1;
        testObject.delete(books.get(2).getBookId());
        int actualCount = testObject.findAll().size();
        assertEquals(expectedCount,actualCount);
    }
}