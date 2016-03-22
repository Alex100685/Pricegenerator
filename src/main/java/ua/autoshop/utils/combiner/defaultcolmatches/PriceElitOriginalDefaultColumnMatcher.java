package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class PriceElitOriginalDefaultColumnMatcher implements ColumnMatchesSetter {
    String className;

    public PriceElitOriginalDefaultColumnMatcher(String className) {
        this.className = className;

    }

    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setName(className);
        columnMatch.setBrandMatch(2);
        columnMatch.setCodeMatch(1);
        columnMatch.setNameMatch(3);
        columnMatch.setIncomePriceMatch(5);
        columnMatch.setAvailableMatch(8);
        columnMatch.setSupplyCondition(4);

        return columnMatch;
    }
}
