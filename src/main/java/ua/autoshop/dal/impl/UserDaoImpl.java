package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.Margin;
import ua.autoshop.model.PriceAutoshop;
import ua.autoshop.model.User;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 08.10.2015.
 */
public class UserDaoImpl implements Dao<User> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public User findByName(String name) {
        try{
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username ='"+name+"'", User.class);
            User u = (User) query.getSingleResult();
            entityManager.getTransaction().commit();
            return u;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void delete(User object) {

    }

    @Override
    public void save(User user) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }

    }

    @Override
    public List<PriceAutoshop> getByPrice(String pattern) {
        return null;
    }

    @Override
    public List<PriceAutoshop> getByCode(String pattern) {
        return null;
    }

    @Override
    public List<PriceAutoshop> getByName(String pattern) {
        return null;
    }

    @Override
    public void sortPriceByArticule() {

    }

    @Override
    public void saveList(List<User> priceList) {

    }

    @Override
    public void cleanTable() {

    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {

    }

    @Override
    public List<User> getAllModelsIterable(int offset, int max) {
        return null;
    }

    @Override
    public void save() {

    }
}
