package ua.autoshop.utils.savers.impl.csv;

import ua.autoshop.model.AsgModel;
import ua.autoshop.model.BaseModel;

/**
 * Created by Пользователь on 26.03.2016.
 */
public class PriceAsgReader extends BaseCsvReader {
    public PriceAsgReader(Class clazz) {
        super(clazz);
    }

    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceAsg(price);
    }

    @Override
    protected void writeCellToObject(BaseModel baseModel, String[] row, int j){
        AsgModel priceAsg = (AsgModel) baseModel;
        if(j==columnMatches.getBrandMatch()) {
            priceAsg.setBrand(row[j]);
            return;
        }
        if(j==columnMatches.getCodeMatch()){
            row[j] = row[j].trim();
            priceAsg.setCode(row[j]);
            return;
        }
        if(j==columnMatches.getNameMatch()){
            priceAsg.setName(row[j]);
            return;
        }

        if(j==columnMatches.getIncomePriceMatch()){
            row[j] = row[j].replace(" ", "");
            priceAsg.setIncomePrice(row[j]);
            return;
        }
        if(j==columnMatches.getAvailableMatch()){
            row[j] = row[j].replace(" ", "");
            priceAsg.setAvailable(row[j]);
            return;
        }
    }

}
