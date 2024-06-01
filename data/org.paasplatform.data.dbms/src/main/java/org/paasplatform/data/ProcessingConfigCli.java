package org.paasplatform.data;

import org.paasplatform.data.deprecated.ProcessingConfig;

import java.util.Properties;
import java.util.regex.Pattern;

public class ProcessingConfigCli implements ProcessingConfig {

    private String databaseType = "";

    private Pattern columnExclusions = Pattern.compile("[^.]");
    private Pattern indirectColumnExclusions = Pattern.compile("[^.]");

    private Pattern tableInclusions = Pattern.compile(".*");
    private Pattern tableExclusions = Pattern.compile(".*\\$.*");
    private int maxDbThreads = 0;
    private boolean includeRoutineDefinition = false;

    public ProcessingConfigCli() {
    }

    @Override
    public String getDatabaseType() {
        return databaseType;
    }

    @Override
    public Properties getDatabaseTypeProperties() {
        return null;
    }

    @Override
    public boolean isExportedKeysEnabled() {
        return false;
    }

    @Override
    public boolean isNumRowsEnabled() {
        return false;
    }

    @Override
    public boolean isViewsEnabled() {
        return true;
    }

    @Override
    public Pattern getColumnExclusions() {
        return columnExclusions;
    }

    @Override
    public Pattern getIndirectColumnExclusions() {
        return indirectColumnExclusions;
    }

    @Override
    public Pattern getTableInclusions() {
        return tableInclusions;
    }

    @Override
    public Pattern getTableExclusions() {
        return tableExclusions;
    }

    @Override
    public int getMaxDbThreads() {
       return maxDbThreads;
    }

    @Override
    public boolean includeRoutineDefinition() {
        return includeRoutineDefinition;
    }

}
