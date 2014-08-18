package rtti.maps;

import java.util.Map;

/**
 * Created by bogdan.teut on 15/08/2014.
 */
public class LinkedMapEntry<K,V> implements Map.Entry<K,V>{

    private K key;
    private V value;
    protected LinkedMapEntry<K,V> next;

    public LinkedMapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public LinkedMapEntry(K key, V value, LinkedMapEntry<K,V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SlowMapEntry)) return false;
        SlowMapEntry<K,V> otherEntry = (SlowMapEntry)obj;

        return (key == null?
                otherEntry.getKey()==null
                :key.equals(otherEntry.getKey()))&&
                (value == null?
                        otherEntry.getValue()==null
                        :value.equals(otherEntry.getValue()));
    }

    @Override
    public int hashCode() {
        int result =17;
        result = result*37 + key.hashCode();
        result = result*37 + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return key+" = "+value;
    }
}
