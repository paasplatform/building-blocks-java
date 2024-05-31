package org.paasplatform.data.meta;

import java.util.Objects;

/**
 * @author John Currier
 * @author Ismail Simsek
 * @author Daniel Watt
 * @author Nils Petzaell
 */
public final class Schema implements Comparable<Schema>{
    private final String name;
    private String comment =null;

    public Schema(String name) {
        this(name,null);
    }

    public Schema(String name, String comment) {
        this.name = Objects.requireNonNull(name);
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int compareTo(Schema i) {
        return this.getName().compareTo(i.getName());
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Schema) {
            return ((Schema)o).getName().equals(name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}