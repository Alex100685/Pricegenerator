package ua.autoshop.utils.savers.impl.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceElitOriginal;
import ua.autoshop.model.PriceUnicTrade;
import ua.autoshop.utils.CommonVariables;

/**
 * Created by Пользователь on 13.12.2015.
 */
public class PriceElitOriginalReader extends BaseExcelReader {
    public PriceElitOriginalReader(Class clazz) {
        super(clazz);
    }

    @Override
    protected void addIfNotHeader(Row hfsRow) {
        if (hfsRow.getRowNum() > 1) {
            price.add(baseModel);
        }
    }

    /*@Override
    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value) {
        PriceElitOriginal priceElitOriginal = (PriceElitOriginal) price;

        if(cell.getColumnIndex()==0) {
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceElitOriginal.setCodeElit(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==1){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceElitOriginal.setArticule(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==2){
            priceElitOriginal.setBrand(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==3){
            if(value==null){
                value = "";
            }
            priceElitOriginal.setName(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==4){
            if(value==null){
                value = "";
            }
            priceElitOriginal.setSupplyCondition(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==5){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceElitOriginal.setPrice(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==8){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceElitOriginal.setAvailable(value);
        }
        return priceElitOriginal;
    }*/

    @Override
    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value) {
        PriceElitOriginal priceElitOriginal = (PriceElitOriginal) price;

        if(cell.getColumnIndex()==columnMatches.getCodeMatch()){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceElitOriginal.setArticule(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==columnMatches.getBrandMatch()){
            priceElitOriginal.setBrand(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==columnMatches.getNameMatch()){
            if(value==null){
                value = "";
            }
            priceElitOriginal.setName(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==columnMatches.getIncomePriceMatch()){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceElitOriginal.setPrice(value);
            return priceElitOriginal;
        }
        if(cell.getColumnIndex()==columnMatches.getAvailableMatch()){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceElitOriginal.setAvailable(value);
        }
        if(cell.getColumnIndex()==columnMatches.getSupplyCondition()){
            if(value==null){
                value = "";
            }
            priceElitOriginal.setSupplyCondition(value);
            return priceElitOriginal;
        }
        return priceElitOriginal;
    }



    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceElitOriginal(price);
    }



}
