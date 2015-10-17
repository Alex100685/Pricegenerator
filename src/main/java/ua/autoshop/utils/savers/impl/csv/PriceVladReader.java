package ua.autoshop.utils.savers.impl.csv;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceAutotechnix;
import ua.autoshop.model.PriceGerasimenko;
import ua.autoshop.model.PriceVlad;
import ua.autoshop.utils.savers.impl.csv.BaseCsvReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 09.10.2015.
 */
public class PriceVladReader extends BaseCsvReader {


    public PriceVladReader(Class clazz) {
        super(clazz);
    }

    protected void writeCellToObject(BaseModel baseModel, String [] row, int j) {
        PriceVlad priceVlad = (PriceVlad) baseModel;
        row[j] = row[j].replace("\"", "");
        row[j] = row[j].replace(" ", "");
        if(j==0) {
            row[j] = row[j].replace(" ", "");
            priceVlad.setInsideCode(row[j]);
            return;
        }
        if(j==1){
            priceVlad.setBrand(row[j]);
            return;
        }
        if(j==2){
            priceVlad.setFullName(row[j]);
            return;
        }
        if(j==3){
            row[j] = row[j].replace(" ", "");
            priceVlad.setArticule(row[j]);
            return;
        }
        if(j==4){
            row[j] = row[j].replace(" ", "");
            priceVlad.setPrice(row[j]);
            return;
        }
        if(j==5){
            row[j] = row[j].replace(" ", "");
            priceVlad.setLeftByDefault(row[j]);
            return;
        }
        if(j==6){
            row[j] = row[j].replace(" ", "");
            priceVlad.setLeftTotal(row[j]);
            return;
        }
        if(j==7){
            priceVlad.setComment(row[j]);
        }
    }

    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceVlad(price);
    }
}
