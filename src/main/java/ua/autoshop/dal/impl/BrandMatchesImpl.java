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
 * Created by Пользователь on 05.12.2015.
 */
public class BrandMatchesImpl extends DaoImpl<BrandMatches> {


    @AllowNullResult
    @Override
    public BrandMatches findByName(String name) {
        try {
            Query query = entityManager.createQuery("SELECT b FROM BrandMatches b WHERE b.priceBrand ='" + name + "'", BrandMatches.class);
            BrandMatches b = (BrandMatches) query.getSingleResult();
            return b;
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    protected String getEnityClassName() {
        return "BrandMatches";
    }

    @Override
    public String getWholeSaleMarginName() {
        return null;
    }

    @AllowNullResult
    @Override
    public BrandMatches findByThreeParams(String brand, String trueBrand, String cut) {
        try {
            Query query = entityManager.createQuery("SELECT b FROM BrandMatches b WHERE b.priceBrand ='" + brand + "' AND b.priceBrandMatch = '"+trueBrand+"' AND b.cutFromArticule = '"+cut+"'", BrandMatches.class);
            BrandMatches b = (BrandMatches) query.getSingleResult();
            return b;
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List getAllModelsIterable(int offset, int max) {
        return null;
    }

    @Override
    protected String getTableName() {
        return "brand_matches";
    }

    @Override
    protected boolean conditionToSave(BrandMatches brandMatches) {
        return false;
    }

    @Override
    protected void fillPriceFields(BrandMatches brandMatches, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {

    }
}
