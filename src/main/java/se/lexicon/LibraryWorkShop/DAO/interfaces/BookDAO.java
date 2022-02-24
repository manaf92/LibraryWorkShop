package se.lexicon.LibraryWorkShop.DAO.interfaces;

import se.lexicon.LibraryWorkShop.models.entity.AppUser;
import se.lexicon.LibraryWorkShop.models.entity.Book;

import java.util.Collection;

public interface BookDAO {
    Book findById(int id);
    Collection<Book> findAll();
    Book creat(Book book);
    Book update(Book book);
    void delete(int id);
}
