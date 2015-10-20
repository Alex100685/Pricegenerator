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
        if(margin.getPriceName().equals("Интеркарс")){
            baseMargin = new DefaultMarginIntercars(margin);
        }
        if(margin.getPriceName().equals("Влад")){
            baseMargin = new DefaultMarginVlad(margin);
        }
        if(margin.getPriceName().equals("Элит")){
            baseMargin = new DefaultMarginElit(margin);
        }
        if(margin.getPriceName().equals("Генстар")){
            baseMargin = new DefaultMarginGenstar(margin);
        }
        if(margin.getPriceName().equals("Амперис")){
            baseMargin = new DefaultMarginAmperis(margin);
        }
        return baseMargin;
    }
}
