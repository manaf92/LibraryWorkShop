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
import se.lexicon.LibraryWorkShop.models.entity.Details;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext

class DetailsDAORepositoryTest {

    List<Details> details;
    Details d1 ;
    @Autowired
    private DetailsDAORepository testObject ;
    @Autowired
    private TestEntityManager em;
    @BeforeEach
    void setUp() {
         details = new ArrayList<>(Arrays.asList(
                    new Details(0,"test1@mail.se","test1", LocalDate.parse("1992-01-01")),
                    new Details(0,"test2@mail.se","test2", LocalDate.parse("1994-01-01")),
                    new Details(0,"test3@mail.se","test3", LocalDate.parse("1993-01-01"))
         ));
        details.forEach(d-> em.persist(d));
        d1= details.get(0);

    }

    @Test
    void findById() {
        String  expectedEmail = "test1@mail.se";
        String actualEmail = testObject.findById(d1.getDetailsId()).getEmail();
        assertEquals(expectedEmail,actualEmail);
    }

    @Test
    void findAll() {
        int expectedCount = details.size();
        int actualCount = testObject.findAll().size();
        assertEquals(expectedCount,actualCount);
    }

    @Test
    void creat() {
        Details d4 = new Details(0,"test4@mail.se","test4",LocalDate.parse("1988-01-01"));
        testObject.creat(d4);
        int expectedCount = details.size()+1;

        int actualCount = testObject.findAll().size();

        assertEquals(expectedCount,actualCount);
    }

    @Test
    void update() {
        Details updatedDetails = new Details(d1.getDetailsId(),"update1@mail.se","update1",LocalDate.parse("1988-01-01"));
        testObject.update(updatedDetails);

        String expectedName = "update1";
        String actualName = testObject.findById(d1.getDetailsId()).getName();
        System.out.println(actualName);
        assertEquals(expectedName,actualName);

        testObject.findAll().forEach(System.out::println);

        // To check extra if they are same count of details
        int expectedCount = details.size();

        int actualCount = testObject.findAll().size();

        assertEquals(expectedCount,actualCount);
    }

    @Test
    void delete() {
        int expectedCount = details.size()-1;

        testObject.delete(details.get(2).getDetailsId());

        int actualCount = testObject.findAll().size();

        assertEquals(expectedCount,actualCount);
    }
}