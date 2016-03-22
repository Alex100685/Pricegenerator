package ua.autoshop.utils.filecreator;

import org.apache.poi.xssf.streaming.SXSSFRow;
import ua.autoshop.config.ApplicationContextHolder;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.Comment;
import ua.autoshop.model.PriceAutoshop;
import com.csvreader.CsvWriter;

import java.io.*;

/**
 * Created by Пользователь on 21.10.2015.
 */
public class CsvCreator extends FileCreator {



    //private static final String OUTPUT_FILE = "/output.csv";

    private static final char DELIMITER = ';';

    CsvWriter csvOutput;
    CsvWriter csvOutputAutoXCatalogTOMarket;
    CsvWriter csvOutputAutoXCatalogAutoshopNet;


    @Override
    public void prepareForReading(String filename) {
        String tempDir = System.getProperty(TEMP_DIR);
        File targetFile = new File(tempDir+filename);
        if(targetFile.exists()){
                targetFile.delete();
        }
        try {
            targetFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fis = new FileOutputStream(targetFile);
            csvOutput = new CsvWriter(new OutputStreamWriter(fis, "UTF-8"), DELIMITER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void prepareForReadingAutoXCatalogTOMarket(String filename) {
        String tempDir = System.getProperty(TEMP_DIR);
        File targetFile = new File(tempDir+filename);
        if(targetFile.exists()){
            targetFile.delete();
        }
        try {
            targetFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fis = new FileOutputStream(targetFile);
            csvOutputAutoXCatalogTOMarket = new CsvWriter(new OutputStreamWriter(fis, "UTF-8"), DELIMITER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void prepareForReadingAutoXCatalogAutoshopNet(String filename) {
        String tempDir = System.getProperty(TEMP_DIR);
        File targetFile = new File(tempDir+filename);
        if(targetFile.exists()){
            targetFile.delete();
        }
        try {
            targetFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fis = new FileOutputStream(targetFile);
            csvOutputAutoXCatalogAutoshopNet = new CsvWriter(new OutputStreamWriter(fis, "UTF-8"), DELIMITER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createHeaders() {
        try {
            csvOutput.write("Бренд");
            csvOutput.write("Категория");
            csvOutput.write("Розничная цена");
            csvOutput.write("Наличие всего");
            csvOutput.write("Код");
            csvOutput.write("Описание");
            csvOutput.write("Поставщик");
            csvOutput.endRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void createHeadersAutoXCatalogTOMarket() {
        try {
            csvOutputAutoXCatalogTOMarket.write("КОД");
            csvOutputAutoXCatalogTOMarket.write("БРЕНД");
            csvOutputAutoXCatalogTOMarket.write("НАЗВАНИЕ");
            csvOutputAutoXCatalogTOMarket.write("КОЛИЧЕСТВО");
            csvOutputAutoXCatalogTOMarket.write("ЦЕНА ВХОД");
            csvOutputAutoXCatalogTOMarket.write("ЦЕНА ОПТ");
            csvOutputAutoXCatalogTOMarket.write("ЦЕНА РОЗНИЦА");
            csvOutputAutoXCatalogTOMarket.write("СРОК ПОСТАВКИ");
            csvOutputAutoXCatalogTOMarket.write("КАТЕГОРИЯ");
            csvOutputAutoXCatalogTOMarket.write("ОПИСАНИЕ");
            csvOutputAutoXCatalogTOMarket.write("КАРТИНКА");
            csvOutputAutoXCatalogTOMarket.write("ДОП.ИНФО");
            csvOutputAutoXCatalogTOMarket.write("ПОЛКА");
            csvOutputAutoXCatalogTOMarket.endRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void createHeadersAutoXCatalogAutoshopNet() {
        try {
            csvOutputAutoXCatalogAutoshopNet.write("КОД");
            csvOutputAutoXCatalogAutoshopNet.write("БРЕНД");
            csvOutputAutoXCatalogAutoshopNet.write("НАЗВАНИЕ");
            csvOutputAutoXCatalogAutoshopNet.write("КОЛИЧЕСТВО");
            csvOutputAutoXCatalogAutoshopNet.write("ЦЕНА ПОСТАВКИ");
            csvOutputAutoXCatalogAutoshopNet.write("СРОК ПОСТАВКИ");
            csvOutputAutoXCatalogAutoshopNet.write("КАТЕГОРИЯ");
            csvOutputAutoXCatalogAutoshopNet.write("ОПИСАНИЕ");
            csvOutputAutoXCatalogAutoshopNet.write("КАРТИНКА");
            csvOutputAutoXCatalogAutoshopNet.write("ДОП.ИНФО");
            csvOutputAutoXCatalogAutoshopNet.write("ПОЛКА");
            csvOutputAutoXCatalogAutoshopNet.endRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void createNextRow(PriceAutoshop price) {
        try {
            BrandMatcherContent bmc = brandMatchesMap.get(price.getBrand());



            if(bmc!=null){
                String trueBrand =  bmc.getTrueBrand();
                csvOutput.write(trueBrand);
            }
            else{
                csvOutput.write(price.getBrand());
            }

            if(price.getCategory()!=null){
                csvOutput.write(price.getCategory());
            }else{
                csvOutput.write("11");
            }
            csvOutput.write(Double.toString(price.getRetailPrice()));
            csvOutput.write(price.getAvailable());
            if(bmc!=null && price.getCode()!=null && bmc.getArtCut()!=null) {
                String code = price.getCode();
                code = code.replace(bmc.getArtCut(), "").trim();
                csvOutput.write(code);
            }
            else{
                csvOutput.write(price.getCode());
            }
            csvOutput.write(price.getName());
            csvOutput.write(price.getSupplier());
            csvOutput.flush();
            csvOutput.endRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createNextRowAutoXCatalogTOMarket(PriceAutoshop price, Comment comment) {
        try {
            BrandMatcherContent bmc = brandMatchesMap.get(price.getBrand());

            if(bmc!=null && price.getCode()!=null && bmc.getArtCut()!=null) {
                String code = price.getCode();
                code = code.replace(bmc.getArtCut(), "").trim();
                csvOutputAutoXCatalogTOMarket.write(code);

            }else{
                csvOutputAutoXCatalogTOMarket.write(price.getCode());
            }

            if(bmc!=null){
                String trueBrand =  bmc.getTrueBrand();
                csvOutputAutoXCatalogTOMarket.write(trueBrand);
            }
            else{
                csvOutputAutoXCatalogTOMarket.write(price.getBrand());
            }
            csvOutputAutoXCatalogTOMarket.write(price.getName());
            csvOutputAutoXCatalogTOMarket.write(price.getAvailable());
            if(!price.getSupplier().equals("ТОМАРКЕТ")) {
                csvOutputAutoXCatalogTOMarket.write(String.valueOf(price.getIncomePrice()));
                csvOutputAutoXCatalogTOMarket.write(String.valueOf(price.getWholesalePrice()));
                csvOutputAutoXCatalogTOMarket.write(String.valueOf(price.getRetailPrice()));
            }else{
                csvOutputAutoXCatalogTOMarket.write(String.valueOf(price.getIncomePrice()));
                csvOutputAutoXCatalogTOMarket.write(String.valueOf(price.getWholesaleToMarket()));
                csvOutputAutoXCatalogTOMarket.write(String.valueOf(price.getRetailTomarket()));
            }
            csvOutputAutoXCatalogTOMarket.write("1");
            if(price.getCategory()!=null){
                csvOutputAutoXCatalogTOMarket.write(price.getCategory());
            }else{
                csvOutputAutoXCatalogTOMarket.write("11");
            }
            csvOutputAutoXCatalogTOMarket.write(" "); //no description field
            csvOutputAutoXCatalogTOMarket.write(price.getPicture());
            if(price.getSupplier().equals("ТОМАРКЕТ")){
                csvOutputAutoXCatalogTOMarket.write(" "); //
            }
            else{
                if(comment!=null && !comment.equals("")) {
                    csvOutputAutoXCatalogTOMarket.write(comment.getText());
                }
                else{
                    csvOutputAutoXCatalogTOMarket.write(price.getAdditionalInformation());
                }
            }
            csvOutputAutoXCatalogTOMarket.write(price.getShelf());
            csvOutputAutoXCatalogTOMarket.flush();
            csvOutputAutoXCatalogTOMarket.endRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void createNextRowAutoXCatalogAutoshopNet(PriceAutoshop price) {

        try {
            BrandMatcherContent bmc = brandMatchesMap.get(price.getBrand());

            if(bmc!=null && price.getCode()!=null && bmc.getArtCut()!=null) {
                String code = price.getCode();
                code = code.replace(bmc.getArtCut(), "").trim();
                csvOutputAutoXCatalogAutoshopNet.write(code);

            }else{
                csvOutputAutoXCatalogAutoshopNet.write(price.getCode());
            }

            if(bmc!=null){
                String trueBrand =  bmc.getTrueBrand();
                csvOutputAutoXCatalogAutoshopNet.write(trueBrand);
            }
            else{
                csvOutputAutoXCatalogAutoshopNet.write(price.getBrand());
            }
            csvOutputAutoXCatalogAutoshopNet.write(price.getName());
            csvOutputAutoXCatalogAutoshopNet.write(price.getAvailable());
            csvOutputAutoXCatalogAutoshopNet.write(String.valueOf(price.getRetailPrice()));
            csvOutputAutoXCatalogAutoshopNet.write("1");
            if(price.getCategory()!=null){
                csvOutputAutoXCatalogAutoshopNet.write(price.getCategory());
            }else{
                csvOutputAutoXCatalogAutoshopNet.write("1");
            }
            csvOutputAutoXCatalogAutoshopNet.write(" "); //no description field
            csvOutputAutoXCatalogAutoshopNet.write(" "); //no picture field
            csvOutputAutoXCatalogAutoshopNet.write(price.getPicture());
            csvOutputAutoXCatalogAutoshopNet.write(price.getShelf());
            csvOutputAutoXCatalogAutoshopNet.flush();
            csvOutputAutoXCatalogAutoshopNet.endRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void finishReading() {

    }

    @Override
    public void finishReadingAutoXCatalogTOMarket() {

    }

    @Override
    public void finishReadingAutoXCatalogAutoshopNet() {

    }

    @Override
    public String getSuffix() {
        return ".csv";
    }
}
