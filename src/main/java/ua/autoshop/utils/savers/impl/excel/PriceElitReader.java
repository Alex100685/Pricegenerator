package ua.autoshop.utils.savers.impl.excel;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceAutotechnix;
import ua.autoshop.model.PriceGerasimenko;
import ua.autoshop.utils.savers.impl.excel.BaseExcelReader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Пользователь on 09.10.2015.
 */
public class PriceElitReader extends BaseExcelReader {

    public PriceElitReader(Class clazz) {
        super(clazz);
    }


    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value){
        PriceGerasimenko priceGerasimenko = (PriceGerasimenko) price;

        if(cell.getColumnIndex()==0) {
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceGerasimenko.setActiveCode(value);
            return priceGerasimenko;
        }
        if(cell.getColumnIndex()==1){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceGerasimenko.setCatalogNumber(value);
            return priceGerasimenko;
        }
        if(cell.getColumnIndex()==2){
            priceGerasimenko.setBrand(value);
            return priceGerasimenko;
        }
        if(cell.getColumnIndex()==3){
            priceGerasimenko.setProductDescription(value);
            return priceGerasimenko;
        }
        if(cell.getColumnIndex()==4){
            priceGerasimenko.setEatDescription(value);
            return priceGerasimenko;
        }
        if(cell.getColumnIndex()==5){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceGerasimenko.setClientPrice(value);
            return priceGerasimenko;
        }
        if(cell.getColumnIndex()==6){
            if(value!=null) {
                value = value.replace(" ", "");
                value = value.replace("+", "");
            }
            priceGerasimenko.setAvailableOnCentralYourBranch(value);
            return priceGerasimenko;
        }
        if(cell.getColumnIndex()==7){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceGerasimenko.setAvailableOnCentral(value);
            return priceGerasimenko;
        }
        if(cell.getColumnIndex()==7){
            if(value!=null) {
                value = value.replace(" ", "");
            }
            priceGerasimenko.setAvailableOnAnother(value);
        }
        return priceGerasimenko;
    }

    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceElit(price);
    }
}
