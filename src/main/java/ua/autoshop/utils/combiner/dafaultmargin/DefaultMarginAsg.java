package ua.autoshop.utils.combiner.dafaultmargin;

import ua.autoshop.model.Margin;

/**
 * Created by Пользователь on 26.03.2016.
 */
public class DefaultMarginAsg extends DefaultMarginBase {
    public DefaultMarginAsg(Margin margin) {
        super(margin);
    }

    @Override
    public void setDefaultMargins() {
        margin.setLessThanHundred(1.1);
        margin.setLessThanHundredFixed(35.0);
        margin.setFromHundredToTreeHundred(1.1);
        margin.setFromHundredToTreeHundredFixed(35.0);
        margin.setFromTreeHundredToFiveHundred(1.07);
        margin.setFromTreeHundredToFiveHundredFixed(35.0);
        margin.setFromFiveHundredToThousand(1.07);
        margin.setFromFiveHundredToThousandFixed(0.0);
        margin.setFromThousandToFiveThousands(1.05);
        margin.setFromThousandToFiveThousandsFixed(0.0);
        margin.setFromFiveToTenThousands(1.05);
        margin.setFromFiveToTenThousandsFixed(0.0);
        margin.setFromTenToTwentyThousands(1.03);
        margin.setFromTenToTwentyThousandsFixed(0.0);
        margin.setFromTwentyToFifty(1.03);
        margin.setFromTwentyToFiftyFixed(0.0);
        margin.setFromFiftyToHundred(1.03);
        margin.setFromFiftyToHundredFixed(0.0);
        margin.setAboveHundredThousands(1.03);
        margin.setAboveHundredThousandsFixed(0.0);
        margin.setUsdCurrencyRate(1.0);
    }

}
