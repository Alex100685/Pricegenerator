package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 11.12.2015.
 */
public class CommentImpl implements Dao {

    @Autowired
    EntityManager entityManager;

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public BaseModel findByName(String name) {
        try{
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.id ="+1, Comment.class);
            Comment c = (Comment) query.getSingleResult();
            entityManager.getTransaction().commit();
            return c;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void delete(BaseModel object) {

    }

    @Override
    public void save() {

    }

    @Override
    public void save(BaseModel comment) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist((Comment)comment);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void saveList(List priceList) {

    }

    @Override
    public void cleanTable() {

    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {

    }

    @Override
    public List getAllModelsIterable(int offset, int max) {
        return null;
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
    public Comment findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }
}
