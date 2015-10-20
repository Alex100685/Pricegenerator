package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;
import ua.autoshop.utils.marginmaker.MarginMaker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 08.10.2015.
 */
public class PriceVladDaoImpl implements Dao<PriceVlad> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceVlad findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceVlad object) {

    }


    @Override
    public void save() {

    }

    @Override
    public void saveList(List<PriceVlad> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceVlad priceVlad = (PriceVlad) price;
                if(priceVlad.getLeftByDefault()!=null && !priceVlad.getLeftByDefault().equals("") && !priceVlad.getLeftByDefault().equals("0")) {
                    entityManager.persist(priceVlad);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_vlad");
            q.executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {
        int offset = 0;

        List<PriceVlad> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceVlad price : priceList)
            {
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getFullName());
                priceAutoshop.setAvailable(price.getLeftByDefault());
                priceAutoshop.setBrand(price.getBrand());
                priceAutoshop.setCode(price.getArticule());
                Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("Владислав");
                entityManager.persist(priceAutoshop);
            }

            entityManager.flush();
            entityManager.clear();
            entityManager.getTransaction().commit();
            offset += priceList.size();
        }
    }

    @Override
    public List<PriceVlad> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceVlad p", PriceVlad.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceVlad priceVlad) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceVlad);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }

    }


}
