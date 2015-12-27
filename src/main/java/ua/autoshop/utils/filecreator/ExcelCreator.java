package ua.autoshop.utils.filecreator;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import ua.autoshop.model.Comment;
import ua.autoshop.model.PriceAutoshop;
import ua.autoshop.utils.writer.ExcelWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Пользователь on 21.10.2015.
 */
public class ExcelCreator extends FileCreator {

    private FileOutputStream tempOutput;

    private FileOutputStream tempOutputAutoXCatalogTOMarket;

    private FileOutputStream tempOutputAutoXCatalogAutoshopNet;

    private SXSSFWorkbook workbook;

    private SXSSFWorkbook workbookAutoXCatalogTOMarket;

    private SXSSFWorkbook workbookAutoXCatalogAutoshopNet;

    private SXSSFSheet sheet;

    private SXSSFSheet sheetAutoXCatalogTOMarket;

    private SXSSFSheet sheetAutoXCatalogAutoshopNet;

    SXSSFRow row;

    SXSSFRow rowAutoXCatalogTOMarket;

    SXSSFRow rowAutoXCatalogAutoshopNet;


    int rowNumber = 0;

    int rowNumberAutoXCatalogTOMarket = 0;

    int rowNumberAutoXCatalogAutoshopNet = 0;

    @Override
    public void prepareForReading(String filename){
        workbook = ExcelWriter.openForWritting(filename);
        tempOutput = ExcelWriter.openOutputStream(filename);
        sheet = (SXSSFSheet) workbook.createSheet("Прайс");
    }

    @Override
    public void prepareForReadingAutoXCatalogTOMarket(String filename) {
        workbookAutoXCatalogTOMarket = ExcelWriter.openForWritting(filename);
        tempOutputAutoXCatalogTOMarket = ExcelWriter.openOutputStream(filename);
        sheetAutoXCatalogTOMarket = (SXSSFSheet) workbookAutoXCatalogTOMarket.createSheet("Прайс");
    }

