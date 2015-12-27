package ua.autoshop.dal.impl;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;
import ua.autoshop.utils.filecreator.FileCreator;
import ua.autoshop.utils.filecreator.FileCreatorContext;
import ua.autoshop.utils.marginmaker.MarginMaker;
import ua.autoshop.utils.writer.ExcelWriter;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Пользователь on 13.10.2015.
 */
public class PriceAutoshopDaoImpl implements Dao<PriceAutoshop> {

    @Autowired
    EntityManager entityManager;

    PriceAutoshop lastWrittenPrice;




    @Override
    public List<PriceAutoshop> findAll() {
        return null;
    }

    @Override
    public List <PriceAutoshop> findByCode(String code) {
        try{
            Query query = entityManager.createQuery("SELECT p FROM PriceAutoshop p WHERE p.code = '"+code+"'");
            List <PriceAutoshop> priceList = (List<PriceAutoshop>) query.getResultList();
            return priceList;
        } catch(NoResultException e) {
            return null;
        }
    }

    public List<String> fetchAllToMarketEntities(){
        try{
            Query query = entityManager.createQuery("SELECT p.articule FROM PriceTomarket p");
            List <String> codeSet = (List <String>) query.getResultList();
            return codeSet;
        } catch(NoResultException e) {
            return null;
        }
    }


    @Override
    public PriceAutoshop findByName(String name) {
        return null;
    }

    @Override
    public void delete(PriceAutoshop object) {
        entityManager.getTransaction().begin();
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void save() {

    }

    @Override
    public void saveList(List<PriceAutoshop> priceList) {

    }

    @Override
    public void cleanTable() {
        try{
            entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("DELETE FROM price_autoshop");
            q.executeUpdate();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }

    }

    private PriceAutoshop createCommonNextRowIfNecessary(PriceAutoshop commonPriceEntity, PriceAutoshop price, FileCreator fileCreator){
        if(commonPriceEntity!=null){
            if(price.getCode()!=null) {
                if (!price.getCode().equalsIgnoreCase(commonPriceEntity.getCode())) {
                    fileCreator.createNextRow(commonPriceEntity);
                    commonPriceEntity = price;
                }
            }
        }
        if(commonPriceEntity==null || price.getRetailPrice()<=commonPriceEntity.getRetailPrice()){
            commonPriceEntity = price;
        }
        return commonPriceEntity;
    }

    private PriceAutoshop createAutoXCatalogTOMarketNextRowIfNecessary(PriceAutoshop autoXCatalogTOMarketEntity, PriceAutoshop price, FileCreator fileCreator, Comment comment){
        if(autoXCatalogTOMarketEntity!=null && price.getCode()!=null){
            if(lastWrittenPrice!=null && lastWrittenPrice.getSupplier().equals("ТОМАРКЕТ") && !autoXCatalogTOMarketEntity.getSupplier().equals("ТОМАРКЕТ") && lastWrittenPrice.getCode().equalsIgnoreCase(autoXCatalogTOMarketEntity.getCode())){
                autoXCatalogTOMarketEntity = price;
                return autoXCatalogTOMarketEntity;
            }
            if(!price.getCode().equalsIgnoreCase(autoXCatalogTOMarketEntity.getCode()) || autoXCatalogTOMarketEntity.getSupplier().equals("ТОМАРКЕТ")){
                    fileCreator.createNextRowAutoXCatalogTOMarket(autoXCatalogTOMarketEntity, comment);
                    lastWrittenPrice = autoXCatalogTOMarketEntity;
                    autoXCatalogTOMarketEntity = price;
            }
        }
        if(autoXCatalogTOMarketEntity==null || price.getRetailPrice()<=autoXCatalogTOMarketEntity.getRetailPrice() || price.getSupplier().equals("ТОМАРКЕТ")){
            if(autoXCatalogTOMarketEntity==null){
                autoXCatalogTOMarketEntity = price;
                return autoXCatalogTOMarketEntity;
            }
            if (!autoXCatalogTOMarketEntity.getSupplier().equals("ТОМАРКЕТ")) {
                    autoXCatalogTOMarketEntity = price;
            }

        }
        return autoXCatalogTOMarketEntity;
    }

    private PriceAutoshop createAutoXCatalogAutoshopNetNextRowIfNecessary(PriceAutoshop commonPriceEntity, PriceAutoshop price, FileCreator fileCreator){
        if(commonPriceEntity!=null){
            if(price.getCode()!=null) {
                if (!price.getCode().equalsIgnoreCase(commonPriceEntity.getCode())) {
                    fileCreator.createNextRowAutoXCatalogAutoshopNet(commonPriceEntity);
                    commonPriceEntity = price;
                }
            }
        }
        if(commonPriceEntity==null || price.getRetailPrice()<=commonPriceEntity.getRetailPrice()){
            commonPriceEntity = price;
        }
        return commonPriceEntity;
    }

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {

        sortPriceByArticule();

        Comment comment = getComment();

        FileCreator fileCreator = FileCreatorContext.getStrategy(margin.getPriceName());

        int offset = 0;

                PriceAutoshop commonPriceEntity = null;
                PriceAutoshop autoXCatalogTOMarketEntity = null;
                PriceAutoshop autoXCatalogAutoshopNetEntity = null;

        List<PriceAutoshop> priceList;
        //SortByPrice sbp = new SortByPrice();
        fileCreator.prepareForReading("/output"+fileCreator.getSuffix());
        fileCreator.prepareForReadingAutoXCatalogTOMarket("/outputto" + fileCreator.getSuffix());
        fileCreator.prepareForReadingAutoXCatalogAutoshopNet("/outputauto"+fileCreator.getSuffix());
        fileCreator.createHeaders();
        fileCreator.createHeadersAutoXCatalogTOMarket();
        fileCreator.createHeadersAutoXCatalogAutoshopNet();
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            for (PriceAutoshop price : priceList)
            {
                commonPriceEntity = createCommonNextRowIfNecessary(commonPriceEntity, price, fileCreator);

                autoXCatalogTOMarketEntity = createAutoXCatalogTOMarketNextRowIfNecessary(autoXCatalogTOMarketEntity, price, fileCreator, comment);

                if(!price.getSupplier().equals("ТОМАРКЕТ")) {
                    autoXCatalogAutoshopNetEntity = createAutoXCatalogAutoshopNetNextRowIfNecessary(autoXCatalogAutoshopNetEntity, price, fileCreator);
                }

                /*List <PriceAutoshop> duplicateList = daoPriceAshop.findByCode(price.getCode());
                if(duplicateList!=null) {
                    if(duplicateList.size()>1) {
                        PriceAutoshop[] priceAutoshops = new PriceAutoshop[duplicateList.size()];
                        for (int i = 0; i < duplicateList.size(); i++) {
                            priceAutoshops[i] = duplicateList.get(i);
                        }
                        Arrays.sort(priceAutoshops, sbp);
                        for (int j = 1; j < priceAutoshops.length; j++) {
                            daoPriceAshop.delete(priceAutoshops[j]);
                        }
                    }
                }*/
            }
            entityManager.clear();
            offset += priceList.size();
        }
        fileCreator.createNextRow(commonPriceEntity);
        fileCreator.createNextRowAutoXCatalogTOMarket(autoXCatalogTOMarketEntity, comment);
        if(autoXCatalogAutoshopNetEntity!=null) {
            fileCreator.createNextRowAutoXCatalogAutoshopNet(autoXCatalogAutoshopNetEntity);
        }
        fileCreator.finishReadingAutoXCatalogTOMarket();
        fileCreator.finishReadingAutoXCatalogAutoshopNet();
    }

