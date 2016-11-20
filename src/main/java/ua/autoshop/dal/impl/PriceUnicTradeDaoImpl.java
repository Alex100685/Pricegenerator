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
public class PriceUnicTradeDaoImpl extends DaoImpl<PriceUnicTrade> {

    @Override
    public List<PriceUnicTrade> getAllModelsIterable(int offset, int max) {
        return entityManager.createQuery("SELECT p FROM PriceUnicTrade p", PriceUnicTrade.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    protected String getTableName() {
        return "price_unictrade";
    }

    @Override
    protected boolean conditionToSave(PriceUnicTrade priceUnicTrade) {
        return priceUnicTrade.getAvailable()!=null && !priceUnicTrade.getAvailable().equals("") && !priceUnicTrade.getAvailable().equals("0");
    }

    @Override
    protected void fillPriceFields(PriceUnicTrade price, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {
        CommonVariables.writeToFile = true;
        PriceAutoshop priceAutoshop = new PriceAutoshop();
        priceAutoshop.setName(price.getName());
        priceAutoshop.setAvailable(price.getAvailable());
        priceAutoshop.setBrand(price.getBrand());
        String code = createTrueArticule(price.getArticule(), price.getBrand(), csvCreator);
        priceAutoshop.setCode(code);
        if(price.getCurrency()!=null && price.getCurrency().contains("EUR")) {
            Double incomePrice = MarginMaker.getTrueIncomePrice(price.getPrice(), margin);
            priceAutoshop.setIncomePrice(incomePrice);
            Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getPrice(), wholeSaleMargin);
            priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
            priceAutoshop.setWholesalePrice(priceAshopWholesale);
            Double priceAshop = MarginMaker.addMarginToPrice(price.getPrice(), margin);
            priceAshop = MarginMaker.roundPrice(priceAshop);
            priceAutoshop.setRetailPrice(priceAshop);
        }else{
            Double incomePrice = MarginMaker.getTrueIncomePriceNoCurrency(price.getPrice(), margin);
            priceAutoshop.setIncomePrice(incomePrice);
            Double priceAshopWholesale = MarginMaker.addMarginToPriceNoCurrency(price.getPrice(), wholeSaleMargin);
            priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
            priceAutoshop.setWholesalePrice(priceAshopWholesale);
            Double priceAshop = MarginMaker.addMarginToPriceNoCurrency(price.getPrice(), margin);
            priceAshop = MarginMaker.roundPrice(priceAshop);
            priceAutoshop.setRetailPrice(priceAshop);
        }

        priceAutoshop.setSupplier("Юник ТРЕЙД");
        priceAutoshop.setShelf("Юник ТРЕЙД");
        priceAutoshop.setAdditionalInformation("Доставка в течении 2-3 часов (заказ до 15:00)");
    }

    @Override
    protected String getEnityClassName() {
        return "PriceUnicTrade";
    }

    @Override
    public String getWholeSaleMarginName() {
        return "Юник ТРЕЙД ОПТ";
    }
}
