package org.paasplatform.data.deprecated;


/**
 * Treat views as tables that have no rows and are represented by the SQL that
 * defined them.
 */
public class View extends Table {
    private String viewDefinition;

    /**
     * @param db
     * @param catalog
     * @param schema
     * @param name
     * @param remarks
     * @param viewDefinition
     */
    public View(Database db, String catalog, String schema,
                String name, String remarks, String viewDefinition) {
        super(db, catalog, schema, name, remarks);

        setViewDefinition(viewDefinition);
    }

    /**
     * @return
     */
    @Override
    public boolean isView() {
        return true;
    }

    @Override
    public String getViewDefinition() {
        return viewDefinition;
    }

    public void setViewDefinition(String viewDefinition) {
        if (viewDefinition != null && viewDefinition.trim().length() > 0)
            this.viewDefinition = viewDefinition;
    }
}
