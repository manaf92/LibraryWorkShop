package se.lexicon.LibraryWorkShop.DAO.interfaces;

import se.lexicon.LibraryWorkShop.models.entity.Book;
import se.lexicon.LibraryWorkShop.models.entity.BookLoan;

import java.util.Collection;

public interface BookLoanDAO {
    BookLoan findById(int id);
    Collection<BookLoan> findAll();
    BookLoan creat(BookLoan bookLoan);
    BookLoan update(BookLoan bookLoan);
    void delete(int id);
}
