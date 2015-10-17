package ua.autoshop.utils.savers.impl;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.autoshop.config.ApplicationContextHolder;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.BaseModel;
import ua.autoshop.utils.sixlsx.excel.xlsx.Sheet;
import ua.autoshop.utils.sixlsx.excel.xlsx.SimpleXLSXWorkbook;

import java.io.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
//import com.incesoft.tools.excel.xlsx.*;

/**
 * Created by Пользователь on 11.10.2015.
 */

public abstract class BaseReader <T extends BaseModel> {

    @Autowired
    @Qualifier(MANAGER_BEAN_NAME)
    protected Manager readerManager;

    protected List <T> price;

    protected Class <T> clazz;

    protected T baseModel;

    protected  static final int BATCH = 100;

    protected static final String TEMP_DIR =  "java.io.tmpdir";

    protected static final String DATE_PATTERN = "dd.MM.yyyy";

    protected static final String MANAGER_BEAN_NAME = "readerManager";

    public BaseReader(Class <T> clazz){
        this.clazz = clazz;
        readerManager = (Manager) ApplicationContextHolder.getContext().getBean(MANAGER_BEAN_NAME);
    }

    protected T buildInstance()
    {
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e){
            e.printStackTrace();
        }
        return instance;
    }

    protected int counter = 0;

    protected String prepareTextValue(Cell cell){
        String value = null;
        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    Date date =  cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
                    value = sdf.format(date);
                }
                else{
                    value = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                if(Cell.CELL_TYPE_NUMERIC == cell.getCachedFormulaResultType()){
                    value = String.valueOf(cell.getNumericCellValue());
                    break;

                }
                else{
                    value = cell.getStringCellValue();
                    break;
                }
        }
        return value;
    }



    protected void savefileToTempDir(byte [] data, String endOfPath){
        String tempDir = System.getProperty(TEMP_DIR);
        File f = new File(tempDir+endOfPath);
        FileOutputStream fos = null;
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {

            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public abstract List<T> readPrice(byte [] data);

    protected void addIfNotHeader(Row hfsRow) {
        int n = hfsRow.getRowNum();
        if (hfsRow.getRowNum() != 0 && hfsRow.getRowNum() != 1) {
            price.add(baseModel);
        }
    }

    protected void executeBatch() {
        counter++;
        if(counter>=BATCH){
            saveObjectsFromListToDataBase();
            counter = 0;
            price.clear();
        }
    }


    protected void closeExcel(Object o){
        ByteArrayInputStream is = (ByteArrayInputStream) o;
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    protected abstract void saveObjectsFromListToDataBase();
}
