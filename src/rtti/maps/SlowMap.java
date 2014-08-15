package rtti.maps;

import java.util.AbstractMap;
import java.util.*;


/**
 * Created by bogdan.teut on 13/08/2014.
 */
public class SlowMap<K,V> extends AbstractMap<K,V>{

    private List<K> keys = new ArrayList<K>(); 
    private List<V> values = new ArrayList<V>();

    @Override
    public Set<K> keySet() {
        return new HashSet<K>(keys);
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    public V get(Object key) {
        int index = keys.indexOf(key); 
        return values.get(index);                
    }

    public V put(K key, V value) {
        int index = keys.indexOf(key);

        V oldValue = null;
        if (index != -1){
            oldValue = values.get(index);
            values.set(index, value);
        } else{
            keys.add(key);
            values.add(value);            
        }
        
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        int index = keys.indexOf(key);
        if (index != -1){
            keys.remove(index);
            values.remove(index);
        }
        return null;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> entrySet =  new HashSet<Map.Entry<K, V>>();
        for (K key:keys){
            entrySet.add(new SlowMapEntry<K, V>(key, values.get(keys.indexOf(key))));
        }
        return entrySet;
    }

    public static void main(String[] args) {
        SlowMap<String, String> map = getStringStringSlowMap();
        System.out.println(map);
        System.out.println(map.get("Bulgaria"));

        System.out.println("==================");
        test(map);
        
    }

    private static SlowMap<String, String> getStringStringSlowMap() {
        SlowMap<String, String> map = new SlowMap<String, String>();
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
}

class SlowMapEntry<K,V> implements Map.Entry<K,V>{
    
    private K key;
    private V value;

    SlowMapEntry(K key, V value) {
        this.key = key;
        this.value = value;
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
        return (key== null?0:key.hashCode())
                ^(value== null?0:value.hashCode());
    }

    @Override
    public String toString() {
        return key+" = "+value;
    }
}
