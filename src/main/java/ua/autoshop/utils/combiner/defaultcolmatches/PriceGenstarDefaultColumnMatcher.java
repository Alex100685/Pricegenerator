package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class PriceGenstarDefaultColumnMatcher implements ColumnMatchesSetter {
    String className;

    public PriceGenstarDefaultColumnMatcher(String className) {
        this.className = className;
    }

    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setName(className);
        columnMatch.setNameMatch(0);
        columnMatch.setBrandMatch(1);
        columnMatch.setCodeMatch(2);
        columnMatch.setIncomePriceMatch(5);
        columnMatch.setAvailableMatch(6);

        return columnMatch;
    }
}
