package org.paasplatform.data.meta;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nils Petzaell
 */
public class DbmsMeta {

    private String productName;
    private String productVersion;
    private String identifierQuoteString;
    private Set<String> sqlKeywords;
    private Set<String> systemFunctions;
    private Set<String> numericFunctions;
    private Set<String> stringFunctions;
    private Set<String> timeDateFunctions;

    private DbmsMeta() {}

    public String getProductName() {
        return productName;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public String getIdentifierQuoteString() {
        return identifierQuoteString;
    }

    public Set<String> getSQLKeywords() {
        return sqlKeywords;
    }

    public Set<String> getSystemFunctions() {
        return systemFunctions;
    }

    public Set<String> getNumericFunctions() {
        return numericFunctions;
    }

    public Set<String> getStringFunctions() {
        return stringFunctions;
    }

    public Set<String> getTimeDateFunctions() {
        return timeDateFunctions;
    }

    /**
     * Lists all identifiers used by the DBMS.
     * @return The set of all keywords and built-in functions.
     */
    public Set<String> reservedWords() {
        Set<String> keywords = new HashSet<>();
        keywords.addAll(getSQLKeywords());
        keywords.addAll(getNumericFunctions());
        keywords.addAll(getStringFunctions());
        keywords.addAll(getSystemFunctions());
        keywords.addAll(getTimeDateFunctions());
        return keywords;
    }

    public static class Builder {
        private DbmsMeta dbmsMeta = new DbmsMeta();

        public Builder productName(String productName) {
            dbmsMeta.productName = productName;
            return this;
        }

        public Builder productVersion(String productVersion) {
            dbmsMeta.productVersion = productVersion;
            return this;
        }

        public Builder identifierQuoteString(String identifierQuoteString) {
            dbmsMeta.identifierQuoteString = identifierQuoteString;
            return this;
        }

        public Builder sqlKeywords(final Keywords sqlKeywords) {
            dbmsMeta.sqlKeywords = sqlKeywords.value();
            return this;
        }

        public Builder systemFunctions(Set<String> systemFunctions) {
            dbmsMeta.systemFunctions = systemFunctions;
            return this;
        }

        public Builder numericFunctions(Set<String> numericFunctions) {
            dbmsMeta.numericFunctions = numericFunctions;
            return this;
        }

        public Builder stringFunctions(Set<String> stringFunctions) {
            dbmsMeta.stringFunctions = stringFunctions;
            return this;
        }

        public Builder timeDateFunctions(Set<String> timeDateFunctions) {
            dbmsMeta.timeDateFunctions = timeDateFunctions;
            return this;
        }

        public DbmsMeta getDbmsMeta() {
            return dbmsMeta;
        }
    }
}
