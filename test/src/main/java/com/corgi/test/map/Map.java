package com.corgi.test.map;

public interface Map<K,V> {

    public V put(K k,V v);

    public V get(K k);

    public int size();

    interface Entry<K,V>{
        K getKey();
        V getValue();
        V setValue(V v);
    }
}
