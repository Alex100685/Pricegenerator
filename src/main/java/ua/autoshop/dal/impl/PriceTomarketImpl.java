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
 * Created by Пользователь on 21.11.2015.
 */
public class PriceTomarketImpl implements Dao<PriceTomarket> {

    @Autowired
    EntityManager entityManager;


    @Override
    public List<PriceTomarket> findAll() {
        return null;
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceTomarket findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceTomarket object) {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveList(List<PriceTomarket> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceTomarket priceTomarket = (PriceTomarket) price;
                if(priceTomarket.getAvailableOnStock()!=null && !priceTomarket.getAvailableOnStock().equals("") && !priceTomarket.getAvailableOnStock().equals("0")) {
                    entityManager.persist(priceTomarket);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_tomarket");
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
        Margin wholesaleMargin = getMarginByName("ТОМАРКЕТ ОПТ");
        int offset = 0;
        CsvCreator csvCreator = new CsvCreator();

        List<PriceTomarket> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceTomarket price : priceList)
            {
                CommonVariables.writeToFile = true;
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getProductName());
                priceAutoshop.setAvailable(price.getAvailableOnStock());
                priceAutoshop.setBrand(price.getBrand());
                String code = createTrueArticule(price, csvCreator);
                if(code!=null){
                    code = code.trim();
                }
                priceAutoshop.setCode(code);
                Double incomePrice = MarginMaker.getTrueIncomePrice(price.getIncomePrice(), margin);
                priceAutoshop.setIncomePrice(incomePrice);
                Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getWholesalePrice(), wholesaleMargin);
                priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
                Double wholesalePriceTomarket = MarginMaker.addWholesalePrice(price.getWholesalePrice(), wholesaleMargin);
                priceAutoshop.setWholesaleToMarket(wholesalePriceTomarket);
                priceAutoshop.setWholesalePrice(priceAshopWholesale);
                priceAutoshop.setCategory(price.getCategoryName());
                Double priceAshop = MarginMaker.addMarginToPrice(price.getIncomePrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                Double retailPriceTomarket = MarginMaker.addWholesalePrice(price.getRetailPrice(), wholesaleMargin);
                priceAutoshop.setRetailTomarket(retailPriceTomarket);
                priceAutoshop.setSupplier("ТОМАРКЕТ");
                priceAutoshop.setShelf(price.getShelfOfProduct());
                priceAutoshop.setPicture(price.getPicture());
                if(CommonVariables.writeToFile == true) {
                    entityManager.persist(priceAutoshop);
                }
            }
            entityManager.clear();
            entityManager.getTransaction().commit();
            offset += priceList.size();
        }

    }

    private String  createTrueArticule(PriceTomarket price, CsvCreator csvCreator){
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
    public List<PriceTomarket> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceTomarket p", PriceTomarket.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceTomarket priceTomarket) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceTomarket);
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
    public PriceTomarket findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public PriceTomarket getColumnMatches(String className) {
        return null;
    }
}
