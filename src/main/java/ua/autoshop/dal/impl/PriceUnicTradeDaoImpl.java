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
public class PriceUnicTradeDaoImpl implements Dao<PriceUnicTrade> {

    @Autowired
    EntityManager entityManager;


    @Override
    public List<PriceUnicTrade> findAll() {
        return null;
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceUnicTrade findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceUnicTrade object) {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveList(List<PriceUnicTrade> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceUnicTrade priceUnicTrade = (PriceUnicTrade) price;
                if(priceUnicTrade.getAvailable()!=null && !priceUnicTrade.getAvailable().equals("") && !priceUnicTrade.getAvailable().equals("0")) {
                    entityManager.persist(priceUnicTrade);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_unictrade");
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

        Margin wholesaleMargin = getMarginByName("Юник ТРЕЙД ОПТ");
        CsvCreator csvCreator = new CsvCreator();
        List<PriceUnicTrade> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceUnicTrade price : priceList)
            {
                CommonVariables.writeToFile = true;
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getName());
                priceAutoshop.setAvailable(price.getAvailable());
                priceAutoshop.setBrand(price.getBrand());
                String code = createTrueArticule(price, csvCreator);
                priceAutoshop.setCode(code);
                if(price.getCurrency()!=null && price.getCurrency().contains("EUR")) {
                    Double incomePrice = MarginMaker.getTrueIncomePrice(price.getPrice(), margin);
                    priceAutoshop.setIncomePrice(incomePrice);
                    Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getPrice(), wholesaleMargin);
                    priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
                    priceAutoshop.setWholesalePrice(priceAshopWholesale);
                    Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
                    priceAshop = MarginMaker.roundPrice(priceAshop);
                    priceAutoshop.setRetailPrice(priceAshop);
                }else{
                    Double incomePrice = MarginMaker.getTrueIncomePriceNoCurrency(price.getPrice(), margin);
                    priceAutoshop.setIncomePrice(incomePrice);
                    Double priceAshopWholesale = MarginMaker.addMarginToPriceNoCurrency(price.getPrice(), wholesaleMargin);
                    priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
                    priceAutoshop.setWholesalePrice(priceAshopWholesale);
                    Double priceAshop = MarginMaker.addMarginToPriceNoCurrency(price.getPrice(), margin);
                    priceAshop = MarginMaker.roundPrice(priceAshop);
                    priceAutoshop.setRetailPrice(priceAshop);
                }

                priceAutoshop.setSupplier("Юник ТРЕЙД");
                priceAutoshop.setShelf("Юник ТРЕЙД");
                priceAutoshop.setAdditionalInformation("Доставка в течении 2-3 часов (заказ до 15:00)");
                if(CommonVariables.writeToFile == true) {
                    entityManager.persist(priceAutoshop);
                }
            }

            entityManager.flush();
            entityManager.clear();
            entityManager.getTransaction().commit();
            offset += priceList.size();
        }

    }

    private String  createTrueArticule(PriceUnicTrade price, CsvCreator csvCreator){
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
    public List<PriceUnicTrade> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceUnicTrade p", PriceUnicTrade.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceUnicTrade priceUnicTrade) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceUnicTrade);
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
    public PriceUnicTrade findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public PriceUnicTrade getColumnMatches(String className) {
        return null;
    }
}
