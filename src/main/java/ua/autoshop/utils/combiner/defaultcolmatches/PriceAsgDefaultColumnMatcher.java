package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 26.03.2016.
 */
public class PriceAsgDefaultColumnMatcher implements ColumnMatchesSetter {
    String className;

    public PriceAsgDefaultColumnMatcher(String className) {
        this.className = className;
    }

    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setName(className);
        columnMatch.setBrandMatch(1);
        columnMatch.setNameMatch(3);
        columnMatch.setCodeMatch(0);
        columnMatch.setIncomePriceMatch(5);
        columnMatch.setAvailableMatch(4);

        return columnMatch;
    }
}
