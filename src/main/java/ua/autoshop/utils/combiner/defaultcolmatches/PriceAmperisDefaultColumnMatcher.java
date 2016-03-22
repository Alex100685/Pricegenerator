package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class PriceAmperisDefaultColumnMatcher implements ColumnMatchesSetter {

    String className;

    public PriceAmperisDefaultColumnMatcher(String className) {
        this.className = className;
    }

    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setCodeMatch(1);
        columnMatch.setName(className);
        columnMatch.setNameMatch(2);
        columnMatch.setCategoryMatch(3);
        columnMatch.setIncomePriceMatch(4);
        columnMatch.setAvailableMatch(5);
        return columnMatch;
    }
}
