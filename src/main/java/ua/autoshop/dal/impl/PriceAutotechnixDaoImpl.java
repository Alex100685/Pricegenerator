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
public class PriceAutotechnixDaoImpl implements Dao <PriceAutotechnix> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceAutotechnix findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceAutotechnix object) {

    }

    @Override
    public void save() {

    }

    @Override
    public void save(PriceAutotechnix priceAutotechnix) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceAutotechnix);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }

    }

    @Override
    public void saveList(List<PriceAutotechnix> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                entityManager.persist((PriceAutotechnix)price);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_autotechnix");
            q.executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {
        int offset = 0;

        List<PriceAutotechnix> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceAutotechnix price : priceList)
            {
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getName());
                priceAutoshop.setAvailable(price.getAvailableKiev2());
                priceAutoshop.setBrand(price.getBrand());
                priceAutoshop.setCode(price.getCode());
                Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
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
    public List<PriceAutotechnix> getAllModelsIterable(int offset, int max)
    {
        return entityManager.createQuery("SELECT p FROM PriceAutotechnix p", PriceAutotechnix.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }
}
