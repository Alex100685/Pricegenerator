package ua.autoshop.dal.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import ua.autoshop.dal.Dao;
import ua.autoshop.model.*;
import ua.autoshop.utils.combiner.MarginService;
import ua.autoshop.utils.savers.PriceReaderContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Пользователь on 09.10.2015.
 */
public class ManagerImpl implements Manager {

    @Autowired
    Dao<Comment>daoComment;

    @Autowired
    Dao<BrandMatches> daoBrandMatches;

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
    Dao <PriceTomarket> daoPriceT;

    @Autowired
    Dao <Updates> daoUpdates;

    @Autowired
    Dao <PriceAutoshop> daoPriceAshop;

    @Autowired
    Dao <PriceGenstar> daoPriceGenstar;

    @Autowired
    Dao <PriceAmperis> daoPriceAmperis;

    @Autowired
    Dao<PriceElitOriginal> daoPriceElitOriginal;

    @Autowired
    Dao<PriceUnicTrade> daoPriceUnicTrade;


    public Updates [] getAllUpdates() {
        Updates[] updateArray = new Updates[9];
        Updates updAutotechniks = daoUpdates.findByName("Автотехникс");
        Updates updIntercars = daoUpdates.findByName("Интеркарс");
        Updates updVlad = daoUpdates.findByName("Влад");
        Updates updElit = daoUpdates.findByName("Элит");
        Updates updGensar = daoUpdates.findByName("Генстар");
        Updates updAmperis = daoUpdates.findByName("Амперис");
        Updates updTomarket = daoUpdates.findByName("ТОМАРКЕТ");
        Updates updUnicTrade = daoUpdates.findByName("Юник ТРЕЙД");
        Updates updElitOriginal = daoUpdates.findByName("Элит ОРИГИНАЛ");
        updateArray[0] = updAutotechniks;
        updateArray[1] = updIntercars;
        updateArray[2] = updVlad;
        updateArray[3] = updElit;
        updateArray[4] = updGensar;
        updateArray[5] = updAmperis;
        updateArray[6] = updTomarket;
        updateArray[7] = updUnicTrade;
        updateArray[8] = updElitOriginal;

        return updateArray;
    }

    public Margin [] getAllMargin(){
        Margin [] marginArray = new Margin [18];
        Margin mAutotechniks = daoMargin.findByName("Автотехникс");
        if(mAutotechniks == null){
            mAutotechniks = createNewMarginWithName("Автотехникс");
        }
        Margin mAutotechniksW = daoMargin.findByName("Автотехникс ОПТ");
        if(mAutotechniksW == null){
            mAutotechniksW = createNewMarginWithName("Автотехникс ОПТ");
        }
        Margin mIntercars = daoMargin.findByName("Интеркарс");
        if(mIntercars == null){
            mIntercars = createNewMarginWithName("Интеркарс");
        }
        Margin mIntercarsW = daoMargin.findByName("Интеркарс ОПТ");
        if(mIntercarsW == null){
            mIntercarsW = createNewMarginWithName("Интеркарс ОПТ");
        }
        Margin mVlad = daoMargin.findByName("Влад");
        if(mVlad == null){
            mVlad = createNewMarginWithName("Влад");
        }
        Margin mVladW = daoMargin.findByName("Влад ОПТ");
        if(mVladW == null){
            mVladW = createNewMarginWithName("Влад ОПТ");
        }
        Margin mElit = daoMargin.findByName("Элит");
        if(mElit == null){
            mElit = createNewMarginWithName("Элит");
        }
        Margin mElitW = daoMargin.findByName("Элит ОПТ");
        if(mElitW == null){
            mElitW = createNewMarginWithName("Элит ОПТ");
        }
        Margin mGenstar = daoMargin.findByName("Генстар");
        if(mGenstar == null){
            mGenstar = createNewMarginWithName("Генстар");
        }
        Margin mGenstarW = daoMargin.findByName("Генстар ОПТ");
        if(mGenstarW == null){
            mGenstarW = createNewMarginWithName("Генстар ОПТ");
        }
        Margin mAmperis = daoMargin.findByName("Амперис");
        if(mAmperis == null){
            mAmperis = createNewMarginWithName("Амперис");
        }
        Margin mAmperisW = daoMargin.findByName("Амперис ОПТ");
        if(mAmperisW == null){
            mAmperisW = createNewMarginWithName("Амперис ОПТ");
        }
        Margin mTomarketRetail = daoMargin.findByName("ТОМАРКЕТ РОЗНИЦА");
        if(mTomarketRetail == null){
            mTomarketRetail = createNewMarginWithName("ТОМАРКЕТ РОЗНИЦА");
        }
        Margin mTomarketWholesale = daoMargin.findByName("ТОМАРКЕТ ОПТ");
        if(mTomarketWholesale == null){
            mTomarketWholesale = createNewMarginWithName("ТОМАРКЕТ ОПТ");
        }
        Margin mUnicTradeRetail = daoMargin.findByName("Юник ТРЕЙД РОЗНИЦА");
        if(mUnicTradeRetail == null){
            mUnicTradeRetail = createNewMarginWithName("Юник ТРЕЙД РОЗНИЦА");
        }
        Margin mUnicTradeW = daoMargin.findByName("Юник ТРЕЙД ОПТ");
        if(mUnicTradeW == null){
            mUnicTradeW = createNewMarginWithName("Юник ТРЕЙД ОПТ");
        }

        Margin mElitOriginalRetail = daoMargin.findByName("Элит ОРИГИНАЛ РОЗНИЦА");
        if(mElitOriginalRetail == null){
            mElitOriginalRetail = createNewMarginWithName("Элит ОРИГИНАЛ РОЗНИЦА");
        }
        Margin mElitOriginalW = daoMargin.findByName("Элит ОРИГИНАЛ ОПТ");
        if(mElitOriginalW == null){
            mElitOriginalW = createNewMarginWithName("Элит ОРИГИНАЛ ОПТ");
        }

        marginArray [0] = mAutotechniks;
        marginArray [8] = mAutotechniksW;
        marginArray [1] = mIntercars;
        marginArray [9] = mIntercarsW;
        marginArray [2] = mVlad;
        marginArray [10] = mVladW;
        marginArray [3] = mElit;
        marginArray [11] = mElitW;
        marginArray [4] = mGenstar;
        marginArray [12] = mGenstarW;
        marginArray [5] = mAmperis;
        marginArray [13] = mAmperisW;
        marginArray [6] = mTomarketRetail;
        marginArray [7] = mTomarketWholesale;
        marginArray [14] = mUnicTradeRetail;
        marginArray [15] = mUnicTradeW;
        marginArray [16] = mElitOriginalRetail;
        marginArray [17] = mElitOriginalW;

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
        daoPriceT.iterateAllAndSaveToMainTable(getMarginByName("ТОМАРКЕТ РОЗНИЦА"));
        daoPriceUnicTrade.iterateAllAndSaveToMainTable(getMarginByName("Юник ТРЕЙД РОЗНИЦА"));
        daoPriceElitOriginal.iterateAllAndSaveToMainTable(getMarginByName("Элит ОРИГИНАЛ РОЗНИЦА"));
    }

