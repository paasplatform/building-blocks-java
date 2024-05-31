package org.paasplatform.data.meta;

import java.sql.SQLException;

/**
 * @author Nils Petzaell
 */
public class ColumnInitializationFailure extends SQLException {
    private static final long serialVersionUID = 1L;

    public ColumnInitializationFailure(Table table, SQLException failure) {
        super("Failed to collect column details for " + (table.isView() ? "view" : "table") + " '" + table.getName() + "' in schema '" + table.getContainer() + "'");
        initCause(failure);
    }
}