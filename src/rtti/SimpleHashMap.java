package rtti;

import java.util.*;

/**
 * Created by bogdan.teut on 14/08/2014.
 */
public class SimpleHashMap<K,V> extends AbstractMap<K,V>{
    
    private int SIZE;
    LinkedList<SlowMapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    public SimpleHashMap(int SIZE) {
        this.SIZE = SIZE;
    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        LinkedList<SlowMapEntry<K,V>> bucket = buckets[index];
        SlowMapEntry<K,V> entry = new SlowMapEntry<K, V>(key, value);
        if (bucket == null){
            bucket = new LinkedList<SlowMapEntry<K,V>>();
            bucket.add(entry);
            return null;
        }
        ListIterator<SlowMapEntry<K,V>> iterator = bucket.listIterator();
        while (iterator.hasNext()){
            SlowMapEntry<K,V> currentEntry = iterator.next();
            if (currentEntry.equals(entry)){
                currentEntry.setValue(value);                
            }            
        }        
        
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
