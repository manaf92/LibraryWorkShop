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
import se.lexicon.LibraryWorkShop.models.entity.Author;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class AuthorDAORepositoryTest {

    List<Author> authors;
    Author a1;
    @Autowired
    private AuthorDAORepository testObject ;
    @Autowired
    private TestEntityManager em;
    @BeforeEach
    void setUp() {
        authors = new ArrayList<>(Arrays.asList(
                new Author(0,"Martin","svenson"),
                new Author(0,"Ms","Sy"),
                new Author(0,"Jone","Anderson")
        ));
        authors.forEach(d-> em.persist(d));
        a1 = authors.get(0);

    }

    @Test
    void findById() {
        String  expectedName = "Martin";
        String actualName = testObject.findById(a1.getAuthorId()).getFirstName();
        assertEquals(expectedName,actualName);
    }

    @Test
    void findAll() {
        int expectedCount = authors.size();
        int actualCount = testObject.findAll().size();
        assertEquals(expectedCount,actualCount);
    }

    @Test
    void creat() {
        Author a4 = new Author(0,"Haning","Anderson");
        testObject.creat(a4);
        int expectedCount = authors.size()+1;
        int actualCount = testObject.findAll().size();
        assertEquals(expectedCount,actualCount);
    }

    @Test
    void update() {
        Author updatedBook = new Author(a1.getAuthorId(), "Aalam","Aalam");
        testObject.update(updatedBook);
        String expectedFirstName = "Aalam";
        String actualFirstName = testObject.findById(a1.getAuthorId()).getFirstName();
        assertEquals(expectedFirstName,actualFirstName);
    }

    @Test
    void delete() {
        int expectedCount = authors.size()-1;
        testObject.delete(authors.get(2).getAuthorId());
        int actualCount = testObject.findAll().size();
        assertEquals(expectedCount,actualCount);
    }
}