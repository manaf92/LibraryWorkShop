package se.lexicon.LibraryWorkShop.DAO.interfaces;

import se.lexicon.LibraryWorkShop.models.entity.AppUser;

import java.util.Collection;

public interface AppUserDAO {
    AppUser findById(int id);
    Collection<AppUser> findAll();
    AppUser creat(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(int id);
}
