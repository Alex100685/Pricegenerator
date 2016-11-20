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
public class PriceVladDaoImpl extends DaoImpl<PriceVlad> {

    @Override
    public List<PriceVlad> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceVlad p", PriceVlad.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    protected String getTableName() {
        return "price_vlad";
    }

    @Override
    protected boolean conditionToSave(PriceVlad priceVlad) {
        return priceVlad.getLeftByDefault()!=null && !priceVlad.getLeftByDefault().equals("") && !priceVlad.getLeftByDefault().equals("0");
    }

    @Override
    protected void fillPriceFields(PriceVlad price, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {
        CommonVariables.writeToFile = true;
        PriceAutoshop priceAutoshop = new PriceAutoshop();
        priceAutoshop.setName(price.getFullName());
        priceAutoshop.setAvailable(price.getLeftByDefault());
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
        priceAutoshop.setSupplier("Владислав");
        priceAutoshop.setShelf("Владислав");
        priceAutoshop.setAdditionalInformation("Доставка в течении 2 часов");
    }

    @Override
    protected String getEnityClassName() {
        return "PriceVlad";
    }

    @Override
    public String getWholeSaleMarginName() {
        return "Влад ОПТ";
    }

}
