package ua.autoshop.utils.marginmaker;

import ua.autoshop.model.Margin;

/**
 * Created by Пользователь on 15.10.2015.
 */
public class MarginMaker {

    public static Double addWholesalePrice(String strPrice, Margin margin){
        if(strPrice.contains(",")){
            strPrice = strPrice.replace(',', '.');
        }
        if(strPrice.contains("#")){
            strPrice = "0.0";
        }
        Double price = Double.parseDouble(strPrice)*margin.getUsdCurrencyRate();
        return roundPrice(price);
    }

    public static Double addMarginToPriceNoCurrency(String strPrice, Margin margin){
        if(strPrice.contains(",")){
            strPrice = strPrice.replace(',', '.');
        }
        if(strPrice.contains("#")){
            strPrice = "0.0";
        }
        Double price = Double.parseDouble(strPrice);
        if(price < 100){
            price*=margin.getLessThanHundred();
            price+=margin.getLessThanHundredFixed();
            return price;
        }
        if(price >= 100 && price < 300){
            price*=margin.getFromHundredToTreeHundred();
            price+=margin.getFromHundredToTreeHundredFixed();
            return price;
        }
        if(price >= 300 && price < 500){
            price*=margin.getFromTreeHundredToFiveHundred();
            price+=margin.getFromTreeHundredToFiveHundredFixed();
            return price;
        }
        if(price >= 500 && price < 1000){
            price*=margin.getFromFiveHundredToThousand();
            price+=margin.getFromFiveHundredToThousandFixed();
            return price;
        }
        if(price >= 1000 && price < 5000){
            price*=margin.getFromThousandToFiveThousands();
            price+=margin.getFromThousandToFiveThousandsFixed();
            return price;
        }
        if(price >= 5000 && price < 10000){
            price*=margin.getFromFiveToTenThousands();
            price+=margin.getFromFiveToTenThousandsFixed();
            return price;
        }
        if(price >= 10000 && price < 20000){
            price*=margin.getFromTenToTwentyThousands();
            price+=margin.getFromTenToTwentyThousandsFixed();
            return price;
        }
        if(price >= 20000 && price < 50000){
            price*=margin.getFromTwentyToFifty();
            price+=margin.getFromTwentyToFiftyFixed();
            return price;
        }
        if(price >= 50000 && price < 100000){
            price*=margin.getFromFiftyToHundred();
            price+=margin.getFromFiftyToHundredFixed();
            return price;
        }
        if(price > 100000){
            price*=margin.getAboveHundredThousands();
            price+=margin.getAboveHundredThousandsFixed();
        }
        return price;
    }

    public static Double addMarginToPrice(String strPrice, Margin margin){
        if(strPrice.contains(",")){
            strPrice = strPrice.replace(',', '.');
        }
        if(strPrice.contains("#")){
            strPrice = "0.0";
        }
        Double price = Double.parseDouble(strPrice);
        price *=margin.getUsdCurrencyRate();
        if(price < 100){
            price*=margin.getLessThanHundred();
            price+=margin.getLessThanHundredFixed();
            return price;
        }
        if(price >= 100 && price < 300){
            price*=margin.getFromHundredToTreeHundred();
            price+=margin.getFromHundredToTreeHundredFixed();
            return price;
        }
        if(price >= 300 && price < 500){
            price*=margin.getFromTreeHundredToFiveHundred();
            price+=margin.getFromTreeHundredToFiveHundredFixed();
            return price;
        }
        if(price >= 500 && price < 1000){
            price*=margin.getFromFiveHundredToThousand();
            price+=margin.getFromFiveHundredToThousandFixed();
            return price;
        }
        if(price >= 1000 && price < 5000){
            price*=margin.getFromThousandToFiveThousands();
            price+=margin.getFromThousandToFiveThousandsFixed();
            return price;
        }
        if(price >= 5000 && price < 10000){
            price*=margin.getFromFiveToTenThousands();
            price+=margin.getFromFiveToTenThousandsFixed();
            return price;
        }
        if(price >= 10000 && price < 20000){
            price*=margin.getFromTenToTwentyThousands();
            price+=margin.getFromTenToTwentyThousandsFixed();
            return price;
        }
        if(price >= 20000 && price < 50000){
            price*=margin.getFromTwentyToFifty();
            price+=margin.getFromTwentyToFiftyFixed();
            return price;
        }
        if(price >= 50000 && price < 100000){
            price*=margin.getFromFiftyToHundred();
            price+=margin.getFromFiftyToHundredFixed();
            return price;
        }
        if(price > 100000){
            price*=margin.getAboveHundredThousands();
            price+=margin.getAboveHundredThousandsFixed();
        }
        return price;
    }

    public static Double roundPrice(Double price){
        price = price * 100;
        int i = (int) Math.round(price);
        price = (double)i / 100;
        return price;
    }

}
