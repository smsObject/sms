package com.sms.common;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by moon
 * on 2014/12/3.
 */
public class ParamMap<K, V> {
    private Map<K, V> map = new HashMap<K, V>();

    public void setMap(Map<K, V> map) {
        this.map = map;
    }

    public Map<K, V> getMap() {
        return map;
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    public boolean equals(Object o) {
        return map.equals(o);
    }

    public V get(Object key) {
        return map.get(key);
    }

    public int hashCode() {
        return map.hashCode();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public V put(K key, V value) {
        return map.put(key, value);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    public V remove(Object key) {
        return map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public Collection<V> values() {
        return map.values();
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public String getString(String key) {
        Object value = get(key);
        return value == null ? null : value.toString().trim();
    }

    public Integer getInteger(String key) {
        String value = getString(key);
        return StringUtils.isEmpty(value) ? null : Integer.valueOf(value);
    }

    public int getIntegerDefaultZero(String key) {
        Integer i = getInteger(key);
        return i == null ? 0 : i;
    }

    public Long getLong(String key) {
        String value = getString(key);
        return StringUtils.isEmpty(value) ? null : Long.valueOf(value);
    }

    public long getLongDefaultZero(String key) {
        Long l = getLong(key);
        return l == null ? 0L : l;
    }

    public Double getDouble(String key) {
        String value = getString(key);
        return StringUtils.isEmpty(value) ? null : Double.valueOf(value);
    }

    public BigDecimal getBigDecimal(String key) {
        Double value = getDouble(key);
        return value == null ? null : BigDecimal.valueOf(value);
    }

    public Byte getByte(String key) {
        String value = getString(key);
        return StringUtils.isEmpty(value) ? null : Byte.valueOf(value);
    }
}
