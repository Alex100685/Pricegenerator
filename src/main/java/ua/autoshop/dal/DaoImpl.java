package ua.autoshop.dal;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.annotation.EntityManagerTransaction;
import ua.autoshop.model.*;
import ua.autoshop.utils.CommonVariables;
import ua.autoshop.utils.filecreator.BrandMatcherContent;
import ua.autoshop.utils.filecreator.CsvCreator;
import ua.autoshop.utils.marginmaker.MarginMaker;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 19.11.2016.
 */
public abstract class DaoImpl <T extends BaseModel> implements Dao <T> {

    @Autowired
    protected EntityManager entityManager;


    @Override
    public List <T> findAll() {
        try{
            Query query = entityManager.createQuery("SELECT b FROM " +getEnityClassName()+ " b");
            return (List<T>)query.getResultList();
        } catch(NoResultException e) {
            return null;
        }
    }

    @Override
    public List<T> findByCode(String code) {
        return null;
    }

    @Override
    public T findByName(String name) {
        return null;
    }

    @EntityManagerTransaction
    @Override
    public void delete(T object) {
        entityManager.remove(object);
    }

    @Override
    public void save() {

    }

    @EntityManagerTransaction
    @Override
    public void saveList(List <T> priceList) {
            for(T price:priceList) {
                T t = price;
                if(conditionToSave(t)) {
                    entityManager.persist(t);
                }
            }
    }

    @EntityManagerTransaction
    @Override
    public void cleanTable() {
            Query q = entityManager.createNativeQuery("DELETE FROM "+getTableName());
            q.executeUpdate();
    }

    public Margin getMarginByName(String name) {
        Query query = entityManager.createQuery("SELECT m FROM Margin m WHERE m.priceName ='"+name+"'", Margin.class);
        return  (Margin) query.getSingleResult();
    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {
        int offset = 0;
        Margin wholesaleMargin = getMarginByName(getWholeSaleMarginName());

        CsvCreator csvCreator = new CsvCreator();
        List<T> priceList;
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            savePriceItems(priceList, margin, wholesaleMargin, csvCreator);
            offset += priceList.size();
        }

    }

    @EntityManagerTransaction
    @Override
    public void save(T t) {
        entityManager.persist(t);
    }

    @Override
    public List<T> getByPrice(String pattern) {
        return null;
    }

    @Override
    public List<T> getByCode(String pattern) {
        return null;
    }

    @Override
    public List<T> getByName(String pattern) {
        return null;
    }

    @Override
    public void sortPriceByArticule() {

    }

    @Override
    public T findByThreeParams(String brand, String trueBrand, String cut) {
        return null;
    }

    @Override
    public T getColumnMatches(String className) {
        return null;
    }

    @Override
    public abstract List getAllModelsIterable(int offset, int max);

    protected String  createTrueArticule(String articule, String brand,  CsvCreator csvCreator){
        if(articule!=null){
            articule = articule.replaceAll(" ","");
            articule = articule.trim();
            if(brand !=null) {
                BrandMatcherContent bmc = csvCreator.getBrandMatchesMap().get(brand.trim());
                if (bmc!=null){
                    List <String> attributesToCut = csvCreator.getArticuleMatchesMap().get(brand.trim());
                    for(String attr : attributesToCut){
                        articule = articule.replace(attr, "");
                    }
                }
            }
        }
        return articule;
    }

    @EntityManagerTransaction
    protected void savePriceItems(List<T> priceList, Margin margin, Margin wholesaleMargin, CsvCreator csvCreator){
        for (T price : priceList)
        {
            fillPriceFields(price, margin, wholesaleMargin, csvCreator);
            if(CommonVariables.writeToFile == true) {
                entityManager.persist(price);
            }
        }
    }

    public abstract String getWholeSaleMarginName();

    protected abstract String getTableName();

    protected abstract boolean conditionToSave(T t);

    protected abstract void fillPriceFields(T t, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator);

    protected abstract String getEnityClassName();


}