    @Override
    public void prepareForReadingAutoXCatalogAutoshopNet(String filename) {
        workbookAutoXCatalogAutoshopNet = ExcelWriter.openForWritting(filename);
        tempOutputAutoXCatalogAutoshopNet = ExcelWriter.openOutputStream(filename);
        sheetAutoXCatalogAutoshopNet = (SXSSFSheet) workbookAutoXCatalogAutoshopNet.createSheet("Прайс");
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
    public void createHeadersAutoXCatalogTOMarket() {
        rowAutoXCatalogTOMarket = (SXSSFRow) sheetAutoXCatalogTOMarket.createRow(rowNumberAutoXCatalogTOMarket);
        rowAutoXCatalogTOMarket.createCell(0).setCellValue("КОД");
        rowAutoXCatalogTOMarket.createCell(1).setCellValue("БРЕНД");
        rowAutoXCatalogTOMarket.createCell(2).setCellValue("НАЗВАНИЕ");
        rowAutoXCatalogTOMarket.createCell(3).setCellValue("КОЛИЧЕСТВО");
        rowAutoXCatalogTOMarket.createCell(4).setCellValue("ЦЕНА ОПТ");
        rowAutoXCatalogTOMarket.createCell(5).setCellValue("ЦЕНА РОЗНИЦА");
        rowAutoXCatalogTOMarket.createCell(6).setCellValue("СРОК ПОСТАВКИ");
        rowAutoXCatalogTOMarket.createCell(7).setCellValue("КАТЕГОРИЯ");
        rowAutoXCatalogTOMarket.createCell(8).setCellValue("ОПИСАНИЕ");
        rowAutoXCatalogTOMarket.createCell(9).setCellValue("КАРТИНКА");
        rowAutoXCatalogTOMarket.createCell(10).setCellValue("ДОП.ИНФО");
        rowAutoXCatalogTOMarket.createCell(11).setCellValue("ПОЛКА");

    }

    @Override
    public void createHeadersAutoXCatalogAutoshopNet() {
        rowAutoXCatalogAutoshopNet = (SXSSFRow) sheetAutoXCatalogAutoshopNet.createRow(rowNumberAutoXCatalogAutoshopNet);
        rowAutoXCatalogAutoshopNet.createCell(0).setCellValue("КОД");
        rowAutoXCatalogAutoshopNet.createCell(1).setCellValue("БРЕНД");
        rowAutoXCatalogAutoshopNet.createCell(2).setCellValue("НАЗВАНИЕ");
        rowAutoXCatalogAutoshopNet.createCell(3).setCellValue("КОЛИЧЕСТВО");
        rowAutoXCatalogAutoshopNet.createCell(4).setCellValue("ЦЕНА ПОСТАВКИ");
        rowAutoXCatalogAutoshopNet.createCell(5).setCellValue("СРОК ПОСТАВКИ");
        rowAutoXCatalogAutoshopNet.createCell(6).setCellValue("КАТЕГОРИЯ");
        rowAutoXCatalogAutoshopNet.createCell(7).setCellValue("ОПИСАНИЕ");
        rowAutoXCatalogAutoshopNet.createCell(8).setCellValue("КАРТИНКА");
        rowAutoXCatalogAutoshopNet.createCell(9).setCellValue("ДОП.ИНФО");
        rowAutoXCatalogAutoshopNet.createCell(10).setCellValue("ПОЛКА");


    }

    @Override
    public void createNextRow(PriceAutoshop price){
        rowNumber++;
        row = (SXSSFRow) sheet.createRow(rowNumber);
        BrandMatcherContent bmc = brandMatchesMap.get(price.getBrand());

        if( bmc!=null){
            String trueBrand =  bmc.getTrueBrand();
            row.createCell(0).setCellValue(trueBrand);
        }
        else{
            row.createCell(0).setCellValue(price.getBrand());
        }
        row.createCell(1).setCellValue(Double.toString(price.getRetailPrice()));
        row.createCell(2).setCellValue(price.getAvailable());
        if(bmc!=null){
            String code = price.getCode();
            code = code.replace(bmc.getArtCut(), "").trim();
            row.createCell(3).setCellValue(code);
        }else{
            row.createCell(3).setCellValue(price.getCode());
        }

        row.createCell(4).setCellValue(price.getName());
        row.createCell(5).setCellValue(price.getSupplier());

    }

    @Override
    public void createNextRowAutoXCatalogTOMarket(PriceAutoshop price, Comment comment) {
        rowNumberAutoXCatalogTOMarket++;
        rowAutoXCatalogTOMarket = (SXSSFRow) sheetAutoXCatalogTOMarket.createRow(rowNumberAutoXCatalogTOMarket);
        BrandMatcherContent bmc = brandMatchesMap.get(price.getBrand());

        if(bmc!=null && price.getCode()!=null && bmc.getArtCut()!=null) {
            String code = price.getCode();
            code = code.replace(bmc.getArtCut(), "").trim();
            rowAutoXCatalogTOMarket.createCell(0).setCellValue(code);

        }else{
            rowAutoXCatalogTOMarket.createCell(0).setCellValue(price.getCode());
        }
        if(bmc!=null){
            String trueBrand =  bmc.getTrueBrand();
            rowAutoXCatalogTOMarket.createCell(1).setCellValue(trueBrand);
        }
        else{
            rowAutoXCatalogTOMarket.createCell(1).setCellValue(price.getBrand());
        }
        rowAutoXCatalogTOMarket.createCell(2).setCellValue(price.getName());
        rowAutoXCatalogTOMarket.createCell(3).setCellValue(price.getAvailable());
        if(!price.getSupplier().equals("ТОМАРКЕТ")) {
            rowAutoXCatalogTOMarket.createCell(4).setCellValue(price.getWholesalePrice());
            rowAutoXCatalogTOMarket.createCell(5).setCellValue(price.getRetailPrice());
        }else{
            rowAutoXCatalogTOMarket.createCell(4).setCellValue(price.getWholesaleToMarket());
            rowAutoXCatalogTOMarket.createCell(5).setCellValue(price.getRetailTomarket());
        }
        rowAutoXCatalogTOMarket.createCell(6).setCellValue("1");
        if(price.getCategory()!=null){
            rowAutoXCatalogTOMarket.createCell(7).setCellValue(price.getCategory());
        }
        else {
            rowAutoXCatalogTOMarket.createCell(7).setCellValue("11");
        }
        rowAutoXCatalogTOMarket.createCell(8).setCellValue(" "); //no description field
        rowAutoXCatalogTOMarket.createCell(9).setCellValue(" "); //no picture field
        if(price.getSupplier().equals("ТОМАРКЕТ")){
            rowAutoXCatalogTOMarket.createCell(10).setCellValue(" "); //no picture field
        }
        else{
            if(comment!=null && !comment.equals("")) {
                rowAutoXCatalogTOMarket.createCell(10).setCellValue(comment.getText());
            }else{
                rowAutoXCatalogTOMarket.createCell(10).setCellValue(price.getAdditionalInformation());
            }
        }
        rowAutoXCatalogTOMarket.createCell(11).setCellValue(price.getShelf());


    }

    @Override
    public void createNextRowAutoXCatalogAutoshopNet(PriceAutoshop price) {
        rowNumberAutoXCatalogAutoshopNet++;
        rowAutoXCatalogAutoshopNet = (SXSSFRow) sheetAutoXCatalogAutoshopNet.createRow(rowNumberAutoXCatalogAutoshopNet);
        BrandMatcherContent bmc = brandMatchesMap.get(price.getBrand());
        if(bmc!=null && price.getCode()!=null && bmc.getArtCut()!=null) {
            String code = price.getCode();
            code = code.replace(bmc.getArtCut(), "").trim();
            rowAutoXCatalogAutoshopNet.createCell(0).setCellValue(code);

        }else{
            rowAutoXCatalogAutoshopNet.createCell(0).setCellValue(price.getCode());
        }
        if(bmc!=null){
            String trueBrand =  bmc.getTrueBrand();
            rowAutoXCatalogAutoshopNet.createCell(1).setCellValue(trueBrand);
        }
        else{
            rowAutoXCatalogAutoshopNet.createCell(1).setCellValue(price.getBrand());
        }
        rowAutoXCatalogAutoshopNet.createCell(2).setCellValue(price.getName());
        rowAutoXCatalogAutoshopNet.createCell(3).setCellValue(price.getAvailable());
        rowAutoXCatalogAutoshopNet.createCell(4).setCellValue(price.getRetailPrice());
        rowAutoXCatalogAutoshopNet.createCell(5).setCellValue("1");
        if(price.getCategory()!=null){
            rowAutoXCatalogAutoshopNet.createCell(6).setCellValue(price.getCategory());
        }
        else {
            rowAutoXCatalogAutoshopNet.createCell(6).setCellValue("1");
        }
        rowAutoXCatalogAutoshopNet.createCell(7).setCellValue(" "); //no description field
        rowAutoXCatalogAutoshopNet.createCell(8).setCellValue(" "); //no picture field
        rowAutoXCatalogAutoshopNet.createCell(9).setCellValue(" "); //no picture field
        rowAutoXCatalogTOMarket.createCell(10).setCellValue(price.getShelf());

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

    @Override
    public void finishReadingAutoXCatalogTOMarket() {
        try {
            workbookAutoXCatalogTOMarket.write(tempOutputAutoXCatalogTOMarket);
            workbookAutoXCatalogTOMarket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            tempOutputAutoXCatalogTOMarket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void finishReadingAutoXCatalogAutoshopNet() {
        try {
            workbookAutoXCatalogAutoshopNet.write(tempOutputAutoXCatalogAutoshopNet);
            workbookAutoXCatalogAutoshopNet.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            tempOutputAutoXCatalogAutoshopNet.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getSuffix() {
        return ".xlsx";
    }


}
