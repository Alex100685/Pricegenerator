package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.DaoImpl;
import ua.autoshop.dal.annotation.AllowNullResult;
import ua.autoshop.model.Margin;
import ua.autoshop.model.PriceAutoshop;
import ua.autoshop.model.User;
import ua.autoshop.utils.filecreator.CsvCreator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 08.10.2015.
 */
public class UserDaoImpl extends DaoImpl<User> {

    @Override
    public User findByName(String name) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username ='"+name+"'", User.class);
        return  (User) query.getSingleResult();
    }

    @Override
    public List getAllModelsIterable(int offset, int max) {
        return null;
    }

    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected boolean conditionToSave(User user) {
        return false;
    }

    @Override
    protected void fillPriceFields(User user, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {

    }

    @Override
    protected String getEnityClassName() {
        return "User";
    }

    @Override
    public String getWholeSaleMarginName() {
        return null;
    }
}
