package org.paasplatform.data.meta;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Column {
    private String columnName;
    /**
     * SQL type from java.sql.Types
     */
    private String dataType;
    /**
     * Data source dependent type name, for a UDT the type name is fully qualified
     */
    private String typeName;
    private Integer columnSize;
    /**
     * the number of fractional digits. Null is returned for data types where
     * DECIMAL_DIGITS is not applicable
     */
    private Integer decimalDigits;
    private Integer numPrecRadix;
    /**
     * is NULL allowed.
     * columnNoNulls - might not allow NULL values
     * columnNullable - definitely allows NULL values
     * columnNullableUnknown - nullability unknown
     */
    private Integer nullable;
    private String remarks;
    /**
     * COLUMN_DEF String => default value for the column, which should be
     * interpreted as a string when the value is enclosed in single quotes (may be
     * null)
     */
    private String columnDef;
    /**
     * CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in the
     * column
     */
    private String charOctetLength;
    /**
     * ORDINAL_POSITION int => index of column in table (starting at 1)
     */
    private Integer ordinalPosition;
    /**
     * IS_NULLABLE String => ISO rules are used to determine the nullability for a
     * column.
     * YES --- if the column can include NULLs
     * NO --- if the column cannot include NULLs
     * empty string --- if the nullability for the column is unknown
     */
    private String isNullable;

    /**
     * IS_AUTOINCREMENT String => Indicates whether this column is auto incremented
     * YES --- if the column is auto incremented
     * NO --- if the column is not auto incremented
     * empty string --- if it cannot be determined whether the column is auto
     * incremented
     */
    private String isAutoIncrement;
    /**
     * IS_GENERATEDCOLUMN String => Indicates whether this is a generated column
     * YES --- if this a generated column
     * NO --- if this not a generated column
     * empty string --- if it cannot be determined whether this is a generated
     * column
     */
    private String isGeneratedColumn;

    private String table;
    private String schema;
    private String catalog;
}
