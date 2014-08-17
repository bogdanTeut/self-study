package rtti.maps;

import java.util.*;

/**
 * Created by bogdan.teut on 14/08/2014.
 */
public class LinkedEntriesHashMap<K,V> extends AbstractMap<K,V>{
    
    private int SIZE;
    LinkedMapEntry<K,V>[] buckets;

    public LinkedEntriesHashMap(int SIZE) {
        this.SIZE = SIZE;
        buckets = new LinkedMapEntry[SIZE];
    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(key);

        for(LinkedMapEntry<K,V> entry = buckets[index];entry != null;entry = entry.next){
            if (entry.getKey().equals(key)){
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        buckets[index] = new LinkedMapEntry<K, V>(key, value);
        return null;
    }

    @Override
    public V get(Object key) {
        int index = getIndex((K)key);
        for(LinkedMapEntry<K,V> entry = buckets[index];entry != null;entry = entry.next){
            if (entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()%SIZE);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {

                final LinkedMapEntry<K,V> next = null;
                final LinkedMapEntry<K,V> current = null;
                int index = 0;

                {
                    while(index<buckets.length-1 && (next = buckets[index++]) == null);
                }

                return new Iterator<Entry<K, V>>() {
                    @Override
                    public boolean hasNext() {
                        return false;
                    }

                    @Override
                    public Entry<K, V> next() {
                        next = current.next;
                    }

                    @Override
                    public void remove() {

                    }
                };
            }

            @Override
            public int size() {
                return 0;
            }
        };
    }

    private static LinkedEntriesHashMap<String, String> getStringStringSlowMap() {
        LinkedEntriesHashMap<String, String> map = new LinkedEntriesHashMap<String, String>(3);
        map.put("Bulgaria", "Sofia");
        map.put("Romania", "Bucharest");
        map.put("Hungary", "Budapest");
        return map;
    }

    public static void test(Map<String,String> map) {
        System.out.println(map.getClass().getSimpleName());
        map.putAll(getStringStringSlowMap());
// Map has ‘Set’ behavior for keys:
        map.putAll(getStringStringSlowMap());
        System.out.println(map.keySet());
// Producing a Collection of the values:
        System.out.println("Values: ");
        System.out.println(map.values());
        System.out.println(map);
        System.out.println("map.containsKey(Bulgaria): " + map.containsKey("Bulgaria"));
        System.out.println(("map.containsKey(Bulgaria): " + map.get("Bulgaria")));

        System.out.println("map.containsValue(Sofia): "
                + map.containsValue("Sofia"));
        String key = map.keySet().iterator().next();
        System.out.println("First key in map: " + key);
        map.remove(key);
        System.out.println(map.keySet());
        System.out.println(("map.isEmpty(): " + map.isEmpty()));
        map.clear();
        System.out.println(("map.isEmpty(): " + map.isEmpty()));
        map.putAll(getStringStringSlowMap());
        System.out.println(map);
// Operations on the Set change the Map:
        map.keySet().removeAll(map.keySet());
//        System.out.println(map.keySet());
        System.out.println("map.isEmpty(): " + map.isEmpty());


        System.out.println("Hashmap test");
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("A","Capital A");
        hashMap.put("B","Capital B");
        hashMap.put("C","Capital C");
        System.out.println(map.keySet());
        map.keySet().removeAll(map.keySet());
        System.out.println(map.keySet());
    }


    public static void main(String[] args) {
        LinkedEntriesHashMap<String, String> map = getStringStringSlowMap();
        System.out.println(map);
        System.out.println(map.get("Bulgaria"));
        
        test(map);
    }
}
