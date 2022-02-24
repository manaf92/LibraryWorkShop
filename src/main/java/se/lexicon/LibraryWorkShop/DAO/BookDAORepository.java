package se.lexicon.LibraryWorkShop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.LibraryWorkShop.DAO.interfaces.BookDAO;
import se.lexicon.LibraryWorkShop.models.entity.Book;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class BookDAORepository implements BookDAO {

    private final EntityManager entityManager;
    @Autowired
    public BookDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public Book findById(int id) {
        return entityManager.find(Book.class,id);
    }

    @Override
    public Collection<Book> findAll() {
        return entityManager.createQuery("SELECT d from Book d ", Book.class)
                .getResultList();
    }

    @Override
    public Book creat(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        entityManager.merge(book);
        return book;
    }

    @Override
    public void delete(int id) {
        Book book = findById(id);
        if (book!= null) entityManager.remove(book);
    }
}
