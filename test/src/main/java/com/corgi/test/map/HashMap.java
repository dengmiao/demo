package com.corgi.test.map;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-20 15:29
 **/
public class HashMap<K, V> implements Map<K, V> {

    Entry<K, V> table[] = null;
    private static int defaultLength = 16;//默认长度
    private static double defaultLoader = 0.75d;//加载因子
    private int size;

    @Override
    public V put(K k, V v) {
        if(table == null) {
            table = new Entry[defaultLength];
        }
        //通过hash算法，计算要插入的下标值
        int index = getIndex(k,defaultLength);
        //是否需要扩容
        if(size > defaultLength * defaultLoader){
            reSize();
        }
        //插入值
        Node<K, V> node = (Node<K, V>) table[index];
        if(table[index] == null){
            table[index] = new Node<K, V>(k, v, null);
            size++;
        }else{
            if(k.equals(node.getKey()) || k == node.getKey()){
                return node.setValue(v);
            }else{
                table[index] = new Node<K, V>(k, v, node);
                size++;
            }
        }
        return null;
    }

    private void reSize() {
        if(size > defaultLength * defaultLoader){
            //扩容*2
            System.out.println("reSize");
            //创建新table
            Entry<K, V> newTable[] = new Entry[defaultLength<<1];
            //每个值重新散列，插入到新的newTable
            Node<K, V> e = null;
            for(int i = 0; i < table.length; i++){
                e = (Node<K, V>) table[i];
                while(e != null){
                    //重新散列
                    int index = getIndex(e.getKey(), newTable.length);
                    Entry<K, V> next = e.next;
                    e.next = (Node<K, V>)newTable[index];
                    newTable[index] = e;
                    e = (Node<K, V>) next;
                }
            }
            //替换旧table
            table = newTable;
            defaultLength = newTable.length;
            newTable = null;
        }
    }

    private int getIndex(Object k,int length) {
        if(k == null){
            return 0;
        }
        //hash算法实现
        int hash = k.hashCode();//得到hash值
        //取模算法
//		hash % (defaultLength -1)
        //与算法
//		hash & (defaultLength -1)
        return hash & (length - 1);
    }

    @Override
    public V get(K k) {
        if(table != null){
            int index = getIndex(k, defaultLength);
            Node<K, V> node = getNode((Node<K, V>) table[index],k);
            if(node != null){
                return node.getValue();
            }
        }
        return null;
    }

    private Node<K, V> getNode(Node<K, V> node, K k) {
        while(node != null){
            if(node.getKey().equals(k)){
                return node;
            }else{
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        System.out.println("--------------------------");
        String str = "";
        if(table != null){
            Node<K, V> e = null;
            for(int i = 0; i < table.length; i++){
                e = (Node<K, V>) table[i];
                str += "index[" + i + "] : ";
                while(e != null){
                    str += "[" + e.getKey() + "-" + e.getValue() + "]";
                    if(e.next != null){
                        e = e.next;
                    }else{
                        e = null;
                    }
                }
                str += "\n";
            }
        }
        return str;
    }

    static class Node<K, V> implements Entry<K, V> {

        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            super();
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V v) {
            V oldValue = this.value;
            this.value = v;
            return oldValue;
        }

    }
}
