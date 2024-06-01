package org.paasplatform.data.deprecated;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by rkasa on 2016-12-10.
 *  "macro" to validate that a table is somewhat valid
 *
 * @author John Currier
 * @author Rafal Kasa
 * @author Daniel Watt
 * @author Nils Petzaell
 */
public class NameValidator {
    private final String clazz;
    private final Pattern include;
    private final Pattern exclude;
    private final Set<String> validTypes;

    /**
     * @param clazz table or view
     * @param include
     * @param exclude
     * @param validTypes
     */
    public NameValidator(String clazz, Pattern include, Pattern exclude, String[] validTypes) {
        this.clazz = clazz;
        this.include = include;
        this.exclude = exclude;
        this.validTypes = new HashSet<>();
        for (String type : validTypes) {
            this.validTypes.add(type.toUpperCase());
        }
    }

    /**
     * Returns <code>true</code> if the table/view name is deemed "valid"
     *
     * @param name name of the table or view
     * @param type type as returned by metadata.getTables():TABLE_TYPE
     * @return
     */
    public boolean isValid(String name, String type) {
        return new DatabasetypeValidator(this.validTypes).isValid(type)
                && new ExclusionValidator(this.clazz, this.exclude).isValid(name)
                && new InclusionValidator(this.clazz, this.include).isIncluded(name);
    }
}
