package ua.autoshop.utils.savers.impl.csv;
import org.apache.poi.ss.usermodel.Cell;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceAutotechnix;
import ua.autoshop.model.PriceVlad;
import ua.autoshop.utils.savers.impl.BaseReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 15.10.2015.
 */
public class BaseCsvReader <T extends BaseModel> extends BaseReader {
    public BaseCsvReader(Class clazz) {
        super(clazz);
    }

    protected static final String END_OF_PATH = "/temp.csv";

    protected static final String ENCODING = "windows-1251";

    protected static final String DEFAULT_DELIMITER = ";";

    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceAutotechix(price);
    }

    @Override
    public List<T> readPrice(byte [] data) {
        price = new ArrayList<>();
        savefileToTempDir(data, END_OF_PATH);
        String tempDir = System.getProperty(TEMP_DIR);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(tempDir+END_OF_PATH), ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        String line;
        try {
            while ((line = br.readLine()) != null) {
                count++;
                String [] row = getSplitedRow(line);
                baseModel = buildInstance();
                for(int j=0; j<row.length; j++){
                    writeCellToObject(baseModel, row, j);
                }
                if(count!=1) {
                    price.add(baseModel);
                }
                executeBatch();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return price;
    }

    protected String [] getSplitedRow(String line){
        String [] row = line.split(DEFAULT_DELIMITER);
        return row;
    }

    protected Reader getReader(byte [] data) {
        try {
            return new InputStreamReader(new ByteArrayInputStream(data), ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unable to read csv input", e);
        }
    }
    protected void writeCellToObject(BaseModel baseModel, String[] row, int j){
        PriceAutotechnix priceAutotechnix = (PriceAutotechnix) baseModel;
        if(j==0) {
            priceAutotechnix.setBrand(row[j]);
            return;
        }
        if(j==1){
            row[j] = row[j].replace(" ", "");
            priceAutotechnix.setCode(row[j]);
            return;
        }
        if(j==2){
            priceAutotechnix.setName(row[j]);
            return;
        }
        if(j==3){
            row[j] = row[j].replace(" ", "");
            priceAutotechnix.setPrice(row[j]);
            return;
        }
        if(j==4){
            row[j] = row[j].replace(" ", "");
            priceAutotechnix.setAvailableKiev2(row[j]);
            return;
        }
        if(j==5){
            row[j] = row[j].replace(" ", "");
            priceAutotechnix.setAvailableKiev1(row[j]);
            return;
        }
        if(j==6){
            row[j] = row[j].replace(" ", "");
            priceAutotechnix.setAvailableRovno(row[j]);
            return;
        }
        if(j==7){
            row[j] = row[j].replace(" ", "");
            priceAutotechnix.setAvailableKhmelnitskiy(row[j]);
        }
    }

}
