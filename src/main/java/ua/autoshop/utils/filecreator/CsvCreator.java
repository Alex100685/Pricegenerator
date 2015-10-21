package ua.autoshop.utils.filecreator;

import ua.autoshop.model.PriceAutoshop;
import com.csvreader.CsvWriter;

import java.io.*;

/**
 * Created by Пользователь on 21.10.2015.
 */
public class CsvCreator extends FileCreator {

    private static final String OUTPUT_FILE = "/output.csv";

    private static final char DELIMITER = ';';

    CsvWriter csvOutput;


    @Override
    public void prepareForReading() {
        String tempDir = System.getProperty(TEMP_DIR);
        File targetFile = new File(tempDir+OUTPUT_FILE);
        if(targetFile.exists()){
                targetFile.delete();
        }
        try {
            targetFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            csvOutput = new CsvWriter(new FileWriter(targetFile, true), DELIMITER);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createHeaders() {
        try {
            csvOutput.write("Бренд");
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
    public void createNextRow(PriceAutoshop price) {
        try {
            csvOutput.write(price.getBrand());
            csvOutput.write(Double.toString(price.getRetailPrice()));
            csvOutput.write(price.getAvailable());
            csvOutput.write(price.getCode());
            csvOutput.write(price.getName());
            csvOutput.write(price.getSupplier());
            csvOutput.endRecord();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void finishReading() {

    }
}
