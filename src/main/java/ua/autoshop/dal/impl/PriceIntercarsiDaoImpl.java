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
public class PriceIntercarsiDaoImpl implements Dao<PriceIntercarsi> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceIntercarsi findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceIntercarsi object) {

    }


    @Override
    public void save() {

    }

    @Override
    public void saveList(List<PriceIntercarsi> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                entityManager.persist((PriceIntercarsi)price);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_intercarsi");
            q.executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {
        int offset = 0;

        List<PriceIntercarsi> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceIntercarsi price : priceList)
            {
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getName());
                priceAutoshop.setAvailable(price.getAvailableUr1());
                priceAutoshop.setBrand(price.getBrand());
                priceAutoshop.setCode(price.getArticule());
                Double priceAshop = MarginMaker.addMarginToPrice(price.getWholesalePrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
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
    public List<PriceIntercarsi> getAllModelsIterable(int offset, int max) {

        return entityManager.createQuery("SELECT p FROM PriceIntercarsi p", PriceIntercarsi.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceIntercarsi priceIntercarsi) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceIntercarsi);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }

    }
}
