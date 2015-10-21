package ua.autoshop.utils.filecreator;

import ua.autoshop.model.PriceAutoshop;

/**
 * Created by Пользователь on 21.10.2015.
 */
public abstract class FileCreator {

    protected static final String TEMP_DIR =  "java.io.tmpdir";

    public abstract void prepareForReading();

    public abstract void createHeaders();

    public abstract void createNextRow(PriceAutoshop price);

    public abstract void finishReading();

}
