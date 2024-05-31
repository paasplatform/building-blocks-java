package org.paasplatform.data.meta;


import java.util.ArrayList;
import java.util.List;

/**
 * Metadata about a stored procedure or function
 *
 * @author John Currier
 * @author Mårten Bohlin
 */
public class Routine implements Comparable<Routine> {
    private final String name;
    private final String type;
    private final String definitionLanguage;
    private final String definition;
    private final boolean deterministic;
    private final String dataAccess;
    private final String securityType;
    private final String comment;
    private final String returnType;
    private final List<RoutineParameter> params = new ArrayList<>();

    /**
     * @param name
     * @param type
     * @param returnType
     * @param definitionLanguage
     * @param definition
     * @param deterministic
     * @param dataAccess
     * @param securityType
     * @param comment
     */
    public Routine(String name,
                   String type, // function or procedure
                   String returnType,
                   String definitionLanguage,
                   String definition,
                   boolean deterministic, String dataAccess,
                   String securityType, String comment) {
        this.name = name;
        this.type = type;
        this.returnType = returnType;
        this.definitionLanguage = definitionLanguage;
        this.definition = coalesce(definition, "");
        this.dataAccess = dataAccess;
        this.securityType = securityType;
        this.deterministic = deterministic;
        this.comment = comment;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @return
     */
    public String getDefinitionLanguage() {
        return definitionLanguage;
    }

    /**
     * @return
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * @return
     */
    public boolean isDeterministic() {
        return deterministic;
    }

    /**
     * @return
     */
    public String getDataAccess() {
        return dataAccess;
    }

    /**
     * @return
     */
    public String getSecurityType() {
        return securityType;
    }

    /**
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the return type for the routine or null if there is none
     *
     * @return
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * @param param
     */
    public void addParameter(RoutineParameter param) {
        params.add(param);
    }

    /**
     * Returns the types of the routine's parameters.
     * @return
     */
    public List<RoutineParameter> getParameters() {
        return params;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Routine other) {
        int rc = getName().compareTo(other.getName());
        if (rc == 0)
            rc = getType().compareTo(other.getType());
        if (rc == 0)
            rc = String.valueOf(getReturnType()).compareTo(String.valueOf(other.getReturnType()));
        if (rc == 0)
            rc = getDefinition().compareTo(other.getDefinition());
        return rc;
    }

    private <T> T coalesce(T a, T b) {
        return a!=null ? a : b;
    }
}
