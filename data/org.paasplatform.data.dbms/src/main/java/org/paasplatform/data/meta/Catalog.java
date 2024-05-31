package org.paasplatform.data.meta;


import java.util.Objects;

/**
 * @author John Currier
 * @author Ismail Simsek
 * @author Daniel Watt
 * @author Nils Petzaell
 */
public final class Catalog implements Comparable<Catalog>{
    private final String name;
    private String comment;

    public Catalog(String name) {
        this(name,null);
    }

    public Catalog(String name, String comment) {
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

    public int compareTo(Catalog i) {
        return this.getName().compareTo(i.getName());
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Catalog) {
            return ((Catalog)o).getName().equals(name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
