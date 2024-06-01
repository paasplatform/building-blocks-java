package org.paasplatform.data.deprecated.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;


/**
 * Additional metadata about a table as expressed in XML instead of from
 * the database.
 *
 * @author John Currier
 * @author Daniel Watt
 * @author Nils Petzaell
 */
public class TableMeta {
    private final String name;
    private final String comments;
    private final List<TableColumnMeta> columns = new ArrayList<>();
    private final String remoteCatalog;
    private final String remoteSchema;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    TableMeta(Node tableNode) {
        NamedNodeMap attribs = tableNode.getAttributes();

        name = attribs.getNamedItem("name").getNodeValue();
        comments = new CmFacade(tableNode).value();

        Node node = attribs.getNamedItem("remoteSchema");
        remoteSchema = node == null ? null : node.getNodeValue().trim();

        node = attribs.getNamedItem("remoteCatalog");
        remoteCatalog = node == null ? null : node.getNodeValue().trim();

        LOGGER.debug("Found XML table metadata for {} remoteCatalog: {} remoteSchema: {} comments: {}", name, remoteCatalog, remoteSchema, comments);

        NodeList columnNodes = ((Element)tableNode.getChildNodes()).getElementsByTagName("column");

        for (int i = 0; i < columnNodes.getLength(); ++i) {
            Node colNode = columnNodes.item(i);
            columns.add(new TableColumnMeta(colNode));
        }
    }

    public String getName() {
        return name;
    }

    public String getComments() {
        return comments;
    }

    public List<TableColumnMeta> getColumns() {
        return columns;
    }

    public String getRemoteCatalog() {
        return remoteCatalog;
    }

    public String getRemoteSchema() {
        return remoteSchema;
    }
}