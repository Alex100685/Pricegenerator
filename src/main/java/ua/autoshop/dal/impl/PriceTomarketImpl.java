package ua.autoshop.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.autoshop.dal.Dao;
import ua.autoshop.dal.DaoImpl;
import ua.autoshop.model.*;
import ua.autoshop.utils.CommonVariables;
import ua.autoshop.utils.filecreator.BrandMatcherContent;
import ua.autoshop.utils.filecreator.CsvCreator;
import ua.autoshop.utils.marginmaker.MarginMaker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Пользователь on 21.11.2015.
 */
public class PriceTomarketImpl extends DaoImpl<PriceTomarket> {

    @Override
    public List<PriceTomarket> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceTomarket p", PriceTomarket.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    protected String getTableName() {
        return "price_tomarket";
    }

    @Override
    protected boolean conditionToSave(PriceTomarket priceTomarket) {
        return priceTomarket.getAvailableOnStock()!=null && !priceTomarket.getAvailableOnStock().equals("") && !priceTomarket.getAvailableOnStock().equals("0");
    }

    @Override
    protected void fillPriceFields(PriceTomarket price, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {
        CommonVariables.writeToFile = true;
        PriceAutoshop priceAutoshop = new PriceAutoshop();
        priceAutoshop.setName(price.getProductName());
        priceAutoshop.setAvailable(price.getAvailableOnStock());
        priceAutoshop.setBrand(price.getBrand());
        String code = createTrueArticule(price.getArticule(), price.getBrand(), csvCreator);
        if(code!=null){
            code = code.trim();
        }
        priceAutoshop.setCode(code);
        Double incomePrice = MarginMaker.getTrueIncomePrice(price.getIncomePrice(), margin);
        priceAutoshop.setIncomePrice(incomePrice);
        Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getWholesalePrice(), wholeSaleMargin);
        priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
        Double wholesalePriceTomarket = MarginMaker.addWholesalePrice(price.getWholesalePrice(), wholeSaleMargin);
        priceAutoshop.setWholesaleToMarket(wholesalePriceTomarket);
        priceAutoshop.setWholesalePrice(priceAshopWholesale);
        priceAutoshop.setCategory(price.getCategoryName());
        Double priceAshop = MarginMaker.addMarginToPrice(price.getIncomePrice(), margin);
        priceAshop = MarginMaker.roundPrice(priceAshop);
        priceAutoshop.setRetailPrice(priceAshop);
        Double retailPriceTomarket = MarginMaker.addWholesalePrice(price.getRetailPrice(), wholeSaleMargin);
        priceAutoshop.setRetailTomarket(retailPriceTomarket);
        priceAutoshop.setSupplier("ТОМАРКЕТ");
        priceAutoshop.setShelf(price.getShelfOfProduct());
        priceAutoshop.setPicture(price.getPicture());
    }

    @Override
    protected String getEnityClassName() {
        return "PriceTomarket";
    }

    @Override
    public String getWholeSaleMarginName() {
        return "ТОМАРКЕТ ОПТ";
    }
}
