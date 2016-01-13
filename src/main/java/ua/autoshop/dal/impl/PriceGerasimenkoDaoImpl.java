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
public class PriceGerasimenkoDaoImpl implements Dao<PriceGerasimenko> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PriceGerasimenko> findAll() {
        return null;
    }

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
        Margin wholesaleMargin = getMarginByName("Элит ОПТ");
        CsvCreator csvCreator = new CsvCreator();
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
                String code = createTrueArticule(price, csvCreator);
                priceAutoshop.setCode(code);
                Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getClientPrice(), wholesaleMargin);
                priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
                priceAutoshop.setWholesalePrice(priceAshopWholesale);
                Double priceAshop = MarginMaker.addMarginToPrice(price.getClientPrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("Элит");
                priceAutoshop.setShelf("Элит");
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

    private String  createTrueArticule(PriceGerasimenko price, CsvCreator csvCreator){
        String articule = price.getCatalogNumber();
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

    @Override
    public void sortPriceByArticule() {

    }

    @Override
    public PriceGerasimenko findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

}
