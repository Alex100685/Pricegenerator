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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Пользователь on 19.10.2015.
 */
public class PriceAmperisDaoImpl implements Dao<PriceAmperis> {



    @Autowired
    EntityManager entityManager;


    @Override
    public List<PriceAmperis> findAll() {
        return null;
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceAmperis findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceAmperis object) {

    }

    @Override
    public void save() {


    }

    @Override
    public void saveList(List<PriceAmperis> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceAmperis priceAmperis = (PriceAmperis) price;
                if(priceAmperis.getAvailable()!=null && !priceAmperis.getAvailable().equals("") && !priceAmperis.getAvailable().equals("0")) {
                    entityManager.persist(priceAmperis);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_amperis");
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
        Margin wholesaleMargin = getMarginByName("Амперис ОПТ");

        CsvCreator csvCreator = new CsvCreator();

        List<PriceAmperis> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceAmperis price : priceList)
            {
                CommonVariables.writeToFile = true;
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getName());
                priceAutoshop.setAvailable(price.getAvailable());
                priceAutoshop.setBrand(price.getProductGroup());
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
                priceAutoshop.setSupplier("Амперис");
                priceAutoshop.setShelf("Амперис");
                priceAutoshop.setAdditionalInformation("Доставка в течении 2 часов");
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

    private String  createTrueArticule(PriceAmperis price, CsvCreator csvCreator){
        String articule = price.getFullCode();
        if(articule!=null){
            articule = articule.replaceAll(" ","");
            articule = articule.trim();
            if(price.getProductGroup()!=null) {
                BrandMatcherContent bmc = csvCreator.getBrandMatchesMap().get(price.getProductGroup().trim());
                if (bmc!=null){
                    List <String> attributesToCut = csvCreator.getArticuleMatchesMap().get(price.getProductGroup().trim());
                    for(String attr : attributesToCut){
                        articule = articule.replace(attr, "");
                    }
                }

            }
        }
        return articule;
    }

    @Override
    public List<PriceAmperis> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceAmperis p", PriceAmperis.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceAmperis priceAmperis) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceAmperis);
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
    public PriceAmperis findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public PriceAmperis getColumnMatches(String className) {
        return null;
    }
}
