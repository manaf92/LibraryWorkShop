package se.lexicon.LibraryWorkShop.DAO.interfaces;

import se.lexicon.LibraryWorkShop.models.entity.Details;

import java.util.Collection;

public interface DetailsDAO {
    Details findById(int id);
    Collection<Details> findAll();
    Details creat(Details details);
    Details update(Details details);
    void delete(int id);
}
