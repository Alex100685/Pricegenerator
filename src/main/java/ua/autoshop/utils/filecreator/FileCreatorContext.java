package ua.autoshop.utils.filecreator;

/**
 * Created by Пользователь on 21.10.2015.
 */
public class FileCreatorContext {

    public static FileCreator getStrategy(String index){
        FileCreator fileCreator = null;
        if(index.equals("csv")){
            fileCreator =  new CsvCreator();
        }
        if(index.equals("xlsx")){
            fileCreator = new ExcelCreator();
        }
        return fileCreator;
    }

}
