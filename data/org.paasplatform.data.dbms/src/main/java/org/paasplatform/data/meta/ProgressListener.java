package org.paasplatform.data.meta;

import java.util.Collection;

/**
 * Listener of schema analysis and ERD generation progress.
 * Overall intent is to allow various views to render progress details appropriately.
 *
 * @author John Currier
 */
public interface ProgressListener {

    void startCollectingTablesViews();
    void tableViewCollected(Table table);
    /**
     * @return duration of CollectingTablesViews in milliseconds
     */
    long finishedCollectingTablesViews();

    void startConnectingTablesViews();
    void connectedTableView(Table table);
    /**
     * @return duration of ConnectingTablesViews in milliseconds
     */
    long finishedConnectingTablesViews();

    void startCreatingSummaries();
    void createdSummary();
    /**
     * @return duration of CreatingSummaries in milliseconds
     */
    long finishedCreatingSummaries();

    void startCreatingTablePages();
    void createdTablePage(Table table);

    /**
     * @return duration of CreatingTablePages in milliseconds
     */
    long finishedCreatingTablePages();

    /**
     * @return overall duration in milliseconds
     */
    long finished(Collection<Table> tables);
}