    public Comment getComment() {
        try{
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.id ="+1, Comment.class);
            Comment c = (Comment) query.getSingleResult();
            entityManager.getTransaction().commit();
            return c;
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    public void saveComment(Comment comment) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(comment);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<PriceAutoshop> getAllModelsIterable(int offset, int max)
    {
        return entityManager.createNativeQuery("SELECT * FROM price_autoshop LIMIT "+offset+", "+max, PriceAutoshop.class).getResultList();
    }

    /*@Override
    public List<PriceAutoshop> getAllModelsIterable(int offset, int max)
    {
        //entityManager.clear();
        return entityManager.createQuery("SELECT p FROM PriceAutoshop p", PriceAutoshop.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }*/

    @Override
    public void save(PriceAutoshop priceAutoshop) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(priceAutoshop);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }

    }

    @Override
    public List<PriceAutoshop> getByPrice(String pattern) {
        try{
            List<PriceAutoshop> list = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT p FROM PriceAutoshop p WHERE CAST(p.retailPrice AS string) LIKE '%"+pattern+"%'", PriceAutoshop.class).setMaxResults(500);
            list = query.getResultList();
            return list;
        } catch(NoResultException e) {
            return null;
        }
    }

    @Override
    public List<PriceAutoshop> getByCode(String pattern) {
        try{
            List<PriceAutoshop> list = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT p FROM PriceAutoshop p WHERE p.code LIKE '%"+pattern+"%'", PriceAutoshop.class).setMaxResults(500);
            list = query.getResultList();
            return list;
        } catch(NoResultException e) {
            return null;
        }
    }

    @Override
    public List<PriceAutoshop> getByName(String pattern) {
        try{
            List<PriceAutoshop> list = new ArrayList<>();
            Query query = entityManager.createQuery("SELECT p FROM PriceAutoshop p WHERE p.name LIKE '%"+pattern+"%'", PriceAutoshop.class).setMaxResults(500);
            list = query.getResultList();
            return list;
        } catch(NoResultException e) {
            return null;
        }
    }

    @Override
    public void sortPriceByArticule() {
        try{
        entityManager.getTransaction().begin();
            Query q = entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS price_autoshop2 LIKE price_autoshop");
            q.executeUpdate();
            Query q1 = entityManager.createNativeQuery("INSERT INTO price_autoshop2 (brand, wholesale_price, retail_price, tomarket_retail, tomarket_wholesale, available, code, name, supplier, shelf, category, additional_information) SELECT brand, wholesale_price, retail_price, tomarket_retail, tomarket_wholesale, available, code, name, supplier, shelf, category, additional_information FROM price_autoshop ORDER BY code");
            q1.executeUpdate();
            Query q2 = entityManager.createNativeQuery("DROP TABLE price_autoshop");
            q2.executeUpdate();
            Query q3 = entityManager.createNativeQuery("RENAME TABLE price_autoshop2 TO price_autoshop");
            q3.executeUpdate();
        entityManager.getTransaction().commit();
    } catch (Exception ex) {
        entityManager.getTransaction().rollback();
        ex.printStackTrace();
    }
    }


}
