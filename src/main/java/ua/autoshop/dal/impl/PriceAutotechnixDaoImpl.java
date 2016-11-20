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
 * Created by Пользователь on 08.10.2015.
 */
public class PriceAutotechnixDaoImpl extends DaoImpl<PriceAutotechnix> {

    @Override
    protected String getTableName() {
        return "price_autotechnix";
    }

    @Override
    protected boolean conditionToSave(PriceAutotechnix priceAutotechnix) {
        return priceAutotechnix.getAvailableKiev1()!=null
                && !priceAutotechnix.getAvailableKiev1().equals("")
                && !priceAutotechnix.getAvailableKiev1().equals("0");
    }

    @Override
    protected void fillPriceFields(PriceAutotechnix price, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {
        CommonVariables.writeToFile = true;
        PriceAutoshop priceAutoshop = new PriceAutoshop();
        priceAutoshop.setName(price.getName());
        priceAutoshop.setAvailable(price.getAvailableKiev1());
        priceAutoshop.setBrand(price.getBrand());
        String code = createTrueArticule(price.getCode(), price.getBrand(), csvCreator);
        priceAutoshop.setCode(code);
        Double incomePrice = MarginMaker.getTrueIncomePrice(price.getPrice(), margin);
        priceAutoshop.setIncomePrice(incomePrice);
        Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getPrice(), wholeSaleMargin);
        priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
        priceAutoshop.setWholesalePrice(priceAshopWholesale);
        Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
        priceAshop = MarginMaker.roundPrice(priceAshop);
        priceAutoshop.setRetailPrice(priceAshop);
        priceAutoshop.setSupplier("Автотехникс");
        priceAutoshop.setShelf("Автотехникс");
        priceAutoshop.setAdditionalInformation("Доставка в течении 2 часов");
    }

    @Override
    protected String getEnityClassName() {
        return "PriceAutotechnix";
    }

    @Override
    public String getWholeSaleMarginName() {
        return "Автотехникс ОПТ";
    }


    @Override
    public List<PriceAutotechnix> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceAutotechnix p", PriceAutotechnix.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }
}
