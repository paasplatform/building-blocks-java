package org.paasplatform.data.meta;

import java.util.HashSet;
import java.util.Set;

public class Combined implements Keywords {

    private final Keywords first;
    private final Keywords second;

    public Combined(final Keywords first, final Keywords second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Set<String> value() {
        Set<String> allSqlKeywords = new HashSet<>(this.first.value());
        Set<String> sqlKeywords = this.second.value();
        allSqlKeywords.addAll(sqlKeywords);
        return allSqlKeywords;
    }
}
