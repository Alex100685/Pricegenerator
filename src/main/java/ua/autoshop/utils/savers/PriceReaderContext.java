package ua.autoshop.utils.savers;

import org.springframework.stereotype.Component;
import ua.autoshop.model.PriceAutotechnix;
import ua.autoshop.model.PriceGerasimenko;
import ua.autoshop.model.PriceIntercarsi;
import ua.autoshop.model.PriceVlad;
import ua.autoshop.utils.savers.impl.*;
import ua.autoshop.utils.savers.impl.csv.PriceAutotechnixReader;
import ua.autoshop.utils.savers.impl.csv.PriceVladReader;
import ua.autoshop.utils.savers.impl.excel.PriceElitReader;
import ua.autoshop.utils.savers.impl.excel.PriseIntercarsReader;

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
        return baseReader;
    }

}
