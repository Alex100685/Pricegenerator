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
 * Created by Пользователь on 13.12.2015.
 */
public class PriceElitOriginalDaoImpl extends DaoImpl<PriceElitOriginal> {

    @Override
    public List<PriceElitOriginal> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceElitOriginal p", PriceElitOriginal.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    protected String getTableName() {
        return "price_elit_original";
    }

    @Override
    protected boolean conditionToSave(PriceElitOriginal priceElitOriginal) {
        return priceElitOriginal.getAvailable()!=null && !priceElitOriginal.getAvailable().equals("") && !priceElitOriginal.getAvailable().equals("0");
    }

    @Override
    protected void fillPriceFields(PriceElitOriginal price, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {
        CommonVariables.writeToFile = true;
        PriceAutoshop priceAutoshop = new PriceAutoshop();
        priceAutoshop.setName(price.getName());
        priceAutoshop.setAvailable(price.getAvailable());
        priceAutoshop.setBrand(price.getBrand());
        String code = createTrueArticule(price.getArticule(), price.getBrand(), csvCreator);
        priceAutoshop.setCode(code);
        Double incomePrice = MarginMaker.getTrueIncomePrice(price.getPrice(), margin);
        priceAutoshop.setIncomePrice(incomePrice);
        Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getPrice(), wholeSaleMargin);
        priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
        priceAutoshop.setWholesalePrice(priceAshopWholesale);
        Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
        priceAshop = MarginMaker.roundPrice(priceAshop);
        priceAutoshop.setRetailPrice(priceAshop);
        priceAutoshop.setSupplier("Элит ОРИГИНАЛ");
        priceAutoshop.setShelf("Элит ОРИГИНАЛ");
        priceAutoshop.setAdditionalInformation(price.getSupplyCondition());
    }

    @Override
    protected String getEnityClassName() {
        return "PriceElitOriginal";
    }

    @Override
    public String getWholeSaleMarginName() {
        return "Элит ОРИГИНАЛ ОПТ";
    }

}
