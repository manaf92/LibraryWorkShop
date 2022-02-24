package se.lexicon.LibraryWorkShop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.lexicon.LibraryWorkShop.DAO.interfaces.AppUserDAO;
import se.lexicon.LibraryWorkShop.models.entity.AppUser;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class AppUserDAORepository implements AppUserDAO {


    private final EntityManager entityManager;
    @Autowired
    public AppUserDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public AppUser findById(int id) {
        return entityManager.find(AppUser.class,id);
    }
    @Override
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("SELECT d from AppUser d ", AppUser.class)
                .getResultList();
    }

    @Override
    public AppUser creat(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    public AppUser update(AppUser appUser) {
        entityManager.merge(appUser);
        return appUser;
    }

    @Override
    public void delete(int id) {
        AppUser appUser = findById(id);
        if (appUser!= null) entityManager.remove(appUser);
    }
}
