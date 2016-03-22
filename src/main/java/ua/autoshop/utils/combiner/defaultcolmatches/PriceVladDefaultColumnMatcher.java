package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class PriceVladDefaultColumnMatcher implements ColumnMatchesSetter {
    String className;

    public PriceVladDefaultColumnMatcher(String className) {
        this.className = className;
    }

    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setName(className);
        columnMatch.setBrandMatch(1);
        columnMatch.setNameMatch(7);
        columnMatch.setCodeMatch(3);
        columnMatch.setIncomePriceMatch(4);
        columnMatch.setAvailableMatch(5);

        return columnMatch;
    }
}
