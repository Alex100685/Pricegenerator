package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.DaoImpl;
import ua.autoshop.dal.annotation.AllowNullResult;
import ua.autoshop.model.*;
import ua.autoshop.utils.filecreator.CsvCreator;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 14.10.2015.
 */
public class MarginDaoImpl extends DaoImpl<Margin> {

    @AllowNullResult
    @Override
    public Margin findByName(String name) {
        Query query = entityManager.createQuery("SELECT m FROM Margin m WHERE m.priceName ='"+name+"'", Margin.class);
        return (Margin) query.getSingleResult();
    }

    @Override
    public List getAllModelsIterable(int offset, int max) {
        return null;
    }

    @Override
    public String getWholeSaleMarginName() {
        return null;
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected boolean conditionToSave(Margin margin) {
        return false;
    }

    @Override
    protected void fillPriceFields(Margin margin, Margin margin2, Margin wholeSaleMargin, CsvCreator csvCreator) {

    }

    @Override
    protected String getEnityClassName() {
        return null;
    }
}
