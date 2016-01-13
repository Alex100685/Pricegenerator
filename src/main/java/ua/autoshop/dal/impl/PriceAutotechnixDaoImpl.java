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
public class PriceAutotechnixDaoImpl implements Dao <PriceAutotechnix> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PriceAutotechnix> findAll() {
        return null;
    }

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
    public PriceAutotechnix findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public void saveList(List<PriceAutotechnix> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceAutotechnix priceAutotechnix = (PriceAutotechnix) price;
                if(priceAutotechnix.getAvailableKiev1()!=null && !priceAutotechnix.getAvailableKiev1().equals("") && !priceAutotechnix.getAvailableKiev1().equals("0")) {
                    entityManager.persist(priceAutotechnix);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_autotechnix");
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
        Margin wholesaleMargin = getMarginByName("Автотехникс ОПТ");

        CsvCreator csvCreator = new CsvCreator();
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
                String code = createTrueArticule(price, csvCreator);
                priceAutoshop.setCode(code);
                Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getPrice(), wholesaleMargin);
                priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
                priceAutoshop.setWholesalePrice(priceAshopWholesale);
                Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("Автотехникс");
                priceAutoshop.setShelf("Автотехникс");
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

    private String  createTrueArticule(PriceAutotechnix price, CsvCreator csvCreator){
        String articule = price.getCode();
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
    public List<PriceAutotechnix> getAllModelsIterable(int offset, int max)
    {
        return entityManager.createQuery("SELECT p FROM PriceAutotechnix p", PriceAutotechnix.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }
}