    @Override
    public void iterateAllAndRemoveDuplicates() {
        /*int offset = 0;
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

                *//*List <PriceAutoshop> duplicateList = daoPriceAshop.findByCode(price.getCode());
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
                }*//*
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
        }*/
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

    public void saveUpdateEmpty(String updateName){
        Updates u = daoUpdates.findByName(updateName);
        if (u == null) {
            u = new Updates();
            u.setPriceName(updateName);
            daoUpdates.save(u);
        }
        u.setDateOfUpdate("ПУСТО");
        daoUpdates.save(u);
    }

    @Override
    public void saveAllPriceIntercarsi(List<PriceIntercarsi> priceList) {
        daoPriceI.saveList(priceList);
    }

    @Override
    public void saveAllPriceTomarket(List <PriceTomarket> priceList) {
        daoPriceT.saveList(priceList);
    }

    @Override
    public void sortCommonTableByArticule() {
        daoPriceAshop.sortPriceByArticule();
    }

    @Override
    public List<BrandMatches> getBrandMatches() {
       return daoBrandMatches.findAll();
    }

    @Override
    public void saveBrandMatch(BrandMatches bm) {
        daoBrandMatches.save(bm);
    }

    @Override
    public BrandMatches findBrandMatchByName(String name) {
        return daoBrandMatches.findByName(name);
    }

    @Override
    public void deleteBrandMatch(BrandMatches bm) {
        daoBrandMatches.delete(bm);
    }

    @Override
    public void saveComment(Comment comment) {
        daoComment.save(comment);
    }

    @Override
    public Comment getComment() {
        Comment comment = daoComment.findByName("1");
        return comment;
    }

    @Override
    public void saveAllPriceUnicTrade(List<PriceUnicTrade> priceList) {
        daoPriceUnicTrade.saveList(priceList);
    }

    @Override
    public void saveAllPriceElitOriginal(List<PriceElitOriginal> priceList) {
        daoPriceElitOriginal.saveList(priceList);
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

    @Override
    public List<PriceAutoshop> findAutoshopPriceByPrice(String pattern) {
        List<PriceAutoshop> autoshopList = daoPriceAshop.getByPrice(pattern);
        return autoshopList;
    }

    @Override
    public List<PriceAutoshop> findAutoshopPriceByCode(String pattern) {
        List<PriceAutoshop> autoshopList = daoPriceAshop.getByCode(pattern);
        return autoshopList;
    }

    @Override
    public List<PriceAutoshop> findAutoshopPriceByName(String pattern) {
        List<PriceAutoshop> autoshopList = daoPriceAshop.getByName(pattern);
        return autoshopList;
    }


}
