package org.paasplatform.data.meta;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class IndexInfo {
    /**
     * index name; null when TYPE is tableIndexStatistic
     */
    private String indexName;
    /**
     * Can index values be non-unique. false when TYPE is tableIndexStatistic
     */
    private Boolean nonUnique;
    /**
     * index catalog (maybe null); null when TYPE is tableIndexStatistic
     */
    private String indexQualifier;
    /**
     * short => index type:
     * tableIndexStatistic - this identifies table statistics that are returned in conjunction with a table's index descriptions
     * tableIndexClustered - this is a clustered index
     * tableIndexHashed - this is a hashed index
     * tableIndexOther - this is some other style of index
     */
    private Short type;

    /**
     * column sequence number within index; zero when TYPE is tableIndexStatistic
     */
    private Short ordinalPosition;
    /**
     * null when TYPE is tableIndexStatistic
     */
    private  String columnName;
    /**
     * column sort sequence, "A" => ascending, "D" => descending, may be null if sort sequence is not supported; null when TYPE is tableIndexStatistic
     */
    private String ascOrDesc;
    /**
     * When TYPE is tableIndexStatistic, then this is the number of rows in the table; otherwise, it is the number of unique values in the index.
     */
    private String cardinality;
    /**
     * When TYPE is tableIndexStatistic then this is the number of pages used for the table, otherwise it is the number of pages used for the current index.
     */
    private long pages;
    /**
     * Filter condition, if any. (maybe null)
     */
    private String filterCondition;


    private String table;
    private String schema;
    private String catalog;
}
