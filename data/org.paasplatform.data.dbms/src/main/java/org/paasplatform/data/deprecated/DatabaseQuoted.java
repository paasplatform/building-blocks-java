package org.paasplatform.data.deprecated;

/**
 * Quotes the name as if it were an SQL identifier.
 */
public final class DatabaseQuoted implements Name {

    private final DbmsMeta meta;
    private final Name origin;

    public DatabaseQuoted(final DbmsMeta meta, final Name origin) {
        this.meta = meta;
        this.origin = origin;
    }

    @Override
    public String value() {
        String quote = this.meta.getIdentifierQuoteString();
        return quote + this.origin.value() + quote;
    }
}
