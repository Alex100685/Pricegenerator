package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;
import ua.autoshop.utils.marginmaker.MarginMaker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 19.10.2015.
 */
public class PriceAmperisDaoImpl implements Dao<PriceAmperis> {

    @Autowired
    EntityManager entityManager;


    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceAmperis findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceAmperis object) {

    }

    @Override
    public void save() {


    }

    @Override
    public void saveList(List<PriceAmperis> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceAmperis priceAmperis = (PriceAmperis) price;
                if(priceAmperis.getAvailable()!=null && !priceAmperis.getAvailable().equals("") && !priceAmperis.getAvailable().equals("0")) {
                    entityManager.persist(priceAmperis);
                }
            }
            entityManager.getTransaction().commit();
            entityManager.clear();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void cleanTable() {
        try{
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("DELETE FROM price_amperis");
            q.executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {
        int offset = 0;

        List<PriceAmperis> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceAmperis price : priceList)
            {
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getName());
                priceAutoshop.setAvailable(price.getAvailable());
                priceAutoshop.setBrand(price.getProductGroup());
                priceAutoshop.setCode(price.getFullCode());
                Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("Амперис");
                entityManager.persist(priceAutoshop);
                price = null;
            }

            entityManager.flush();
            entityManager.clear();
            entityManager.getTransaction().commit();
            offset += priceList.size();
        }

    }

    @Override
    public List<PriceAmperis> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceAmperis p", PriceAmperis.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceAmperis priceAmperis) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceAmperis);
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
}
