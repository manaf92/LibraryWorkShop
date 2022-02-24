package se.lexicon.LibraryWorkShop.DAO;

import se.lexicon.LibraryWorkShop.models.entity.Details;

import java.util.Collection;

public interface DetailsDAO {
    Details findById(int id);
    Collection<Details> findAll();
    Details creat(Details details);
    Details update(Details details);
    void delete(int id);
}
