package se.lexicon.LibraryWorkShop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.LibraryWorkShop.DAO.interfaces.AuthorDAO;
import se.lexicon.LibraryWorkShop.models.entity.Author;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class AuthorDAORepository  implements AuthorDAO {
    private final EntityManager entityManager;
    @Autowired
    public AuthorDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author findById(int id) {
        return entityManager.find(Author.class,id);
    }

    @Override
    public Collection<Author> findAll() {
        return entityManager.createQuery("SELECT a from Author a ", Author.class)
                .getResultList();
    }

    @Override
    public Author creat(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author update(Author author) {
        entityManager.merge(author);
        return author;
    }

    @Override
    public void delete(int id) {
        Author author = findById(id);
        if (author!= null) entityManager.remove(author);
    }
}
