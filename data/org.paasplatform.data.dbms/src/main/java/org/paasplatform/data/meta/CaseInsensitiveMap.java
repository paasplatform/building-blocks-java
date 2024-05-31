package org.paasplatform.data.meta;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * A {@link HashMap} implementation that uses {@link String}s as its keys
 * where the keys are treated without regard to case.  That is, <code>get("MyTableName")</code>
 * will return the same object as <code>get("MYTABLENAME")</code>.
 *
 * @author John Currier
 * @author Daniel Watt
 */
public class CaseInsensitiveMap<V> extends HashMap<String, V>
{
    private static final long serialVersionUID = 1L;

    public CaseInsensitiveMap()
    {
    }

    public CaseInsensitiveMap(int initialCapacity)
    {
        super(initialCapacity);
    }

    @Override
    public V get(Object key) {
        V name = null;
        if (key != null) {
            name = super.get(((String)key).toUpperCase());
        }
        return name;
    }

    @Override
    public V put(String key, V value) {
        return super.put(key.toUpperCase(), value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> map) {
        for (Entry<? extends String, ? extends V> e : map.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public V merge(String key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return super.merge(key.toUpperCase(), value, remappingFunction);
    }

    @Override
    public V remove(Object key) {
        return super.remove(((String)key).toUpperCase());
    }

    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(((String)key).toUpperCase());
    }
}