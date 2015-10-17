package ua.autoshop.utils.sorter;

import ua.autoshop.model.PriceAutoshop;

import java.util.Comparator;

/**
 * Created by Пользователь on 15.10.2015.
 */
public class SortByPrice implements Comparator<PriceAutoshop> {

    @Override
    public int compare(PriceAutoshop o1, PriceAutoshop o2) {
        Double s1 = o1.getRetailPrice();
        Double s2 = o2.getRetailPrice();

        if(s1>s2){
            return 1;
        }
        if(s1<s2){
            return -1;
        }
        else{
            return 0;
        }
    }

}
