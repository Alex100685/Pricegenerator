package ua.autoshop.dal.impl;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.Margin;
import ua.autoshop.model.PriceAutoshop;
import ua.autoshop.model.PriceAutotechnix;
import ua.autoshop.utils.marginmaker.MarginMaker;
import ua.autoshop.utils.writer.ExcelWriter;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Пользователь on 13.10.2015.
 */
public class PriceAutoshopDaoImpl implements Dao<PriceAutoshop> {

    @Autowired
    EntityManager entityManager;


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

    @Override
    public void iterateAllAndSaveToMainTable(Margin margin) {
        int offset = 0;

        List<PriceAutoshop> priceList;
        //SortByPrice sbp = new SortByPrice();
        int rowNumber = 0;
        SXSSFWorkbook workbook = ExcelWriter.openForWritting();
        FileOutputStream tempOutput = ExcelWriter.openOutputStream();


        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Прайс");
        SXSSFRow row = (SXSSFRow) sheet.createRow(rowNumber);
        row.createCell(0).setCellValue("Бренд");
        row.createCell(1).setCellValue("Розничная цена");
        row.createCell(2).setCellValue("Наличие всего");
        row.createCell(3).setCellValue("Код");
        row.createCell(4).setCellValue("Описание");
        row.createCell(5).setCellValue("Поставщик");
        while ((priceList = getAllModelsIterable(offset, PORTION)).size() > 0)
        {
            for (PriceAutoshop price : priceList)
            {
                rowNumber++;
                row = (SXSSFRow) sheet.createRow(rowNumber);
                row.createCell(0).setCellValue(price.getBrand());
                row.createCell(1).setCellValue(Double.toString(price.getRetailPrice()));
                row.createCell(2).setCellValue(price.getAvailable());
                row.createCell(3).setCellValue(price.getCode());
                row.createCell(4).setCellValue(price.getName());
                row.createCell(5).setCellValue(price.getSupplier());
                price = null;

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
        try {
            workbook.write(tempOutput);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            tempOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PriceAutoshop> getAllModelsIterable(int offset, int max)
    {
        //entityManager.clear();
        return entityManager.createQuery("SELECT p FROM PriceAutoshop p", PriceAutoshop.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

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


}
