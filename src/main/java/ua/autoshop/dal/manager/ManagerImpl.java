package ua.autoshop.dal.manager;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;
import ua.autoshop.utils.combiner.MarginService;
import ua.autoshop.utils.marginmaker.MarginMaker;
import ua.autoshop.utils.savers.PriceReaderContext;
import ua.autoshop.utils.sixlsx.excel.xlsx.Sheet;
import ua.autoshop.utils.sixlsx.excel.xlsx.SimpleXLSXWorkbook;
import ua.autoshop.utils.sorter.SortByPrice;
import ua.autoshop.utils.writer.ExcelWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Пользователь on 09.10.2015.
 */
public class ManagerImpl implements Manager {

    @Autowired
    Dao<Margin> daoMargin;

    @Autowired
    Dao<User> daoUser;

    @Autowired
    Dao <PriceGerasimenko> daoPriceG;

    @Autowired
    Dao <PriceVlad> daoPriceV;

    @Autowired
    Dao <PriceAutotechnix> daoPriceA;

    @Autowired
    Dao <PriceIntercarsi> daoPriceI;

    @Autowired
    Dao <Updates> daoUpdates;

    @Autowired
    Dao <PriceAutoshop> daoPriceAshop;

    @Autowired
    Dao <PriceGenstar> daoPriceGenstar;

    @Autowired
    Dao <PriceAmperis> daoPriceAmperis;


    public Updates [] getAllUpdates() {
        Updates[] updateArray = new Updates[6];
        Updates updAutotechniks = daoUpdates.findByName("Автотехникс");
        Updates updIntercars = daoUpdates.findByName("Интеркарс");
        Updates updVlad = daoUpdates.findByName("Влад");
        Updates updElit = daoUpdates.findByName("Элит");
        Updates updGensar = daoUpdates.findByName("Генстар");
        Updates updAmperis = daoUpdates.findByName("Амперис");
        updateArray[0] = updAutotechniks;
        updateArray[1] = updIntercars;
        updateArray[2] = updVlad;
        updateArray[3] = updElit;
        updateArray[4] = updGensar;
        updateArray[5] = updAmperis;

        return updateArray;
    }

    public Margin [] getAllMargin(){
        Margin [] marginArray = new Margin [6];
        Margin mAutotechniks = daoMargin.findByName("Автотехникс");
        if(mAutotechniks == null){
            mAutotechniks = createNewMarginWithName("Автотехникс");
        }
        Margin mIntercars = daoMargin.findByName("Интеркарс");
        if(mIntercars == null){
            mIntercars = createNewMarginWithName("Интеркарс");
        }
        Margin mVlad = daoMargin.findByName("Влад");
        if(mVlad == null){
            mVlad = createNewMarginWithName("Влад");
        }
        Margin mElit = daoMargin.findByName("Элит");
        if(mElit == null){
            mElit = createNewMarginWithName("Элит");
        }
        Margin mGenstar = daoMargin.findByName("Генстар");
        if(mGenstar == null){
            mGenstar = createNewMarginWithName("Генстар");
        }
        Margin mAmperis = daoMargin.findByName("Амперис");
        if(mAmperis == null){
            mAmperis = createNewMarginWithName("Амперис");
        }
        marginArray [0] = mAutotechniks;
        marginArray [1] = mIntercars;
        marginArray [2] = mVlad;
        marginArray [3] = mElit;
        marginArray [4] = mGenstar;
        marginArray [5] = mAmperis;
        return marginArray;
    }

    @Override
    public Margin getMarginByName(String priceName) {
        return daoMargin.findByName(priceName);
    }

    @Override
    public void saveMargin(Margin margin) {
        daoMargin.save(margin);
    }

    @Override
    public void createCommonPriceInDB() {
        daoPriceA.iterateAllAndSaveToMainTable(getMarginByName("Автотехникс"));
        daoPriceG.iterateAllAndSaveToMainTable(getMarginByName("Элит"));
        daoPriceI.iterateAllAndSaveToMainTable(getMarginByName("Интеркарс"));
        daoPriceV.iterateAllAndSaveToMainTable(getMarginByName("Влад"));
        daoPriceGenstar.iterateAllAndSaveToMainTable(getMarginByName("Генстар"));
        daoPriceAmperis.iterateAllAndSaveToMainTable(getMarginByName("Амперис"));
    }

    @Override
    public void iterateAllAndRemoveDuplicates() {
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
        while ((priceList = daoPriceAshop.getAllModelsIterable(offset, PORTION)).size() > 0)
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



    public Margin createNewMarginWithName (String name){
        Margin margin = new Margin();
        margin.setPriceName(name);
        MarginService.getStrategy(margin).setDefaultMargins();
        daoMargin.save(margin);
        return margin;
    }

    public List <? extends BaseModel> readPrice(MultipartFile file, String index){
        byte [] data = null;
        try {
            data = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<? extends BaseModel> priceList = PriceReaderContext.getStrategy(index).readPrice(data);
        return priceList;
    }

    @Override
    public void saveUpdate(String updateName) {
        Updates u = daoUpdates.findByName(updateName);
        if (u == null) {
            u = new Updates();
            u.setPriceName(updateName);
            daoUpdates.save(u);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String strDate = sdf.format(date);
        u.setDateOfUpdate(strDate);
        daoUpdates.save(u);
    }

    public void saveUpdateFail(String updateName){
        Updates u = daoUpdates.findByName(updateName);
        if (u == null) {
            u = new Updates();
            u.setPriceName(updateName);
            daoUpdates.save(u);
        }
        u.setDateOfUpdate("Недопустимая структура файла");
        daoUpdates.save(u);
    }

    @Override
    public void saveAllPriceIntercarsi(List<PriceIntercarsi> priceList) {
        daoPriceI.saveList(priceList);
    }

    @Override
    public void saveAllPriceVlad(List<PriceVlad> priceList) {
        daoPriceV.saveList(priceList);
    }

    @Override
    public void saveAllPriceElit(List<PriceGerasimenko> priceList) {
        daoPriceG.saveList(priceList);
    }

    @Override
    public void savePriceAutotechix(PriceAutotechnix priceAutotechnix) {
        daoPriceA.save();
    }

    @Override
    public void saveAllPriceAutotechix(List<PriceAutotechnix> priceList) {
        daoPriceA.saveList(priceList);
    }

    @Override
    public void saveAllPriceGenstar(List <PriceGenstar> price) {
        daoPriceGenstar.saveList(price);
    }

    @Override
    public void saveAllPriceAmperis(List<PriceAmperis> priceList) {
        daoPriceAmperis.saveList(priceList);
    }


}
