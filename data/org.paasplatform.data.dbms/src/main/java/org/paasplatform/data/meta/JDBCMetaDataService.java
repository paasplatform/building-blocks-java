package org.paasplatform.data.meta;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class JDBCMetaDataService implements IMetaDataService {
    /**
     * https://docs.oracle.com/javase/8/docs/api/java/sql/DatabaseMetaData.html#getSchemas--
     * @param connection
     * @param catalog
     * @param schemaPattern
     * @return
     * @throws SQLException
     */
    public List<Schema> getSchemas(Connection connection, String catalog, String schemaPattern) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getSchemas(catalog, schemaPattern);
        List<Schema> schemas = new ArrayList<>();
        while (resultSet.next()) {
            Schema schema = new Schema();
            schema.setName(resultSet.getString("TABLE_SCHEM"));
            schema.setCatalog(resultSet.getString("TABLE_CATALOG"));
            schemas.add(schema);
        }

        return schemas;
    }

    /**
     * https://docs.oracle.com/javase/8/docs/api/java/sql/DatabaseMetaData.html#getTables-java.lang.String-java.lang.String-java.lang.String-java.lang.String:A-
     *
     * @param connection
     * @param catalog          a catalog name; must match the catalog name as it is
     *                         stored in the database; "" retrieves those without a
     *                         catalog; null means that the catalog name should not
     *                         be used to narrow the search
     * @param schemaPattern    a schema name pattern; must match the schema name as
     *                         it is stored in the database; "" retrieves those
     *                         without a schema; null means that the schema name
     *                         should not be used to narrow the search
     * @param tableNamePattern a table name pattern; must match the table name as it
     *                         is stored in the database
     * @param types            a list of table types, which must be from the list of
     *                         table types returned from getTableTypes(),to include;
     *                         null returns all types
     * @return
     * @throws SQLException
     */
    public List<Table> getTables(Connection connection, String catalog, String schemaPattern, String tableNamePattern,
            String[] types) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
        List<Table> tables = new ArrayList<>();
        while (resultSet.next()) {
            Table table = new Table();
            table.setName(resultSet.getString("TABLE_NAME"));
            table.setSchema(resultSet.getString("TABLE_SCHEM"));
            table.setCatalog(resultSet.getString("TABLE_CAT"));
            table.setType(resultSet.getString("TABLE_TYPE"));
            table.setRemarks(resultSet.getString("REMARKS"));
            tables.add(table);
        }

        return tables;
    }

    public List<Column> getColumns(Connection connection, String catalog, String schemaPattern, String tableNamePattern,
            String columnNamePattern)
            throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
        List<Column> columns = new ArrayList<>();
        while (resultSet.next()) {
            Column column = new Column();

            column.setTable(resultSet.getString("TABLE_NAME"));
            column.setSchema(resultSet.getString("TABLE_SCHEM"));
            column.setCatalog(resultSet.getString("TABLE_CAT"));
            column.setDataType(resultSet.getString("DATA_TYPE"));
            column.setTypeName(resultSet.getString("TYPE_NAME"));
            column.setColumnSize(resultSet.getInt("COLUMN_SIZE"));
            column.setDecimalDigits(resultSet.getInt("DECIMAL_DIGITS"));
            column.setNumPrecRadix(resultSet.getInt("NUM_PREC_RADIX"));
            column.setNullable(resultSet.getInt("NULLABLE"));
            column.setRemarks(resultSet.getString("REMARKS"));
            column.setColumnDef(resultSet.getString("COLUMN_DEF"));
            column.setCharOctetLength(resultSet.getString("CHAR_OCTET_LENGTH"));
            column.setOrdinalPosition(resultSet.getInt("ORDINAL_POSITION"));
            column.setIsNullable(resultSet.getString("IS_NULLABLE"));
            column.setIsAutoIncrement(resultSet.getString("IS_AUTOINCREMENT"));
            column.setIsGeneratedColumn(resultSet.getString("IS_GENERATEDCOLUMN"));

            columns.add(column);
        }

        return columns;

    }

    public ResultSet getPrimaryKeys(Connection connection, String catalog, String schema, String table)
            throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getPrimaryKeys(catalog, schema, table);

        return resultSet;
    }

    public ResultSet getImportedKeys(Connection connection, String catalog, String schema, String table)
            throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getImportedKeys(catalog, schema, table);

        return resultSet;
    }

    public ResultSet getExportedKeys(Connection connection, String catalog, String schema, String table)
            throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getExportedKeys(catalog, schema, table);

        return resultSet;
    }

    public List<IndexInfo> getIndexInfo(Connection conn, String catalog, String schema, String table, Boolean unique,
            Boolean approximate)
            throws SQLException {
        List<IndexInfo> indexes = new ArrayList<>();
        DatabaseMetaData dm = conn.getMetaData();
        ResultSet resultSet = dm.getIndexInfo(catalog, schema, table, unique, approximate);
        while (resultSet.next()) {
            IndexInfo indexInfo = new IndexInfo();
            indexInfo.setTable(resultSet.getString("TABLE_NAME"));
            indexInfo.setSchema(resultSet.getString("TABLE_SCHEM"));
            indexInfo.setCatalog(resultSet.getString("TABLE_CAT"));

            indexInfo.setNonUnique(resultSet.getBoolean("NON_UNIQUE"));
            indexInfo.setIndexQualifier(resultSet.getString("INDEX_QUALIFIER"));
            indexInfo.setIndexName(resultSet.getString("INDEX_NAME"));
            indexInfo.setType(resultSet.getShort("TYPE"));
            indexInfo.setOrdinalPosition(resultSet.getShort("ORDINAL_POSITION"));
            indexInfo.setColumnName(resultSet.getString("COLUMN_NAME"));
            indexInfo.setAscOrDesc(resultSet.getString("ASC_OR_DESC"));
            indexInfo.setCardinality(resultSet.getString("CARDINALITY"));
            indexInfo.setPages(resultSet.getLong("PAGES"));
            indexInfo.setFilterCondition(resultSet.getString("FILTER_CONDITION"));

            indexes.add(indexInfo);
        }

        return indexes;
    }

    public void getDatabaseInformation(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println("数据库已知的用户: " + metaData.getUserName());
        System.out.println("数据库的系统函数的逗号分隔列表: " + metaData.getSystemFunctions());
        System.out.println("数据库的时间和日期函数的逗号分隔列表: " + metaData.getTimeDateFunctions());
        System.out.println("数据库的字符串函数的逗号分隔列表: " + metaData.getStringFunctions());
        System.out.println("数据库供应商用于 'schema' 的首选术语: " + metaData.getSchemaTerm());
        System.out.println("数据库URL: " + metaData.getURL());
        System.out.println("是否允许只读:" + metaData.isReadOnly());
        System.out.println("数据库的产品名称:" + metaData.getDatabaseProductName());
        System.out.println("数据库的版本:" + metaData.getDatabaseProductVersion());
        System.out.println("驱动程序的名称:" + metaData.getDriverName());
        System.out.println("驱动程序的版本:" + metaData.getDriverVersion());
        System.out.println("数据库中使用的表类型");
        ResultSet rs = metaData.getTableTypes();
        while (rs.next()) {
            System.out.println(rs.getString("TABLE_TYPE"));
        }
    }
}
