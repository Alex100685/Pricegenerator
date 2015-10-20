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
public class PriceGenstarDaoImpl implements Dao<PriceGenstar> {

    @Autowired
    EntityManager entityManager;


    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceGenstar findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceGenstar object) {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveList(List<PriceGenstar> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceGenstar priceGenstar = (PriceGenstar) price;
                if(priceGenstar.getAvailable()!=null && !priceGenstar.getAvailable().equals("") && !priceGenstar.getAvailable().equals("0")) {
                    entityManager.persist(priceGenstar);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_genstar");
            q.executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {

        int offset = 0;

        List<PriceGenstar> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceGenstar price : priceList)
            {
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getName());
                priceAutoshop.setAvailable(price.getAvailable());
                priceAutoshop.setBrand(price.getBrand());
                priceAutoshop.setCode(price.getArticule());
                Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("Генстар");
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
    public List<PriceGenstar> getAllModelsIterable(int offset, int max)
    {
        return entityManager.createQuery("SELECT p FROM PriceGenstar p", PriceGenstar.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceGenstar priceGenstar) {

    }
}
