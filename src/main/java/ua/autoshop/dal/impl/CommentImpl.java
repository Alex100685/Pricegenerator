package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.DaoImpl;
import ua.autoshop.dal.annotation.AllowNullResult;
import ua.autoshop.dal.manager.Manager;
import ua.autoshop.model.*;
import ua.autoshop.utils.filecreator.CsvCreator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 11.12.2015.
 */
public class CommentImpl extends DaoImpl<Comment> {

    @AllowNullResult
    @Override
    public Comment findByName(String name) {
        Query query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.id ="+1, Comment.class);
        return  (Comment) query.getSingleResult();
    }

    @Override
    public List getAllModelsIterable(int offset, int max) {
        return null;
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected boolean conditionToSave(Comment comment) {
        return false;
    }

    @Override
    protected void fillPriceFields(Comment comment, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {

    }

    @Override
    protected String getEnityClassName() {
        return null;
    }

    @Override
    public String getWholeSaleMarginName() {
        return null;
    }
}
