package se.lexicon.LibraryWorkShop.DAO.interfaces;

import se.lexicon.LibraryWorkShop.models.entity.Author;

import java.util.Collection;

public interface AuthorDAO {
    Author findById(int id);
    Collection<Author> findAll();
    Author creat(Author author);
    Author update(Author author);
    void delete(int id);
}
