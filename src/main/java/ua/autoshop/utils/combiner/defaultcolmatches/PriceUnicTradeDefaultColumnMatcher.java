package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class PriceUnicTradeDefaultColumnMatcher implements ColumnMatchesSetter {
    String className;

    public PriceUnicTradeDefaultColumnMatcher(String className) {
        this.className = className;

    }

    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setName(className);
        columnMatch.setCodeMatch(0);
        columnMatch.setNameMatch(1);
        columnMatch.setBrandMatch(2);
        columnMatch.setAvailableMatch(6);
        columnMatch.setIncomePriceMatch(8);
        columnMatch.setCurrencyMatch(9);

        return columnMatch;
    }
}
