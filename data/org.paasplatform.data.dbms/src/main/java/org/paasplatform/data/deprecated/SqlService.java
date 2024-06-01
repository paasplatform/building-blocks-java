package org.paasplatform.data.deprecated;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Optional.ofNullable;


public class SqlService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final DbmsService dbmsService = new DbmsService();

    private Connection connection;
    private DatabaseMetaData databaseMetaData;
    private DbmsMeta dbmsMeta;
    private Pattern invalidIdentifierPattern;

    public DatabaseMetaData connect(SqlConnection sqlConnection) throws SQLException, IOException, ClassNotFoundException {
        this.connection = sqlConnection.connection();
        databaseMetaData = this.connection.getMetaData();
        dbmsMeta = dbmsService.fetchDbmsMeta(databaseMetaData);
        invalidIdentifierPattern = new InvalidIdentifierPattern(databaseMetaData).pattern();
        return databaseMetaData;
    }

    public Connection getConnection() {
        return connection;
    }

    public DatabaseMetaData getDatabaseMetaData() {
        return databaseMetaData;
    }

    public DbmsMeta getDbmsMeta() {
        return dbmsMeta;
    }

    /**
     * Create a <code>PreparedStatement</code> from the specified SQL.
     * The SQL can contain these named parameters (but <b>not</b> question marks).
     * <ol>
     * <li>:schema - replaced with the name of the schema
     * <li>:owner - alias for :schema
     * <li>:table - replaced with the name of the table
     * </ol>
     *
     * @param sql       String - SQL without question marks
     * @param tableName String - <code>null</code> if the statement doesn't deal with <code>Table</code>-level details.
     * @return PreparedStatement
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(String sql, Database db, String tableName) throws SQLException {
        StringBuilder sqlBuf = new StringBuilder(sql);
        List<String> sqlParams = getSqlParams(sqlBuf, db.getName(), db.getCatalog().getName(), db.getSchema().getName(), tableName); // modifies sqlBuf
        LOGGER.debug("{} {}", sqlBuf, sqlParams);

        PreparedStatement stmt = connection.prepareStatement(sqlBuf.toString());
        try {
            for (int i = 0; i < sqlParams.size(); ++i) {
                stmt.setString(i + 1, sqlParams.get(i));
            }
        } catch (SQLException exc) {
            stmt.close();
            throw exc;
        }

        return stmt;
    }

    /**
     * Replaces named parameters in <code>sql</code> with question marks and
     * returns appropriate matching values in the returned <code>List</code> of <code>String</code>s.
     *
     * @param sql       StringBuffer input SQL with named parameters, output named params are replaced with ?'s.
     * @param tableName String
     * @return List of Strings
     * @see #prepareStatement(String, Database, String)
     */
    private static List<String> getSqlParams(StringBuilder sql, String dbName, String catalog, String schema, String tableName) {
        Map<String, String> namedParams = new HashMap<>();
        if (Objects.isNull(schema)) {
            schema = dbName; // some 'schema-less' db's treat the db name like a schema (unusual case)
        }

        namedParams.put(":dbname", dbName);
        namedParams.put(":schema", schema);
        namedParams.put(":owner", schema); // alias for :schema
        if (Objects.nonNull(tableName)) {
            namedParams.put(":table", tableName);
            namedParams.put(":view", tableName); // alias for :table
        }
        if (Objects.nonNull(catalog)) {
            namedParams.put(":catalog", catalog);
        }

        List<String> sqlParams = new ArrayList<>();
        int nextColon = sql.indexOf(":");
        while (nextColon != -1) {
            String paramName = new StringTokenizer(sql.substring(nextColon), " ,\"')").nextToken();
            String paramValue = namedParams.get(paramName);
            if (Objects.isNull(paramValue)) {
                throw new InvalidConfigurationException(
                        "Unexpected named parameter '" + paramName + "' found in SQL '" + sql + "'");
            }
            sqlParams.add(paramValue);
            sql.replace(nextColon, nextColon + paramName.length(), "?"); // replace with a ?
            nextColon = sql.indexOf(":", nextColon);
        }

        return sqlParams;
    }

    public PreparedStatement prepareStatement(String sqlQuery) throws SQLException {
        return connection.prepareStatement(sqlQuery);
    }

    public String getQualifiedTableName(String catalog, String schema, String tableName, boolean forceQuotes) {
        final String maybe = ofNullable(schema).orElse(catalog);

        final Name schemaOrCatalog = Objects.isNull(maybe)
                ? new EmptyName()
                : getSchemaOrCatalog(new NameFromString(maybe), forceQuotes);

        final Name table = forceQuotes ? quoteIdentifier(tableName)
                : new Sanitized(
                this.invalidIdentifierPattern,
                this.dbmsMeta,
                new NameFromString(tableName)
        );

        return new Qualified(table, schemaOrCatalog).value();
    }

    private Name getSchemaOrCatalog(
            final Name schemaOrCatalog,
            final boolean forceQuotes
    ) {
        return forceQuotes ?
                new DatabaseQuoted(
                        this.dbmsMeta,
                        schemaOrCatalog
                ) :
                new Sanitized(
                        this.invalidIdentifierPattern,
                        this.dbmsMeta,
                        schemaOrCatalog
                );
    }

    public Name quoteIdentifier(String id) {
        return new DatabaseQuoted(
                dbmsMeta,
                new NameFromString(id)
        );
    }
}
