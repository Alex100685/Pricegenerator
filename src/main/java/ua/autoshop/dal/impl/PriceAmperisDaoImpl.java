package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.DaoImpl;
import ua.autoshop.dal.annotation.AllowNullResult;
import ua.autoshop.model.*;
import ua.autoshop.utils.CommonVariables;
import ua.autoshop.utils.filecreator.BrandMatcherContent;
import ua.autoshop.utils.filecreator.CsvCreator;
import ua.autoshop.utils.marginmaker.MarginMaker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Пользователь on 19.10.2015.
 */
public class PriceAmperisDaoImpl extends DaoImpl<PriceAmperis> {

    @AllowNullResult
    @Override
    public List<PriceAmperis> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceAmperis p", PriceAmperis.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    protected String getTableName() {
        return "price_amperis";
    }

    @Override
    protected boolean conditionToSave(PriceAmperis priceAmperis) {
        return priceAmperis.getAvailable()!=null && !priceAmperis.getAvailable().equals("") && !priceAmperis.getAvailable().equals("0");
    }

    @Override
    protected void fillPriceFields(PriceAmperis price, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {
        CommonVariables.writeToFile = true;
        PriceAutoshop priceAutoshop = new PriceAutoshop();
        priceAutoshop.setName(price.getName());
        priceAutoshop.setAvailable(price.getAvailable());
        priceAutoshop.setBrand(price.getProductGroup());
        String code = createTrueArticule(price.getFullCode(), price.getProductGroup(), csvCreator);
        priceAutoshop.setCode(code);
        Double incomePrice = MarginMaker.getTrueIncomePrice(price.getPrice(), margin);
        priceAutoshop.setIncomePrice(incomePrice);
        Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getPrice(), wholeSaleMargin);
        priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
        priceAutoshop.setWholesalePrice(priceAshopWholesale);
        Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
        priceAshop = MarginMaker.roundPrice(priceAshop);
        priceAutoshop.setRetailPrice(priceAshop);
        priceAutoshop.setSupplier("Амперис");
        priceAutoshop.setShelf("Амперис");
        priceAutoshop.setAdditionalInformation("Доставка в течении 2 часов");
    }

    @Override
    protected String getEnityClassName() {
        return "PriceAmperis";
    }

    @Override
    public String getWholeSaleMarginName() {
        return "Амперис ОПТ";
    }

}
