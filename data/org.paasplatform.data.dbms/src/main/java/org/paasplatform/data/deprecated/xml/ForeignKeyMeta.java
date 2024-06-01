package org.paasplatform.data.deprecated.xml;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.lang.invoke.MethodHandles;

/**
 * Additional metadata about a foreign key relationship as expressed in XML
 * instead of from the database.
 *
 * @author John Currier
 * @author Daniel Watt
 */
public class ForeignKeyMeta {
    private final String tableName;
    private final String columnName;
    private final String remoteCatalog;
    private final String remoteSchema;
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public ForeignKeyMeta(Node foreignKeyNode) {
        NamedNodeMap attribs = foreignKeyNode.getAttributes();
        Node node = attribs.getNamedItem("table");
        if (node == null)
            throw new IllegalStateException("XML foreignKey definition requires 'table' attribute");
        tableName = node.getNodeValue();
        node = attribs.getNamedItem("column");
        if (node == null)
            throw new IllegalStateException("XML foreignKey definition requires 'column' attribute");
        columnName = node.getNodeValue();
        node = attribs.getNamedItem("remoteSchema");
        remoteSchema = node == null ? null : node.getNodeValue();
        node = attribs.getNamedItem("remoteCatalog");
        remoteCatalog = node == null ? null : node.getNodeValue();

        LOGGER.debug("Found XML FK metadata for {}.{} remoteCatalog: {} remoteSchema: {}", tableName, columnName, remoteCatalog, remoteSchema);
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getRemoteCatalog() {
        return remoteCatalog;
    }

    public String getRemoteSchema() {
        return remoteSchema;
    }

    @Override
    public String toString() {
        return tableName + '.' + columnName;
    }
}