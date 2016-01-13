package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 09.10.2015.
 */
public class UpdatesDaoImpl implements Dao<Updates> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Updates> findAll() {
        return null;
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public Updates findByName(String name) {
        try{
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT u FROM Updates u WHERE u.priceName ='"+name+"'", Updates.class);
            Updates u = (Updates) query.getSingleResult();
            entityManager.getTransaction().commit();
            return u;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void delete(Updates object) {

    }


    @Override
    public void save() {

    }

    @Override
    public void save(Updates updates) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(updates);
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
    public Updates findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public void saveList(List<Updates> priceList) {

    }

    @Override
    public void cleanTable() {

    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {

    }

    @Override
    public List<Updates> getAllModelsIterable(int offset, int max) {
        return null;
    }

}
