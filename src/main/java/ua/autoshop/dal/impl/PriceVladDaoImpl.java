package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;
import ua.autoshop.utils.filecreator.BrandMatcherContent;
import ua.autoshop.utils.filecreator.CsvCreator;
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
    public List<PriceVlad> findAll() {
        return null;
    }

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

    public Margin getMarginByName(String name) {
        try{
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT m FROM Margin m WHERE m.priceName ='"+name+"'", Margin.class);
            Margin m = (Margin) query.getSingleResult();
            entityManager.getTransaction().commit();
            return m;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {
        int offset = 0;

        Margin wholesaleMargin = getMarginByName("Влад ОПТ");
        CsvCreator csvCreator = new CsvCreator();
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
                String code = createTrueArticule(price, csvCreator);
                priceAutoshop.setCode(code);
                Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getPrice(), wholesaleMargin);
                priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
                priceAutoshop.setWholesalePrice(priceAshopWholesale);
                Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("Владислав");
                priceAutoshop.setShelf("Владислав");
                priceAutoshop.setAdditionalInformation("Доставка в течении 2 часов");
                entityManager.persist(priceAutoshop);
            }

            entityManager.flush();
            entityManager.clear();
            entityManager.getTransaction().commit();
            offset += priceList.size();
        }
    }

    private String  createTrueArticule(PriceVlad price, CsvCreator csvCreator){
        String articule = price.getArticule();
        if(articule!=null){
            articule = articule.trim();
            if(price.getBrand()!=null) {
                BrandMatcherContent bmc = csvCreator.getBrandMatchesMap().get(price.getBrand().trim());
                if (bmc!=null){
                    articule = articule.replace(bmc.getArtCut(), "");
                }

            }
        }
        return articule;
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


}
