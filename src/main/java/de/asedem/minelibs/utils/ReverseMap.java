package de.asedem.minelibs.utils;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * A Map, where you can the keys only have one as well as the values
 *
 * @param <K> The key
 * @param <V> The value
 */
public class ReverseMap<K, V> extends HashMap<K, V> {

    private final Map<V, K> helpMap = new HashMap<>();

    /**
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return The inserted value
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        if (this.containsKey(key) || this.helpMap.containsKey(value)) return null;
        this.helpMap.put(value, key);
        return super.put(key, value);
    }

    /**
     * Gets the Key to a specific Value
     *
     * @param value The value
     * @return The key
     */
    @Nullable
    public K getKey(V value) {
        return this.helpMap.get(value);
    }
}
