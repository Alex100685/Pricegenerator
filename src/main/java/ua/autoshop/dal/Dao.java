package ua.autoshop.dal;

import ua.autoshop.model.*;

import java.util.List;

/**
 * Created by Пользователь on 08.10.2015.
 */
public interface Dao <T extends BaseModel> {

    public static final int PORTION = 5000;

    List <T> findAll();

    List<T> findByCode(String code);

    T findByName(String name);

    void delete(T object);

    void save();

    void saveList(List<T> priceList);

    void cleanTable();

    void iterateAllAndSaveToMainTable(Margin margin);

    public List <T> getAllModelsIterable(int offset, int max);

    public void save(T t);

    List<T> getByPrice(String pattern);

    List<T> getByCode(String pattern);

    List<T> getByName(String pattern);

    void sortPriceByArticule();

    T findByThreeParams(String brand, String trueBrand, String cut);

    T getColumnMatches(String className);


}
