package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;
import ua.autoshop.utils.CommonVariables;
import ua.autoshop.utils.combiner.dafaultmargin.DefaultMarginAsgWholesale;
import ua.autoshop.utils.filecreator.BrandMatcherContent;
import ua.autoshop.utils.filecreator.CsvCreator;
import ua.autoshop.utils.marginmaker.MarginMaker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 26.03.2016.
 */

public class AsgModelDaoImpl implements Dao<AsgModel> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<AsgModel> findAll() {
        return null;
    }

    @Override
    public List<PriceAutoshop> findByCode(String code) {
        return null;
    }

    @Override
    public AsgModel findByName(String name) {
        return null;
    }

    @Override
    public void delete(AsgModel object) {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveList(List<AsgModel> priceList) {
        try{
            entityManager.getTransaction().begin();
            for(BaseModel price:priceList) {
                AsgModel asgModel = (AsgModel) price;
                if(asgModel.getAvailable()!=null && !asgModel.getAvailable().equals("") && !asgModel.getAvailable().equals("0")) {
                    entityManager.persist(asgModel);
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
            Query q = entityManager.createNativeQuery("DELETE FROM asg");
            q.executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }

    }

    private String  createTrueArticule(AsgModel price, CsvCreator csvCreator){
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
    public void iterateAllAndSaveToMainTable(Margin margin) {
        int offset = 0;
        Margin wholesaleMargin = getMarginByName("ASG ОПТ");
        CsvCreator csvCreator = new CsvCreator();
        List<AsgModel> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            entityManager.getTransaction().begin();
            for (AsgModel price : priceList)
            {
                CommonVariables.writeToFile = true;
                PriceAutoshop priceAutoshop = new PriceAutoshop();
                priceAutoshop.setName(price.getName());
                priceAutoshop.setAvailable(price.getAvailable());
                priceAutoshop.setBrand(price.getBrand());
                String code = createTrueArticule(price, csvCreator);
                priceAutoshop.setCode(code);
                Double incomePrice = MarginMaker.getTrueIncomePrice(price.getIncomePrice(), margin);
                priceAutoshop.setIncomePrice(incomePrice);
                Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getIncomePrice(), wholesaleMargin);
                priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
                priceAutoshop.setWholesalePrice(priceAshopWholesale);
                Double priceAshop = MarginMaker.addMarginToPrice(price.getIncomePrice(), margin);
                priceAshop = MarginMaker.roundPrice(priceAshop);
                priceAutoshop.setRetailPrice(priceAshop);
                priceAutoshop.setSupplier("ASG");
                priceAutoshop.setShelf("ASG");
                priceAutoshop.setAdditionalInformation("");
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

    @Override
    public List<AsgModel> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM AsgModel p", AsgModel.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public void save(AsgModel asgModel) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(asgModel);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
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
    public AsgModel findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public AsgModel getColumnMatches(String className) {
        return null;
    }
}
