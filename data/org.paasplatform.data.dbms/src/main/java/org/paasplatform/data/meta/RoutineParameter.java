package org.paasplatform.data.meta;

/**
 * Immutable metadata about a parameter used in a stored procedure or function
 *
 * @author John Currier
 */
public class RoutineParameter {
    private final String name;
    private final String type;
    private final String mode;

    /**
     * @param name
     * @param type
     * @param mode
     */
    public RoutineParameter(String name, String type, String mode) {
        this.name = name;
        this.type = type;
        this.mode = mode;
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
    public String getMode() {
        return mode;
    }
}