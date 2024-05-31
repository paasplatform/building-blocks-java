package org.paasplatform.data.meta;


/**
 * Base class to indicate that there was problem with how SchemaSpy was configured / used.
 *
 * @author John Currier
 * @author Nils Petzaell
 */
public class InvalidConfigurationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String paramName;
    private final String paramValue;

    /**
     * When a message is sufficient
     *
     * @param msg
     */
    public InvalidConfigurationException(String msg) {
        super(msg);
        paramName = null;
        paramValue = null;
    }

    /**
     * When there's an associated root cause.
     * The resultant msg will be a combination of <code>msg</code> and cause's <code>msg</code>.
     *
     * @param msg
     * @param cause
     */
    public InvalidConfigurationException(String msg, Throwable cause) {
        super(msg, cause);
        paramName = null;
        paramValue = null;
    }

    public InvalidConfigurationException(String msg, Throwable cause, String paramName, String paramValue) {
        super(msg, cause);
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    /**
     * When there are no details other than the root cause
     *
     * @param cause
     */
    public InvalidConfigurationException(Throwable cause) {
        super(cause);
        paramName = null;
        paramValue = null;
    }

    public InvalidConfigurationException(Throwable cause, String paramName, String paramValue) {
        super(cause);
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public String getParamName() {
        return paramName;
    }


    public String getParamValue() {
        return paramValue;
    }
}

