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
public class PriceIntercarsiDaoImpl extends DaoImpl<PriceIntercarsi> {

    @Override
    public List<PriceIntercarsi> getAllModelsIterable(int offset, int max) {

        return entityManager.createQuery("SELECT p FROM PriceIntercarsi p", PriceIntercarsi.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    protected String getTableName() {
        return "price_intercarsi";
    }

    @Override
    protected boolean conditionToSave(PriceIntercarsi priceIntercarsi) {
        return priceIntercarsi.getAvailableUr1()!=null && !priceIntercarsi.getAvailableUr1().equals("") && !priceIntercarsi.getAvailableUr1().equals("0");
    }

    @Override
    protected void fillPriceFields(PriceIntercarsi price, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {
        CommonVariables.writeToFile = true;
        PriceAutoshop priceAutoshop = new PriceAutoshop();
        priceAutoshop.setName(price.getName());
        priceAutoshop.setAvailable(price.getAvailableUr1());
        priceAutoshop.setBrand(price.getBrand());
        String articule = createTrueArticule(price.getArticule(), price.getBrand(), csvCreator);
        priceAutoshop.setCode(articule);
        Double incomePrice = MarginMaker.getTrueIncomePrice(price.getWholesalePrice(), margin);
        priceAutoshop.setIncomePrice(incomePrice);
        Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getWholesalePrice(), wholeSaleMargin);
        priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
        priceAutoshop.setWholesalePrice(priceAshopWholesale);
        Double priceAshop = MarginMaker.addMarginToPrice(price.getWholesalePrice(), margin);
        priceAshop = MarginMaker.roundPrice(priceAshop);
        priceAutoshop.setRetailPrice(priceAshop);
        priceAutoshop.setSupplier("Интеркарс");
        priceAutoshop.setShelf("Интеркарс");
        priceAutoshop.setAdditionalInformation("Доставка в течении 2 часов");
    }

    @Override
    protected String getEnityClassName() {
        return "PriceIntercarsi";
    }

    @Override
    public String getWholeSaleMarginName() {
        return "Интеркарс ОПТ";
    }
}
