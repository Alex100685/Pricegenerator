package ua.autoshop.utils.savers.impl.excel;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceIntercarsi;
import ua.autoshop.model.PriceVlad;
import ua.autoshop.utils.savers.impl.excel.BaseExcelReader;

import java.util.List;

/**
 * Created by Пользователь on 09.10.2015.
 */
public class PriseIntercarsReader extends BaseExcelReader {


    public PriseIntercarsReader(Class clazz) {
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
        PriceIntercarsi priceIntercarsi = (PriceIntercarsi) price;

        if(cell.getColumnIndex()==0) {
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setCode(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==1){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setArticule(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==2){
            priceIntercarsi.setBrand(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==3){
            priceIntercarsi.setName(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==4){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setRetailPrice(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==5){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setWholesalePrice(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==6){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setAvailableUr1(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==7){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setAvailableUj0(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==8){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setAvailableTwoDays(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==9){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setArticuleOne(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==10){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setAvailableUj7(value);
        }
        return priceIntercarsi;
    }*/


    @Override
    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value) {
        PriceIntercarsi priceIntercarsi = (PriceIntercarsi) price;

        if(cell.getColumnIndex()==columnMatches.getCodeMatch()){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setArticule(value);
            priceIntercarsi.setCode(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==columnMatches.getBrandMatch()){
            priceIntercarsi.setBrand(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==columnMatches.getNameMatch()){
            priceIntercarsi.setName(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==columnMatches.getRetailPriceMatch()){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setRetailPrice(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==columnMatches.getWholesalePriceMatch()){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setWholesalePrice(value);
            return priceIntercarsi;
        }
        if(cell.getColumnIndex()==columnMatches.getAvailableMatch()){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceIntercarsi.setAvailableUr1(value);
            return priceIntercarsi;
        }
        return priceIntercarsi;
    }

    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceIntercarsi(price);
    }
}
