package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class PriseIntercarsDefaultColumnMatcher implements ColumnMatchesSetter {
    String className;
    public PriseIntercarsDefaultColumnMatcher(String className) {
        this.className=className;
    }


    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setName(className);
        columnMatch.setCodeMatch(1);
        columnMatch.setBrandMatch(2);
        columnMatch.setNameMatch(3);
        columnMatch.setRetailPriceMatch(4);
        columnMatch.setWholesalePriceMatch(5);
        columnMatch.setAvailableMatch(6);
        return columnMatch;
    }
}
