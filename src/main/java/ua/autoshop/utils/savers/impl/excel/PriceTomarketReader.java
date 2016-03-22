package ua.autoshop.utils.savers.impl.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceGenstar;
import ua.autoshop.model.PriceTomarket;

/**
 * Created by Пользователь on 21.11.2015.
 */
public class PriceTomarketReader extends BaseExcelReader {


    public PriceTomarketReader(Class clazz) {
        super(clazz);
    }

    @Override
    protected void addIfNotHeader(Row hfsRow) {
        if (hfsRow.getRowNum() != 0) {
            price.add(baseModel);
        }
    }

    /*@Override
    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value) {
        PriceTomarket priceTomarket = (PriceTomarket) price;

        if(cell.getColumnIndex()==0) {
            priceTomarket.setIdCategoty(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==1){
            priceTomarket.setTradePositionNumber(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==2){
            priceTomarket.setCategoryName(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==3){
            priceTomarket.setProductName(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==4){
            priceTomarket.setBrand(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==5){
            priceTomarket.setArticule(value.trim());
        }
        if(cell.getColumnIndex()==6){
            priceTomarket.setIncomePrice(value);
        }
        if(cell.getColumnIndex()==7){
            priceTomarket.setWholesalePrice(value);
        }
        if(cell.getColumnIndex()==8){
            priceTomarket.setRetailPrice(value);
        }
        if(cell.getColumnIndex()==9){
            priceTomarket.setInnerCrossCodes(value);
        }
        if(cell.getColumnIndex()==10){
            priceTomarket.setInnerCrossCodes2(value);
        }
        if(cell.getColumnIndex()==11){
            priceTomarket.setAvailableOnStock(value);
        }
        if(cell.getColumnIndex()==12){
            priceTomarket.setShelfOfProduct(value);
        }
        return priceTomarket;
    }*/


    @Override
    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value) {
        PriceTomarket priceTomarket = (PriceTomarket) price;

        if(cell.getColumnIndex()==columnMatches.getCategoryMatch()){
            priceTomarket.setCategoryName(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==columnMatches.getNameMatch()){
            priceTomarket.setProductName(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==columnMatches.getBrandMatch()){
            priceTomarket.setBrand(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==columnMatches.getCodeMatch()){
            priceTomarket.setArticule(value.trim());
            return priceTomarket;
        }
        if(cell.getColumnIndex()==columnMatches.getIncomePriceMatch()){
            priceTomarket.setIncomePrice(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==columnMatches.getWholesalePriceMatch()){
            priceTomarket.setWholesalePrice(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==columnMatches.getRetailPriceMatch()){
            priceTomarket.setRetailPrice(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==columnMatches.getAvailableMatch()){
            priceTomarket.setAvailableOnStock(value);
            return priceTomarket;
        }
        if(cell.getColumnIndex()==columnMatches.getShelfMatch()){
            priceTomarket.setShelfOfProduct(value);
        }
        if(cell.getColumnIndex()==columnMatches.getPictureMatch()){
            priceTomarket.setPicture(value);
        }
        return priceTomarket;
    }




    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceTomarket(price);
    }


}
