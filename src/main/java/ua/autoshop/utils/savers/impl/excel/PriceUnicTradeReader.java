package ua.autoshop.utils.savers.impl.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceUnicTrade;

/**
 * Created by Пользователь on 13.12.2015.
 */
public class PriceUnicTradeReader extends BaseExcelReader {
    public PriceUnicTradeReader(Class clazz) {
        super(clazz);
    }

    @Override
    protected void addIfNotHeader(Row hfsRow) {
        if (hfsRow.getRowNum() > 9) {
            price.add(baseModel);
        }
    }

    @Override
    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value) {
        PriceUnicTrade priceUnicTrade = (PriceUnicTrade) price;

        if(cell.getColumnIndex()==0) {
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceUnicTrade.setArticule(value);
            return priceUnicTrade;
        }
        if(cell.getColumnIndex()==1){
            if(value==null) {
                value = "";
            }
            priceUnicTrade.setName(value);
            return priceUnicTrade;
        }
        if(cell.getColumnIndex()==2){
            priceUnicTrade.setBrand(value);
            return priceUnicTrade;
        }
        if(cell.getColumnIndex()==6){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceUnicTrade.setAvailable(value);
            return priceUnicTrade;
        }
        if(cell.getColumnIndex()==8){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceUnicTrade.setPrice(value);
            return priceUnicTrade;
        }
        if(cell.getColumnIndex()==9){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceUnicTrade.setCurrency(value);
        }
        return priceUnicTrade;
    }

    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceUnicTrade(price);
    }

}
