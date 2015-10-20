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
public class PriceGerasimenkoDaoImpl implements Dao<PriceGerasimenko> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceGerasimenko findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceGerasimenko object) {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveList(List<PriceGerasimenko> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceGerasimenko priceGerasimenko = (PriceGerasimenko) price;
                if(priceGerasimenko.getAvailableOnCentralYourBranch()!=null && !priceGerasimenko.getAvailableOnCentralYourBranch().equals("") && !priceGerasimenko.getAvailableOnCentralYourBranch().equals("0")) {
                    entityManager.persist(priceGerasimenko);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_gerasimenko");
            q.executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {
        int offset = 0;

        List<PriceGerasimenko> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceGerasimenko price : priceList)
            {
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getProductDescription());
                priceAutoshop.setAvailable(price.getAvailableOnCentralYourBranch());
                priceAutoshop.setBrand(price.getBrand());
                priceAutoshop.setCode(price.getCatalogNumber());
                Double priceAshop = MarginMaker.addMarginToPrice(price.getClientPrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("Элит");
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
    public List<PriceGerasimenko> getAllModelsIterable(int offset, int max) {

        return entityManager.createQuery("SELECT p FROM PriceGerasimenko p", PriceGerasimenko.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceGerasimenko priceElit) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceElit);
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
