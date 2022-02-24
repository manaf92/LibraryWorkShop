package se.lexicon.LibraryWorkShop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.LibraryWorkShop.models.entity.Details;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@Transactional
public class DetailsDAORepository implements DetailsDAO{
    private final EntityManager entityManager;
    @Autowired
    public DetailsDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Details findById(int id) {
        return entityManager.find(Details.class,id);
    }

    @Override
    public Collection<Details> findAll() {

        return entityManager.createQuery("SELECT d from Details d ", Details.class)
                .getResultList();
    }

    @Override
    public Details creat(Details details) {
         entityManager.persist(details);
        return details;
    }

    @Override
    public Details update(Details details) {
        entityManager.merge(details);
        return details;
    }

    @Override
    public void delete(int id) {
        Details details = findById(id);
        if (details!= null) entityManager.remove(details);
    }
}
