package MultiThread;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

public class CustomHashMap<K,V> {
    //quadratic probing,if hash(x)%S failed,we try (hash(x)+1*1)%S for next slot
    //To aviod rehashing cost for increasing hash table size, we can use consistent hashing
    //Ask: number of operations?keys and values range? what kind of methods?
    private static ArrayList<Entry> entries;
    private static int CAPACITY;

    static final class Entry<K,V> extends ReentrantLock {
        K key;
        volatile V value;
        Entry<K,V> next;
        //The number of elements in this segment's region.
        volatile int count;

        public Entry(K key,V value,Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public Entry(){

        }
    }
    public CustomHashMap() {
        CAPACITY = 10000;
        entries = new ArrayList<>(CAPACITY);
//        for(int i=0;i<CAPACITY;i++){    //there is non-negative number in hash table
//            entries.add(new Entry());
//        }
    }
    private static int hash(int key){
        return key%CAPACITY;
    }
    /** value will always be non-negative. */
    public void put(K key, V value) {
        if(value==null)
            throw new NullPointerException();

        int hash = hash(key.hashCode());
        Entry<K,V> head = segmentFor(hash);
        head.lock();
        try{
            if(head==null){
                entries.set(hash,new Entry<>());
            }else{
                Entry<K,V> cur = head;
                while(cur.next!=null && !cur.next.key.equals(key)){
                    cur = cur.next;
                }
                if(cur.next==null){
                    cur.next= new Entry<>(key,value,null);
                    head.count++;
                }else{  //update value
                    cur.next.value = value;
                }
            }
        }finally{
            head.unlock();
        }


    }

    /** returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public V get(K key) {
        if(key==null)
            throw new NullPointerException();
        int hash = hash(key.hashCode());
        Entry<K,V> head = segmentFor(hash);
        if(head==null){
            throw new NoSuchElementException();
        }
        while(head.next!=null){
            if(head.next.key.equals(key)){
                return head.next.value;
            }
        }
        return null;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = hash(key);
        Entry<K,V> head = segmentFor(hash);
        if(head==null)
            return ;
        head.lock();
        try{
            Entry<K,V> cur = head;
            while(cur.next!=null && cur.next.key.equals(key)){
                cur = cur.next;
            }
            if(cur.next!=null) {
                cur.next = cur.next.next;
                if(head.count==1)
                    entries.remove(hash);
            }
        }finally{
            head.unlock();
        }

    }

    private final Entry segmentFor(int hash){
        return entries.get(hash);
    }
}



