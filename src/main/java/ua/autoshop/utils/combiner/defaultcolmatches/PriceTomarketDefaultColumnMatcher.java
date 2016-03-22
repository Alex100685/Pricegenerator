package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class PriceTomarketDefaultColumnMatcher implements ColumnMatchesSetter {
    String className;

    public PriceTomarketDefaultColumnMatcher(String className) {
        this.className = className;

    }

    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setName(className);
        columnMatch.setNameMatch(3);
        columnMatch.setAvailableMatch(11);
        columnMatch.setBrandMatch(4);
        columnMatch.setCodeMatch(5);
        columnMatch.setWholesalePriceMatch(7);
        columnMatch.setRetailPriceMatch(8);
        columnMatch.setIncomePriceMatch(6);
        columnMatch.setCategoryMatch(2);
        columnMatch.setShelfMatch(12);
        columnMatch.setPictureMatch(13);
        return columnMatch;
    }
}
