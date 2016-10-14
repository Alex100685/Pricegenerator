package ua.autoshop.utils.currencyrate;

import javax.xml.bind.annotation.*;

/**
 * Created by Пользователь on 24.03.2016.
 */

@XmlRootElement(name = "currency")
@XmlAccessorType (XmlAccessType.FIELD)
public class Currency {


    String date;
    String digital_code;
    String letter_code;
    String number_of_units;
    String currency_name;
    String exchange_rate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDigital_code() {
        return digital_code;
    }

    public void setDigital_code(String digital_code) {
        this.digital_code = digital_code;
    }

    public String getLetter_code() {
        return letter_code;
    }

    public void setLetter_code(String letter_code) {
        this.letter_code = letter_code;
    }

    public String getNumber_of_units() {
        return number_of_units;
    }

    public void setNumber_of_units(String number_of_units) {
        this.number_of_units = number_of_units;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
}
