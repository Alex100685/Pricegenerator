package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.DaoImpl;
import ua.autoshop.dal.annotation.AllowNullResult;
import ua.autoshop.model.*;
import ua.autoshop.utils.filecreator.CsvCreator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 09.10.2015.
 */
public class UpdatesDaoImpl extends DaoImpl<Updates> {

    @AllowNullResult
    @Override
    public Updates findByName(String name) {
        Query query = entityManager.createQuery("SELECT u FROM Updates u WHERE u.priceName ='"+name+"'", Updates.class);
        return  (Updates) query.getSingleResult();
    }

    @Override
    public List getAllModelsIterable(int offset, int max) {
        return null;
    }

    @Override
    protected String getTableName() {
        return "updates";
    }

    @Override
    protected boolean conditionToSave(Updates updates) {
        return false;
    }

    @Override
    protected void fillPriceFields(Updates updates, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {

    }

    @Override
    protected String getEnityClassName() {
        return "Updates";
    }

    @Override
    public String getWholeSaleMarginName() {
        return null;
    }

}
