package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;
import ua.autoshop.utils.combiner.defaultcolmatches.ColumnMatchesDefaultContext;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class ColumnMatchesDaoImpl implements Dao {

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
        return null;
    }

    @Override
    public void delete(BaseModel object) {

    }

    @Override
    public void save() {

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
    public void save(BaseModel baseModel) {
        entityManager.getTransaction().begin();
        entityManager.persist((ColumnMatches)baseModel);
        entityManager.getTransaction().commit();
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
    public BaseModel findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public ColumnMatches getColumnMatches(String className) {
        ColumnMatches m = null;
        className = className.substring(className.lastIndexOf('.')+1);
        try {
            Query query = entityManager.createQuery("SELECT m FROM ColumnMatches m WHERE m.name ='" + className + "'", ColumnMatches.class);
            m = (ColumnMatches) query.getSingleResult();
            return m;
        }
        catch (NoResultException e){
                m = ColumnMatchesDefaultContext.getStrategy(className).getDefaultColumnMatches(className);
            return m;
        }
    }
}
