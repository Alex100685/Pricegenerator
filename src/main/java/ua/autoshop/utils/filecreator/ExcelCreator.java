package ua.autoshop.utils.filecreator;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import ua.autoshop.model.PriceAutoshop;
import ua.autoshop.utils.writer.ExcelWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Пользователь on 21.10.2015.
 */
public class ExcelCreator extends FileCreator {

    private FileOutputStream tempOutput;

    private SXSSFWorkbook workbook;

    private SXSSFSheet sheet;

    SXSSFRow row;

    int rowNumber = 0;

    @Override
    public void prepareForReading(){
        workbook = ExcelWriter.openForWritting();
        tempOutput = ExcelWriter.openOutputStream();
        sheet = (SXSSFSheet) workbook.createSheet("Прайс");
    }

    @Override
    public void createHeaders(){
        row = (SXSSFRow) sheet.createRow(rowNumber);
        row.createCell(0).setCellValue("Бренд");
        row.createCell(1).setCellValue("Розничная цена");
        row.createCell(2).setCellValue("Наличие всего");
        row.createCell(3).setCellValue("Код");
        row.createCell(4).setCellValue("Описание");
        row.createCell(5).setCellValue("Поставщик");
    }

    @Override
    public void createNextRow(PriceAutoshop price){
        rowNumber++;
        row = (SXSSFRow) sheet.createRow(rowNumber);
        row.createCell(0).setCellValue(price.getBrand());
        row.createCell(1).setCellValue(Double.toString(price.getRetailPrice()));
        row.createCell(2).setCellValue(price.getAvailable());
        row.createCell(3).setCellValue(price.getCode());
        row.createCell(4).setCellValue(price.getName());
        row.createCell(5).setCellValue(price.getSupplier());

    }

    @Override
    public void finishReading(){
        try {
            workbook.write(tempOutput);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            tempOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
