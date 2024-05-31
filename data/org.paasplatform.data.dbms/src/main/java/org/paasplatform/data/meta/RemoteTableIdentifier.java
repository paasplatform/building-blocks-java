package org.paasplatform.data.meta;

import org.paasplatform.data.meta.xml.ForeignKeyMeta;
import org.paasplatform.data.meta.xml.TableMeta;

public class RemoteTableIdentifier {

    public static RemoteTableIdentifier from(ExportForeignKey foreignKey) {
        return new RemoteTableIdentifier(
                foreignKey.getFkTableCat(),
                foreignKey.getFkTableSchema(),
                foreignKey.getFkTableName()
        );
    }

    public static RemoteTableIdentifier from(ImportForeignKey foreignKey) {
        return new RemoteTableIdentifier(
                foreignKey.getPkTableCat(),
                foreignKey.getPkTableSchema(),
                foreignKey.getPkTableName()
        );
    }

    public static RemoteTableIdentifier from(TableMeta tableMeta) {
        return new RemoteTableIdentifier(
                tableMeta.getRemoteCatalog(),
                tableMeta.getRemoteSchema(),
                tableMeta.getName()
        );
    }

    public static RemoteTableIdentifier from(ForeignKeyMeta foreignKeyMeta) {
        return new RemoteTableIdentifier(
                foreignKeyMeta.getRemoteCatalog(),
                foreignKeyMeta.getRemoteSchema(),
                foreignKeyMeta.getTableName()
        );
    }

    private final String catalogName;
    private final String schemaName;
    private final String tableName;

    public RemoteTableIdentifier(String catalogName, String schemaName, String tableName) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public String getTableName() {
        return tableName;
    }
}
