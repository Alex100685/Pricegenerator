package ua.autoshop.utils.savers.impl.csv;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceAutotechnix;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import ua.autoshop.utils.savers.impl.csv.BaseCsvReader;

/**
 * Created by Пользователь on 09.10.2015.
 */

public class PriceAutotechnixReader extends BaseCsvReader {

    public PriceAutotechnixReader(Class clazz) {
        super(clazz);
    }


    private static final String DELIMITER = "\t";

    /*@Override
    public List <BaseModel>  readPrice(byte [] data) {
        price = new ArrayList<>();
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.getFormat().setDelimiter('^');
        parserSettings.setMaxCharsPerColumn(5120);
        CsvParser parser = new CsvParser(parserSettings);
        List<String[]> allRows = parser.parseAll(getReader(data));
        for(int i=1; i<allRows.size(); i++){
           String [] row =  allRows.get(i);
            row = row[0].split("\t");
            PriceAutotechnix priceAutotechnix = new PriceAutotechnix();
            for(int j=0; j<row.length; j++){
               writeCellToObject(priceAutotechnix, row, j);
            }
            price.add(priceAutotechnix);
            executeBatch();
        }
        return price;
    }*/

    @Override
    protected String [] getSplitedRow(String line){
        String [] row = line.split(DELIMITER);
        return row;
    }

    @Override
    protected void writeCellToObject(BaseModel baseModel, String[] row, int j){
        PriceAutotechnix priceAutotechnix = (PriceAutotechnix) baseModel;
        if(j==0) {
            priceAutotechnix.setBrand(row[j]);
        }
        if(j==1){
            if(row[j].contains(" ")) {
                row[j] = row[j].substring(row[j].indexOf(" "));
            }
            priceAutotechnix.setCode(row[j]);
        }
        if(j==2){
            priceAutotechnix.setName(row[j]);
        }
        if(j==3){
            row[j] = row[j].replace(" ", "");
            priceAutotechnix.setPrice(row[j]);
        }
        if(j==4){
            row[j] = row[j].replace(" ", "");
            priceAutotechnix.setAvailableKiev2(row[j]);
        }
        if(j==5){
            row[j] = row[j].replace(">", "");
            row[j] = row[j].replace("<", "");
            priceAutotechnix.setAvailableKiev1(row[j]);
        }
        if(j==6){
            row[j] = row[j].replace(">", "");
            row[j] = row[j].replace("<", "");
            priceAutotechnix.setAvailableRovno(row[j]);
        }
        if(j==7){
            row[j] = row[j].replace(" ", "");
            priceAutotechnix.setAvailableKhmelnitskiy(row[j]);
        }
    }


}
