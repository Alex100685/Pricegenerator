package ua.autoshop.utils.savers.impl.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceGenstar;
import ua.autoshop.model.PriceIntercarsi;

/**
 * Created by Пользователь on 19.10.2015.
 */
public class PriceGenstarReader extends BaseExcelReader {

    public PriceGenstarReader(Class clazz) {
        super(clazz);
    }


    @Override
    protected void addIfNotHeader(Row hfsRow) {
        if (hfsRow.getRowNum() != 0) {
            price.add(baseModel);
        }
    }

    @Override
    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value) {
        PriceGenstar priceGenstar = (PriceGenstar) price;

        if(cell.getColumnIndex()==0) {

            priceGenstar.setName(value);
            return priceGenstar;
        }
        if(cell.getColumnIndex()==1){

            priceGenstar.setBrand(value);
            return priceGenstar;
        }
        if(cell.getColumnIndex()==2){
            priceGenstar.setProductCode(value);
            return priceGenstar;
        }
        if(cell.getColumnIndex()==3){
            priceGenstar.setArticule(value);
            return priceGenstar;
        }
        if(cell.getColumnIndex()==5){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceGenstar.setPrice(value);
            return priceGenstar;
        }
        if(cell.getColumnIndex()==6){
            priceGenstar.setAvailable(value);
        }
        return priceGenstar;
    }

    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceGenstar(price);
    }


}
