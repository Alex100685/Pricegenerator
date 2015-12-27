package ua.autoshop.utils.savers;

import org.springframework.stereotype.Component;
import ua.autoshop.model.*;
import ua.autoshop.utils.savers.impl.*;
import ua.autoshop.utils.savers.impl.csv.PriceAutotechnixReader;
import ua.autoshop.utils.savers.impl.csv.PriceVladReader;
import ua.autoshop.utils.savers.impl.excel.*;

/**
 * Created by Пользователь on 09.10.2015.
 */

@Component
public class PriceReaderContext {

    public static BaseReader getStrategy(String index){
        BaseReader baseReader = null;
        if(index.equals("i")){
            baseReader = new PriseIntercarsReader(PriceIntercarsi.class);
        }
        if(index.equals("a")){
            baseReader = new PriceAutotechnixReader(PriceAutotechnix.class);
        }
        if(index.equals("v")){
            baseReader = new PriceVladReader(PriceVlad.class);
        }
        if(index.equals("e")){
            baseReader = new PriceElitReader(PriceGerasimenko.class);
        }
        if(index.equals("g")){
            baseReader = new PriceGenstarReader(PriceGenstar.class);
        }
        if(index.equals("amp")){
            baseReader = new PriceAmperisReader(PriceAmperis.class);
        }
        if(index.equals("to")){
            baseReader = new PriceTomarketReader(PriceTomarket.class);
        }
        if(index.equals("unic")){
            baseReader = new PriceUnicTradeReader(PriceUnicTrade.class);
        }
        if(index.equals("eo")){
            baseReader = new PriceElitOriginalReader(PriceElitOriginal.class);
        }

        return baseReader;
    }

}
