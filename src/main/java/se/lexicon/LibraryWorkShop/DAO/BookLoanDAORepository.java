package se.lexicon.LibraryWorkShop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.LibraryWorkShop.DAO.interfaces.BookLoanDAO;
import se.lexicon.LibraryWorkShop.models.entity.BookLoan;
import se.lexicon.LibraryWorkShop.models.entity.Details;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class BookLoanDAORepository implements BookLoanDAO {
    private final EntityManager entityManager;
    @Autowired
    public BookLoanDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public BookLoan findById(int id) {
        return entityManager.find(BookLoan.class,id);
    }

    @Override
    public Collection<BookLoan> findAll() {
        return entityManager.createQuery("SELECT d from BookLoan d ", BookLoan.class)
                .getResultList();
    }

    @Override
    public BookLoan creat(BookLoan bookLoan) {
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    public BookLoan update(BookLoan bookLoan) {
        entityManager.merge(bookLoan);
        return bookLoan;
    }

    @Override
    public void delete(int id) {
        BookLoan bookLoan = findById(id);
        if (bookLoan!= null) entityManager.remove(bookLoan);
    }
}
