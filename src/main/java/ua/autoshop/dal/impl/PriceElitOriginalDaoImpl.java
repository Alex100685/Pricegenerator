package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;
import ua.autoshop.utils.CommonVariables;
import ua.autoshop.utils.filecreator.BrandMatcherContent;
import ua.autoshop.utils.filecreator.CsvCreator;
import ua.autoshop.utils.marginmaker.MarginMaker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 13.12.2015.
 */
public class PriceElitOriginalDaoImpl implements Dao<PriceElitOriginal> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<PriceElitOriginal> findAll() {
        return null;
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceElitOriginal findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceElitOriginal object) {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveList(List<PriceElitOriginal> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceElitOriginal priceElitOriginal = (PriceElitOriginal) price;
                if(priceElitOriginal.getAvailable()!=null && !priceElitOriginal.getAvailable().equals("") && !priceElitOriginal.getAvailable().equals("0")) {
                    entityManager.persist(priceElitOriginal);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_elit_original");
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
        Margin wholesaleMargin = getMarginByName("Элит ОРИГИНАЛ ОПТ");

        CsvCreator csvCreator = new CsvCreator();
        List<PriceElitOriginal> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceElitOriginal price : priceList)
            {
                CommonVariables.writeToFile = true;
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getName());
                priceAutoshop.setAvailable(price.getAvailable());
                priceAutoshop.setBrand(price.getBrand());
                String code = createTrueArticule(price, csvCreator);
                priceAutoshop.setCode(code);
                Double incomePrice = MarginMaker.getTrueIncomePrice(price.getPrice(), margin);
                priceAutoshop.setIncomePrice(incomePrice);
                Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getPrice(), wholesaleMargin);
                priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
                priceAutoshop.setWholesalePrice(priceAshopWholesale);
                Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("Элит ОРИГИНАЛ");
                priceAutoshop.setShelf("Элит ОРИГИНАЛ");
                priceAutoshop.setAdditionalInformation(price.getSupplyCondition());
                if(CommonVariables.writeToFile == true) {
                    entityManager.persist(priceAutoshop);
                }
                price = null;
            }

            entityManager.flush();
            entityManager.clear();
            entityManager.getTransaction().commit();
            offset += priceList.size();
        }

    }

    private String  createTrueArticule(PriceElitOriginal price, CsvCreator csvCreator){
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
    public List<PriceElitOriginal> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceElitOriginal p", PriceElitOriginal.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceElitOriginal priceElitOriginal) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceElitOriginal);
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
    public PriceElitOriginal findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public PriceElitOriginal getColumnMatches(String className) {
        return null;
    }
}
