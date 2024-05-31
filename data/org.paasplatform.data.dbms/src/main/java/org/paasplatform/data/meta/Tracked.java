package org.paasplatform.data.meta;



import java.util.Collection;

/**
 * Implementation of {@link ProgressListener}.
 *
 * @author John Currier
 * @author Thomas Traude
 */
public class Tracked implements ProgressListener {

    private final long startedAt;
    private long startedCollectingTablesViewsAt;
    private long startedConnectingTablesViewsAt;
    private long startedCreatingSummariesAt;
    private long startedCreatingTablePagesAt;

    public Tracked() {
        startedAt = System.currentTimeMillis();
    }

    @Override
    public void startCollectingTablesViews() {
        startedCollectingTablesViewsAt = System.currentTimeMillis();
    }

    @Override
    public void tableViewCollected(Table table) { }

    /**
     * Assumes <code>startCollectingTablesViews</code> has already been called.
     */
    @Override
    public long finishedCollectingTablesViews() {
        return System.currentTimeMillis() - startedCollectingTablesViewsAt;
    }

    @Override
    public void startConnectingTablesViews() {
        startedConnectingTablesViewsAt = System.currentTimeMillis();
    }

    @Override
    public void connectedTableView(Table table) { }

    /**
     * Assumes <code>startConnectingTablesViews</code> has already been called.
     */
    @Override
    public long finishedConnectingTablesViews() {
        return System.currentTimeMillis() - startedConnectingTablesViewsAt;
    }

    @Override
    public void startCreatingSummaries() {
        startedCreatingSummariesAt = System.currentTimeMillis();
    }

    @Override
    public void createdSummary() { }

    /**
     * Assumes <code>startCreatingSummaries</code> has already been called.
     */
    @Override
    public long finishedCreatingSummaries() {
        return System.currentTimeMillis() - startedCreatingSummariesAt;
    }

    @Override
    public void startCreatingTablePages() {
        startedCreatingTablePagesAt = System.currentTimeMillis();
    }

    @Override
    public void createdTablePage(Table table) { }

    /**
     * Assumes <code>startedGraphingDetails</code> has already been called.
     */
    @Override
    public long finishedCreatingTablePages() {
        return System.currentTimeMillis() - startedCreatingTablePagesAt;
    }

    @Override
    public long finished(Collection<Table> tablesg) {
        return System.currentTimeMillis() - startedAt;
    }
}

