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
 * Created by Пользователь on 19.10.2015.
 */
public class PriceGenstarDaoImpl extends DaoImpl<PriceGenstar> {

    @Override
    public List<PriceGenstar> getAllModelsIterable(int offset, int max)
    {
        return entityManager.createQuery("SELECT p FROM PriceGenstar p", PriceGenstar.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    protected String getTableName() {
        return "price_genstar";
    }

    @Override
    protected boolean conditionToSave(PriceGenstar priceGenstar) {
        return priceGenstar.getAvailable()!=null && !priceGenstar.getAvailable().equals("") && !priceGenstar.getAvailable().equals("0");
    }

    @Override
    protected void fillPriceFields(PriceGenstar price, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {
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
        priceAutoshop.setSupplier("Генстар");
        priceAutoshop.setShelf("Генстар");
        priceAutoshop.setAdditionalInformation("Доставка в течении 2 часов");
    }

    @Override
    protected String getEnityClassName() {
        return "PriceGenstar";
    }

    @Override
    public String getWholeSaleMarginName() {
        return "Генстар ОПТ";
    }
}
