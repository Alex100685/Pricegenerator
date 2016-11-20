package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.DaoImpl;
import ua.autoshop.dal.annotation.AllowNullResult;
import ua.autoshop.model.*;
import ua.autoshop.utils.combiner.defaultcolmatches.ColumnMatchesDefaultContext;
import ua.autoshop.utils.filecreator.CsvCreator;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class ColumnMatchesDaoImpl extends DaoImpl<ColumnMatches> {

    @AllowNullResult
    @Override
    public ColumnMatches getColumnMatches(String className) {
        className = className.substring(className.lastIndexOf('.')+1);
        Query query = entityManager.createQuery("SELECT m FROM ColumnMatches m WHERE m.name ='" + className + "'", ColumnMatches.class);
        return (ColumnMatches) query.getSingleResult();
    }

    @Override
    public List getAllModelsIterable(int offset, int max) {
        return null;
    }

    @Override
    protected String getTableName() {
        return "column_matches";
    }

    @Override
    protected boolean conditionToSave(ColumnMatches columnMatches) {
        return false;
    }

    @Override
    protected void fillPriceFields(ColumnMatches columnMatches, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {

    }

    @Override
    protected String getEnityClassName() {
        return "ColumnMatches";
    }

    @Override
    public String getWholeSaleMarginName() {
        return null;
    }
}
