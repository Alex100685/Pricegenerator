package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class PriceElitDefaultColumnMatcher implements ColumnMatchesSetter {
    String className;

    public PriceElitDefaultColumnMatcher(String className) {
        this.className = className;

    }

    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setName(className);
        columnMatch.setCodeMatch(1);
        columnMatch.setBrandMatch(2);
        columnMatch.setNameMatch(3);
        columnMatch.setIncomePriceMatch(5);
        columnMatch.setAvailableMatch(6);
        return columnMatch;
    }
}
