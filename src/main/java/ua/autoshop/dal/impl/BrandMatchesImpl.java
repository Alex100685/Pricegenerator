package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 05.12.2015.
 */
public class BrandMatchesImpl implements Dao <BrandMatches> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List <BrandMatches> findAll() {
        try{
            Query query = entityManager.createQuery("SELECT b FROM BrandMatches b");
            List <BrandMatches> matchesList = (List <BrandMatches>) query.getResultList();
            return matchesList;
        } catch(NoResultException e) {
            return null;
        }
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public BrandMatches findByName(String name) {
        try {
            Query query = entityManager.createQuery("SELECT b FROM BrandMatches b WHERE b.priceBrand ='" + name + "'", BrandMatches.class);
            BrandMatches b = (BrandMatches) query.getSingleResult();
            return b;
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void delete(BrandMatches bm) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(bm);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
        }
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
    public void save(BrandMatches brandMatches) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(brandMatches);
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
    public BrandMatches findByThreeParams(String brand, String trueBrand, String cut) {
        try {
            Query query = entityManager.createQuery("SELECT b FROM BrandMatches b WHERE b.priceBrand ='" + brand + "' AND b.priceBrandMatch = '"+trueBrand+"' AND b.cutFromArticule = '"+cut+"'", BrandMatches.class);
            BrandMatches b = (BrandMatches) query.getSingleResult();
            return b;
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    public BrandMatches getColumnMatches(String className) {
        return null;
    }
}
