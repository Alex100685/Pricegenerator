package ua.autoshop.utils.savers.impl.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceAmperis;

/**
 * Created by Пользователь on 19.10.2015.
 */
public class PriceAmperisReader extends BaseExcelReader {
    public PriceAmperisReader(Class clazz) {
        super(clazz);
    }

    @Override
    protected void addIfNotHeader(Row hfsRow) {
        if (hfsRow.getRowNum() != 0) {
            price.add(baseModel);
        }
    }

   /* @Override
    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value) {
        PriceAmperis priceAmperis = (PriceAmperis) price;

        if(cell.getColumnIndex()==0) {

            priceAmperis.setOriginalCode(value);
            return priceAmperis;
        }
        if(cell.getColumnIndex()==1){

            priceAmperis.setFullCode(value);
            return priceAmperis;
        }
        if(cell.getColumnIndex()==2){
            priceAmperis.setName(value);
            return priceAmperis;
        }
        if(cell.getColumnIndex()==3){
            priceAmperis.setProductGroup(value);
            return priceAmperis;
        }
        if(cell.getColumnIndex()==4){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceAmperis.setPrice(value);
            return priceAmperis;
        }
        if(cell.getColumnIndex()==5){
            if(value.contains(",")) {
                value = value.substring(0, value.indexOf(','));
            }
            priceAmperis.setAvailable(value);
        }
        return priceAmperis;
    }*/


    @Override
    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value) {
        PriceAmperis priceAmperis = (PriceAmperis) price;

        if(cell.getColumnIndex()==columnMatches.getCodeMatch()){

            priceAmperis.setFullCode(value);
            return priceAmperis;
        }
        if(cell.getColumnIndex()==columnMatches.getNameMatch()){
            priceAmperis.setName(value);
            return priceAmperis;
        }
        if(cell.getColumnIndex()==columnMatches.getCategoryMatch()){
            priceAmperis.setProductGroup(value);
            return priceAmperis;
        }
        if(cell.getColumnIndex()==columnMatches.getIncomePriceMatch()){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceAmperis.setPrice(value);
            return priceAmperis;
        }
        if(cell.getColumnIndex()==columnMatches.getAvailableMatch()){
            if(value.contains(",")) {
                value = value.substring(0, value.indexOf(','));
            }
            priceAmperis.setAvailable(value);
        }
        return priceAmperis;
    }

    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceAmperis(price);
    }


}
