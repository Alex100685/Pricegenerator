package ua.autoshop.utils.savers.impl.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ua.autoshop.model.BaseModel;
import ua.autoshop.model.PriceGerasimenko;
import ua.autoshop.utils.savers.impl.BaseReader;
import ua.autoshop.utils.sixlsx.excel.xlsx.Sheet;
import ua.autoshop.utils.sixlsx.excel.xlsx.SimpleXLSXWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 15.10.2015.
 */
public class BaseExcelReader <T extends BaseModel> extends BaseReader {

    public BaseExcelReader(Class clazz) {
        super(clazz);
    }

    protected static final String END_OF_PATH = "/temp.xlsx";

    @Override
    public List<T> readPrice(byte [] data) {
        price = new ArrayList<>();
        SimpleXLSXWorkbook workbook = openForReading(data);
        Sheet sheetToRead = workbook.getSheet(0, false);
        Sheet.SheetRowReader reader = sheetToRead.newReader();
        XSSFWorkbook wb = new XSSFWorkbook();
        SXSSFWorkbook hsfWorkbook = new SXSSFWorkbook(wb, 100);
        org.apache.poi.ss.usermodel.Sheet hsfSheet = hsfWorkbook.createSheet();
        ua.autoshop.utils.sixlsx.excel.xlsx.Cell[] row;
        int rowPos = 0;
        while ((row = reader.readRow()) != null) {
            org.apache.poi.ss.usermodel.Row hfsRow = hsfSheet.createRow(rowPos);
            baseModel = buildInstance();

            int cellPos = 0;
            for (ua.autoshop.utils.sixlsx.excel.xlsx.Cell cell : row) {
                if(cell != null){
                    org.apache.poi.ss.usermodel.Cell hfsCell = hfsRow.createCell(cellPos);
                    hfsCell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
                    hfsCell.setCellValue(cell.getValue());
                    String value = prepareTextValue(hfsCell);
                    baseModel = writeCellToObject(baseModel, hfsCell, value);
                }
                cellPos++;
            }
            addIfNotHeader(hfsRow);
            executeBatch();
            rowPos++;
        }
        try {
            workbook.close();
            hsfWorkbook.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return price;
    }

    protected SimpleXLSXWorkbook openForReading(byte [] data){
        Object [] array = new Object [2];
        String tempDir = System.getProperty(TEMP_DIR);
        savefileToTempDir(data, END_OF_PATH);
        SimpleXLSXWorkbook workbook = new SimpleXLSXWorkbook(new File(tempDir + END_OF_PATH));
        return workbook;
    }

    /*protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value){
        PriceGerasimenko priceGerasimenko = (PriceGerasimenko) price;
        if(value!=null) {
            value = value.replace(" ", "");
        }
        if(cell.getColumnIndex()==0) {
            priceGerasimenko.setActiveCode(value);
        }
        if(cell.getColumnIndex()==1){
            priceGerasimenko.setCatalogNumber(value);
        }
        if(cell.getColumnIndex()==2){
            priceGerasimenko.setBrand(value);
        }
        if(cell.getColumnIndex()==3){
            priceGerasimenko.setProductDescription(value);
        }
        if(cell.getColumnIndex()==4){
            priceGerasimenko.setEatDescription(value);
        }
        if(cell.getColumnIndex()==5){
            priceGerasimenko.setClientPrice(value);
        }
        if(cell.getColumnIndex()==6){
            priceGerasimenko.setAvailableOnCentralYourBranch(value);
        }
        if(cell.getColumnIndex()==7){
            priceGerasimenko.setAvailableOnCentral(value);
        }
        if(cell.getColumnIndex()==7){
            priceGerasimenko.setAvailableOnAnother(value);
        }
        return priceGerasimenko;
    }*/

    protected BaseModel writeCellToObject(BaseModel price, Cell cell, String value){
        PriceGerasimenko priceGerasimenko = (PriceGerasimenko) price;
        if(value!=null) {
            value = value.replace(" ", "");
        }
        if(cell.getColumnIndex()==columnMatches.getCodeMatch()){
            priceGerasimenko.setCatalogNumber(value);
        }
        if(cell.getColumnIndex()==columnMatches.getBrandMatch()){
            priceGerasimenko.setBrand(value);
        }
        if(cell.getColumnIndex()==columnMatches.getNameMatch()){
            priceGerasimenko.setProductDescription(value);
        }
        if(cell.getColumnIndex()==columnMatches.getIncomePriceMatch()){
            priceGerasimenko.setClientPrice(value);
        }
        if(cell.getColumnIndex()==columnMatches.getAvailableMatch()){
            priceGerasimenko.setAvailableOnCentralYourBranch(value);
        }
        return priceGerasimenko;
    }

    @Override
    protected void saveObjectsFromListToDataBase() {
        readerManager.saveAllPriceElit(price);
    }
}
