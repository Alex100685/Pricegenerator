package ua.autoshop.utils.currencyrate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Пользователь on 24.03.2016.
 */
@XmlRootElement(name = "currencies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyList {

    @XmlElement(name = "currency")
    private List<Currency> currencies = null;

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
