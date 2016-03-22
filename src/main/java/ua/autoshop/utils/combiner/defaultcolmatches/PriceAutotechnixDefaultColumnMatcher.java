package ua.autoshop.utils.combiner.defaultcolmatches;

import ua.autoshop.model.ColumnMatches;

/**
 * Created by Пользователь on 31.01.2016.
 */
public class PriceAutotechnixDefaultColumnMatcher implements ColumnMatchesSetter {

    String className;

    public PriceAutotechnixDefaultColumnMatcher(String className) {
        this.className = className;
    }

    @Override
    public ColumnMatches getDefaultColumnMatches(String className) {
        ColumnMatches columnMatch = new ColumnMatches();
        columnMatch.setName(className);
        columnMatch.setBrandMatch(0);
        columnMatch.setCodeMatch(1);
        columnMatch.setNameMatch(2);
        columnMatch.setIncomePriceMatch(3);
        columnMatch.setAvailableMatch(5);
        return columnMatch;
    }
}
