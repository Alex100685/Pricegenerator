package ua.autoshop.utils.writer;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.autoshop.utils.sixlsx.excel.xlsx.Sheet;
import ua.autoshop.utils.sixlsx.excel.xlsx.SheetCommentWriter;
import ua.autoshop.utils.sixlsx.excel.xlsx.SimpleXLSXWorkbook;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

import static ua.autoshop.utils.sixlsx.excel.xlsx.Sheet.*;

/**
 * Created by Пользователь on 16.10.2015.
 */
public class ExcelWriter {

    private static Logger logger = LoggerFactory.getLogger(ExcelWriter.class);

    protected static final String TEMP_DIR =  "java.io.tmpdir";
    protected static final String OUTPUT_FILE =  "/output.xlsx";
    protected static final int LIMIT =  100;

    public static void createFullPathIfNotExists() {
        String tempDir = System.getProperty(TEMP_DIR);
        File targetFile = new File(tempDir+OUTPUT_FILE);
        if(!targetFile.exists()){
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static SXSSFWorkbook openForWritting(){
        createFullPathIfNotExists();
        XSSFWorkbook workbook = new XSSFWorkbook();
        SXSSFWorkbook hsfWorkbook = new SXSSFWorkbook(workbook, LIMIT);
        return hsfWorkbook;
    }

    public static FileOutputStream openOutputStream() {
        createFullPathIfNotExists();
        String tempDir = System.getProperty(TEMP_DIR);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(tempDir+OUTPUT_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fos;
    }

}
