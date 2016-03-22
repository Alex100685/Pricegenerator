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
 * Created by Пользователь on 19.10.2015.
 */
public class PriceGenstarDaoImpl implements Dao<PriceGenstar> {

    @Autowired
    EntityManager entityManager;


    @Override
    public List<PriceGenstar> findAll() {
        return null;
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public PriceGenstar findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceGenstar object) {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveList(List<PriceGenstar> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                PriceGenstar priceGenstar = (PriceGenstar) price;
                if(priceGenstar.getAvailable()!=null && !priceGenstar.getAvailable().equals("") && !priceGenstar.getAvailable().equals("0")) {
                    entityManager.persist(priceGenstar);
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
            Query q = entityManager.createNativeQuery("DELETE FROM price_genstar");
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

        Margin wholesaleMargin = getMarginByName("Генстар ОПТ");
        CsvCreator csvCreator = new CsvCreator();
        int offset = 0;

        List<PriceGenstar> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (PriceGenstar price : priceList)
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
                priceAutoshop.setSupplier("Генстар");
                priceAutoshop.setShelf("Генстар");
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

    private String  createTrueArticule(PriceGenstar price, CsvCreator csvCreator){
        String articule = price.getProductCode();
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
    public List<PriceGenstar> getAllModelsIterable(int offset, int max)
    {
        return entityManager.createQuery("SELECT p FROM PriceGenstar p", PriceGenstar.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(PriceGenstar priceGenstar) {

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
    public PriceGenstar findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public PriceGenstar getColumnMatches(String className) {
        return null;
    }
}
