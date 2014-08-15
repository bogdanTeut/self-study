package rtti.maps;


import java.util.*;

/**
 * Created by bogdan.teut on 14/08/2014.
 */
public class SimpleHashMap<K,V> extends AbstractMap<K,V>{
    
    private int SIZE;
    LinkedList<SlowMapEntry<K,V>>[] buckets;

    public SimpleHashMap(int SIZE) {
        this.SIZE = SIZE;
        buckets = new LinkedList[SIZE];
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = getIndex(key);
        if (buckets[index] == null){
            buckets[index] = new LinkedList<SlowMapEntry<K,V>>();
        }
        
        LinkedList<SlowMapEntry<K,V>> bucket = buckets[index];
        SlowMapEntry<K,V> entry = new SlowMapEntry<K, V>(key, value);
        boolean found = false;
        
        ListIterator<SlowMapEntry<K,V>> iterator = bucket.listIterator();
        while (iterator.hasNext()){
            SlowMapEntry<K,V> currentEntry = iterator.next();
            if (currentEntry.equals(entry)){
                oldValue = currentEntry.getValue();
                currentEntry.setValue(value); 
                found = true;
            }            
        }
        if(!found){
            bucket.add(entry);
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = getIndex((K)key);
        LinkedList<SlowMapEntry<K,V>> bucket = buckets[index];
        if (bucket!=null){
            ListIterator<SlowMapEntry<K,V>> iterator = bucket.listIterator();
            while (iterator.hasNext()){
                SlowMapEntry<K,V> currentEntry = iterator.next();
                if (currentEntry.getKey().equals(key)){
                    return  currentEntry.getValue();
                }
            }    
        }
        return null;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()%SIZE);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new LinkedHashSet<Entry<K, V>>();
        for (LinkedList<SlowMapEntry<K,V>> bucket:buckets){
            if (bucket != null){
                ListIterator<SlowMapEntry<K,V>> iterator = bucket.listIterator();
                while (iterator.hasNext()){
                    entrySet.add(iterator.next());
                }                
            }            
        }
        return entrySet;
    }

    private static SimpleHashMap<String, String> getStringStringSlowMap() {
        SimpleHashMap<String, String> map = new SimpleHashMap<String, String>(3);
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
        SimpleHashMap<String, String> map = getStringStringSlowMap();
        System.out.println(map);
        System.out.println(map.get("Bulgaria"));
        
        test(map);
    }
}
