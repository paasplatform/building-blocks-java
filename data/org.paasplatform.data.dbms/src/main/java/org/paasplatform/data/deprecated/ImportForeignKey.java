package org.paasplatform.data.deprecated;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Rafal Kasa
 * @author Nils Petzaell
 */
public class ImportForeignKey {
    private final String fkName;
    private final String fkColumnName;
    private final String pkTableCat;
    private final String pkTableSchema;
    private final String pkTableName;
    private final String pkColumnName;
    private final Integer updateRule;
    private final Integer deleteRule;

    private ImportForeignKey(String fkName, String fkColumnName, String pkTableCat, String pkTableSchema, String pkTableName, String pkColumnName, Integer updateRule, Integer deleteRule) { //NOSONAR not publicly available
        this.fkName = fkName;
        this.fkColumnName = fkColumnName;
        this.pkTableCat = pkTableCat;
        this.pkTableSchema = pkTableSchema;
        this.pkTableName = pkTableName;
        this.pkColumnName = pkColumnName;
        this.updateRule = updateRule;
        this.deleteRule = deleteRule;
    }

    public String getFkName() {
        return fkName;
    }

    public String getFkColumnName() {
        return fkColumnName;
    }

    public String getPkTableCat() {
        return pkTableCat;
    }

    public String getPkTableSchema() {
        return pkTableSchema;
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public Integer getUpdateRule() {
        return updateRule;
    }

    public Integer getDeleteRule() {
        return deleteRule;
    }

    public static class Builder {
        private String fkName;
        private String fkColumnName;
        private String pkTableCat;
        private String pkTableSchema;
        private String pkTableName;
        private String pkColumnName;
        private Integer updateRule;
        private Integer deleteRule;

        public Builder fromImportKeysResultSet(ResultSet resultSet) throws SQLException {
            this.withFkName(resultSet.getString("FK_NAME"))
                    .withFkColumnName(resultSet.getString("FKCOLUMN_NAME"))
                    .withPkTableCat(resultSet.getString("PKTABLE_CAT"))
                    .withPkTableSchema(resultSet.getString("PKTABLE_SCHEM"))
                    .withPkTableName(resultSet.getString("PKTABLE_NAME"))
                    .withPkColumnName(resultSet.getString("PKCOLUMN_NAME"))
                    .withUpdateRule(resultSet.getInt("UPDATE_RULE"))
                    .withDeleteRule(resultSet.getInt("DELETE_RULE"));
            return this;
        }

        public Builder withFkName(String fkName) {
            this.fkName = fkName;
            return this;
        }

        public Builder withFkColumnName(String fkColumnName) {
            this.fkColumnName = fkColumnName;
            return this;
        }

        public Builder withPkTableCat(String pkTableCat) {
            this.pkTableCat = pkTableCat;
            return this;
        }

        public Builder withPkTableSchema(String pkTableSchema) {
            this.pkTableSchema = pkTableSchema;
            return this;
        }

        public Builder withPkTableName(String pkTableName) {
            this.pkTableName = pkTableName;
            return this;
        }

        public Builder withPkColumnName(String pkColumnName) {
            this.pkColumnName = pkColumnName;
            return this;
        }

        public Builder withUpdateRule(Integer updateRule) {
            this.updateRule = updateRule;
            return this;
        }

        public Builder withDeleteRule(Integer deleteRule) {
            this.deleteRule = deleteRule;
            return this;
        }

        public ImportForeignKey build() {
            return new ImportForeignKey(fkName,fkColumnName, pkTableCat, pkTableSchema, pkTableName, pkColumnName, updateRule, deleteRule);
        }
    }
}
