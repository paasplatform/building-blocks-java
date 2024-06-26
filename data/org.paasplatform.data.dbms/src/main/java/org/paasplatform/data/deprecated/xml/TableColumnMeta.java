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
 * Additional metadata about a column as expressed in XML instead of from
 * the database.
 *
 * @author John Currier
 * @author Wojciech Kasa
 * @author Daniel Watt
 * @author Nils Petzaell
 */
public class TableColumnMeta {
    private final Node colNode;
    private final String name;
    private final String type;
    private final boolean isPrimary;
    private final String id;
    private final int size;
    private final int digits;
    private final boolean isNullable;
    private final String comments;
    private final String defaultValue;
    private final boolean isAutoUpdated;
    private final List<ForeignKeyMeta> foreignKeys = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public TableColumnMeta(Node colNode) {
        this.colNode = colNode;
        NamedNodeMap attribs = colNode.getAttributes();
        String tmp;

        name = attribs.getNamedItem("name").getNodeValue();
        comments = new CmFacade(colNode).value();

        Node node = attribs.getNamedItem("type");
        type = node == null ? "Unknown" : node.getNodeValue();

        node = attribs.getNamedItem("id");
        id = node == null ? null : node.getNodeValue();

        node = attribs.getNamedItem("size");
        size = node == null ? 0 : Integer.parseInt(node.getNodeValue());

        node = attribs.getNamedItem("digits");
        digits = node == null ? 0 : Integer.parseInt(node.getNodeValue());

        node = attribs.getNamedItem("nullable");
        isNullable = node != null && evalBoolean(node.getNodeValue());

        node = attribs.getNamedItem("autoUpdated");
        isAutoUpdated = node != null && evalBoolean(node.getNodeValue());

        node = attribs.getNamedItem("primaryKey");
        isPrimary = node != null && evalBoolean(node.getNodeValue());

        node = attribs.getNamedItem("defaultValue");
        defaultValue = node == null ? null : node.getNodeValue();

        LOGGER.debug("Found XML column metadata for {} isPrimaryKey: {} comments: {}", name, isPrimary, comments);

        NodeList fkNodes = ((Element)colNode.getChildNodes()).getElementsByTagName("foreignKey");

        for (int i = 0; i < fkNodes.getLength(); ++i) {
            Node fkNode = fkNodes.item(i);
            foreignKeys.add(new ForeignKeyMeta(fkNode));
        }
    }

    private boolean evalBoolean(String exp) {
        if (exp == null)
            return false;

        String returnExp = exp.trim().toLowerCase();
        return "true".equals(returnExp) || "yes".equals(returnExp) || "1".equals(returnExp);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getDigits() {
        return digits;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public boolean isAutoUpdated() {
        return isAutoUpdated;
    }

    public String getComments() {
        return comments;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public List<ForeignKeyMeta> getForeignKeys() {
        return foreignKeys;
    }

    public boolean isExcluded() {
        final NamedNodeMap attribs = colNode.getAttributes();
        final boolean isExcluded;

        Node node = attribs.getNamedItem("disableDiagramAssociations");
        if (node != null) {
            final String tmp = node.getNodeValue().trim().toLowerCase();
            switch (tmp) {
                case "all":
                    isExcluded = true;
                    break;
                case "exceptdirect":
                    isExcluded = true;
                    break;
                default:
                    isExcluded = false;
                    break;
            }
        } else {
            isExcluded = false;
        }
        return isExcluded;
    }

    public boolean isAllExcluded() {
        final NamedNodeMap attribs = colNode.getAttributes();
        final boolean isAllExcluded;

        Node node = attribs.getNamedItem("disableDiagramAssociations");
        if (node != null) {
            final String tmp = node.getNodeValue().trim().toLowerCase();
            switch (tmp) {
                case "all":
                    isAllExcluded = true;
                    break;
                case "exceptdirect":
                    isAllExcluded = false;
                    break;
                default:
                    isAllExcluded = false;
                    break;
            }
        } else {
            isAllExcluded = false;
        }
        return isAllExcluded;
    }

    public boolean isImpliedParentsDisabled() {
        final NamedNodeMap attribs = colNode.getAttributes();
        final boolean isImpliedParentsDisabled;

        Node node = attribs.getNamedItem("disableImpliedKeys");
        if (node != null) {
            final String tmp = node.getNodeValue().trim().toLowerCase();
            switch (tmp) {
                case "all":
                case "from":
                    isImpliedParentsDisabled = true;
                    break;
                case "to":
                default:
                    isImpliedParentsDisabled = false;
                    break;
            }
        } else {
            isImpliedParentsDisabled = false;
        }
        return isImpliedParentsDisabled;
    }

    public boolean isImpliedChildrenDisabled() {
        final NamedNodeMap attribs = this.colNode.getAttributes();
        final boolean isImpliedChildrenDisabled;

        Node node = attribs.getNamedItem("disableImpliedKeys");
        if (node != null) {
            final String tmp = node.getNodeValue().trim().toLowerCase();
            switch (tmp) {
                case "all":
                case "to":
                    isImpliedChildrenDisabled = true;
                    break;
                case "from":
                default:
                    isImpliedChildrenDisabled = false;
                    break;
            }
        } else {
            isImpliedChildrenDisabled = false;
        }

        return isImpliedChildrenDisabled;
    }
}
