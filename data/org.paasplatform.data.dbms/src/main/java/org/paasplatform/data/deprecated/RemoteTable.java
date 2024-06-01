package org.paasplatform.data.deprecated;

public class RemoteTable extends Table {

    private final String baseContainer;

    /**
     * @param db
     * @param catalog
     * @param schema
     * @param name
     * @param baseContainer
     */
    public RemoteTable(Database db, String catalog, String schema, String name, String baseContainer) {
        super(db, catalog, schema, name, null);
        this.baseContainer = baseContainer;
    }

    public RemoteTable(Database db, RemoteTableIdentifier remoteTableIdentifier, String baseContainer) {
        super(
                db,
                remoteTableIdentifier.getCatalogName(),
                remoteTableIdentifier.getSchemaName(),
                remoteTableIdentifier.getTableName(),
                null
        );
        this.baseContainer = baseContainer;
    }

    @Override
    public boolean isRemote() {
        return true;
    }

    public String getBaseContainer() {
        return baseContainer;
    }
}
