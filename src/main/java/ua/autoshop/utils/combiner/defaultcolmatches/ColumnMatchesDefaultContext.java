package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.*;
import ua.autoshop.utils.savers.impl.csv.PriceAutotechnixReader;
import ua.autoshop.utils.savers.impl.csv.PriceVladReader;
import ua.autoshop.utils.savers.impl.excel.*;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class ColumnMatchesDefaultContext {

    public static ColumnMatchesSetter getStrategy(String className) {
        ColumnMatchesSetter matchesSetter = null;
        if(className.equals("PriceIntercarsi")){
            return new PriseIntercarsDefaultColumnMatcher(className);
        }
        if(className.equals("PriceAutotechnix")){
            return new PriceAutotechnixDefaultColumnMatcher(className);
        }
        if(className.equals("PriceVlad")){
            return new PriceVladDefaultColumnMatcher(className);
        }
        if(className.equals("PriceGerasimenko")){
            return new PriceElitDefaultColumnMatcher(className);
        }
        if(className.equals("PriceGerasimenko")){
            return new PriceGenstarDefaultColumnMatcher(className);
        }
        if(className.equals("PriceAmperis")){
            return new PriceAmperisDefaultColumnMatcher(className);
        }
        if(className.equals("PriceTomarket")){
            return new PriceTomarketDefaultColumnMatcher(className);
        }
        if(className.equals("PriceUnicTrade")){
            return new PriceUnicTradeDefaultColumnMatcher(className);
        }
        if(className.equals("PriceElitOriginal")){
            matchesSetter = new PriceElitOriginalDefaultColumnMatcher(className);
        }
        if(className.equals("AsgModel")){
            matchesSetter = new PriceAsgDefaultColumnMatcher(className);
        }
        return matchesSetter;
    }
}
