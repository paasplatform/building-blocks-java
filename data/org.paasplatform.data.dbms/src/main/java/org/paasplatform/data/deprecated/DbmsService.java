package org.paasplatform.data.deprecated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DbmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public DbmsMeta fetchDbmsMeta(DatabaseMetaData databaseMetaData) {
        DbmsMeta.Builder builder = new DbmsMeta.Builder();

        onlyLogException(() -> builder.productName(databaseMetaData.getDatabaseProductName()));
        onlyLogException(() -> builder.productVersion(databaseMetaData.getDatabaseProductVersion()));
        builder.sqlKeywords(
                new Combined(
                        new Sql92Keywords(),
                        new MetadataKeywords(databaseMetaData)
                )
        );
        onlyLogException(() -> builder.systemFunctions(new UniformSet(databaseMetaData.getSystemFunctions().split(",")).value()));
        onlyLogException(() -> builder.stringFunctions(new UniformSet(databaseMetaData.getStringFunctions().split(",")).value()));
        onlyLogException(() -> builder.numericFunctions(new UniformSet(databaseMetaData.getNumericFunctions().split(",")).value()));
        onlyLogException(() -> builder.timeDateFunctions(new UniformSet(databaseMetaData.getTimeDateFunctions().split(",")).value()));
        onlyLogException(() -> builder.identifierQuoteString(databaseMetaData.getIdentifierQuoteString().trim()));

        return builder.getDbmsMeta();
    }

    @FunctionalInterface
    private interface MetaDataFetcher {
        void fetch() throws SQLException;
    }

    private static void onlyLogException(MetaDataFetcher fetcher) {
        try {
            fetcher.fetch();
        } catch (SQLException sqle) {
            LOGGER.warn("Failed to fetch metadata", sqle);
        }
    }
}