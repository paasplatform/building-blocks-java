package org.paasplatform.data.deprecated;


/**
 * A remote table (exists in another schema (logically or physically))
 * that was created via XML metadata.
 */
public class LogicalRemoteTable extends RemoteTable {

    public LogicalRemoteTable(
            Database database,
            RemoteTableIdentifier remoteTableIdentifier,
            String baseSchema) {
        super(database,
                remoteTableIdentifier,
                baseSchema);
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