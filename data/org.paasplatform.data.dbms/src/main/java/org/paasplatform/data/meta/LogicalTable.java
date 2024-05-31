package org.paasplatform.data.meta;

/**
 * This is a logical (versus physical) table that represents something
 * that doesn't really exist in the current database.
 */
public class LogicalTable extends Table {
    public LogicalTable(Database db, String catalog, String schema, String name, String comments) {
        super(db, catalog, schema, name, comments);
    }

    /**
     * Don't attempt to query our metadata from the database.
     *
     * @return true
     */
    @Override
    public boolean isLogical() {
        return true;
    }
}
