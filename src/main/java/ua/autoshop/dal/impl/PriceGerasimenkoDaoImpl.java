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
public class PriceGerasimenkoDaoImpl extends DaoImpl<PriceGerasimenko> {

    @Override
    public List<PriceGerasimenko> getAllModelsIterable(int offset, int max) {

        return entityManager.createQuery("SELECT p FROM PriceGerasimenko p", PriceGerasimenko.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

    @Override
    public String getWholeSaleMarginName() {
        return "Элит ОПТ";
    }

    @Override
    protected String getTableName() {
        return "price_gerasimenko";
    }

    @Override
    protected boolean conditionToSave(PriceGerasimenko priceGerasimenko) {
        return priceGerasimenko.getAvailableOnCentralYourBranch()!=null && !priceGerasimenko.getAvailableOnCentralYourBranch().equals("") && !priceGerasimenko.getAvailableOnCentralYourBranch().equals("0");
    }

    @Override
    protected void fillPriceFields(PriceGerasimenko price, Margin margin, Margin wholeSaleMargin, CsvCreator csvCreator) {
        CommonVariables.writeToFile = true;
        PriceAutoshop priceAutoshop = new PriceAutoshop();
        priceAutoshop.setName(price.getProductDescription());
        priceAutoshop.setAvailable(price.getAvailableOnCentralYourBranch());
        priceAutoshop.setBrand(price.getBrand());
        String code = createTrueArticule(price.getCatalogNumber(), price.getBrand(), csvCreator);
        priceAutoshop.setCode(code);
        Double incomePrice = MarginMaker.getTrueIncomePrice(price.getClientPrice(), margin);
        priceAutoshop.setIncomePrice(incomePrice);
        Double priceAshopWholesale = MarginMaker.addMarginToPrice(price.getClientPrice(), wholeSaleMargin);
        priceAshopWholesale = MarginMaker.roundPrice(priceAshopWholesale);
        priceAutoshop.setWholesalePrice(priceAshopWholesale);
        Double priceAshop = MarginMaker.addMarginToPrice(price.getClientPrice(), margin);
        priceAshop = MarginMaker.roundPrice(priceAshop);
        priceAutoshop.setRetailPrice(priceAshop);
        priceAutoshop.setSupplier("Элит");
        priceAutoshop.setShelf("Элит");
        priceAutoshop.setAdditionalInformation("Доставка в течении 2 часов");
    }

    @Override
    protected String getEnityClassName() {
        return "PriceGerasimenko";
    }
}
