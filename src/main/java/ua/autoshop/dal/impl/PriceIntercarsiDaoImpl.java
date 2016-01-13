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
public class PriceIntercarsiDaoImpl implements Dao<PriceIntercarsi> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PriceIntercarsi> findAll() {
        return null;
    }

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
                PriceIntercarsi priceIntercarsi = (PriceIntercarsi) price;
                if(priceIntercarsi.getAvailableUr1()!=null && !priceIntercarsi.getAvailableUr1().equals("") && !priceIntercarsi.getAvailableUr1().equals("0")) {
                    entityManager.persist(priceIntercarsi);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_intercarsi");
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

        CsvCreator csvCreator = new CsvCreator();
        Margin wholesaleMargin = getMarginByName("Интеркарс ОПТ");
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
                String articule = createTrueArticule(price, csvCreator);
                priceAutoshop.setCode(articule);
                Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getWholesalePrice(), wholesaleMargin);
                priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
                priceAutoshop.setWholesalePrice(priceAshopWholesale);
                Double priceAshop = MarginMaker.addMarginToPrice(price.getWholesalePrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("Интеркарс");
                priceAutoshop.setShelf("Интеркарс");
                priceAutoshop.setAdditionalInformation("Доставка в течении 2 часов");
                entityManager.persist(priceAutoshop);
                price = null;
            }

            entityManager.flush();
            entityManager.clear();
            entityManager.getTransaction().commit();
            offset += priceList.size();
        }
    }

    private String  createTrueArticule(PriceIntercarsi price, CsvCreator csvCreator){
        String articule = price.getArticule();
        if(articule!=null){
            articule = articule.replaceAll(" ","");
            articule = articule.trim();
            if(price.getBrand()!=null) {
                BrandMatcherContent bmc = csvCreator.getBrandMatchesMap().get(price.getBrand().trim());
                if (bmc!=null){
                    List <String> attributesToCut = csvCreator.getArticuleMatchesMap().get(price.getBrand().trim());
                    for(String attr : attributesToCut){
                        articule = articule.replace(attr, "");
                    }
                }

            }
        }
        return articule;
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
    public PriceIntercarsi findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }
}
