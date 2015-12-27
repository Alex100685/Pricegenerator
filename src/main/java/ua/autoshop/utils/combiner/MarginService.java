package ua.autoshop.utils.combiner;

import ua.autoshop.model.*;
import ua.autoshop.utils.combiner.dafaultmargin.*;
import ua.autoshop.utils.savers.impl.*;

/**
 * Created by Пользователь on 09.10.2015.
 */
public class MarginService {

    public static DefaultMarginBase getStrategy(Margin margin){
        DefaultMarginBase baseMargin = null;
        if(margin.getPriceName().equals("Автотехникс")){
            baseMargin = new DefaultMarginAutotechnix(margin);
        }
        if(margin.getPriceName().equals("Автотехникс ОПТ")){
            baseMargin = new DefaultMarginAutotechnixWholesale(margin);
        }
        if(margin.getPriceName().equals("Интеркарс")){
            baseMargin = new DefaultMarginIntercars(margin);
        }
        if(margin.getPriceName().equals("Интеркарс ОПТ")){
            baseMargin = new DefaultMarginIntercarsWholesale(margin);
        }
        if(margin.getPriceName().equals("Влад")){
            baseMargin = new DefaultMarginVlad(margin);
        }
        if(margin.getPriceName().equals("Влад ОПТ")){
            baseMargin = new DefaultMarginVladWholesale(margin);
        }
        if(margin.getPriceName().equals("Элит")){
            baseMargin = new DefaultMarginElit(margin);
        }
        if(margin.getPriceName().equals("Элит ОПТ")){
            baseMargin = new DefaultMarginElitWholesale(margin);
        }
        if(margin.getPriceName().equals("Генстар")){
            baseMargin = new DefaultMarginGenstar(margin);
        }
        if(margin.getPriceName().equals("Генстар ОПТ")){
            baseMargin = new DefaultMarginGenstarWholesale(margin);
        }
        if(margin.getPriceName().equals("Амперис")){
            baseMargin = new DefaultMarginAmperis(margin);
        }
        if(margin.getPriceName().equals("Амперис ОПТ")){
            baseMargin = new DefaultMarginAmperisWholesale(margin);
        }
        if(margin.getPriceName().equals("ТОМАРКЕТ РОЗНИЦА")){
            baseMargin = new DefaultMarginTomarket(margin);
        }
        if(margin.getPriceName().equals("ТОМАРКЕТ ОПТ")){
            baseMargin = new DefaultMarginTomarketRetail(margin);
        }
        if(margin.getPriceName().equals("Элит ОРИГИНАЛ РОЗНИЦА")){
            baseMargin = new DefaultMarginElitOriginal(margin);
        }
        if(margin.getPriceName().equals("Элит ОРИГИНАЛ ОПТ")){
            baseMargin = new DefaultMarginElitOriginalWholesale(margin);
        }
        if(margin.getPriceName().equals("Юник ТРЕЙД РОЗНИЦА")){
            baseMargin = new DefaultMarginUnicTrade(margin);
        }
        if(margin.getPriceName().equals("Юник ТРЕЙД ОПТ")){
            baseMargin = new DefaultMarginUnicTradeWholesale(margin);
        }

        return baseMargin;
    }
}
