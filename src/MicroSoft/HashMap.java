package MicroSoft;

public class HashMap {
}

class MyHashMap1 {
    //quadratic probing,if hash(x)%S failed,we try (hash(x)+1*1)%S for next slot
    //To aviod rehashing cost for increasing hash table size, we can use consistent hashing
    //Ask: number of operations?keys and values range? what kind of methods?
    private static Entry[] hashTable;
    private static int CAPACITY;
    private static int size;
    private static class Entry{
        int key,value;
        boolean deleted;
        public Entry(int key,int value){
            this.key = key;
            this.value = value;
            this.deleted = false;
        }
        public Entry(){
        }
    }
    public MyHashMap1() {
        CAPACITY = 10000;
        hashTable  = new Entry[CAPACITY];
        size = 0;
        for(int i=0;i<CAPACITY;i++){    //there is non-negetive number in hash table
            hashTable[i] = new Entry(-1,-1);
        }
    }
    private int getHashValue(int key){
        return key%CAPACITY;
    }
    /** value will always be non-negative. */
    public void put(int key, int value) {
        if(size==CAPACITY){
            //we need to rehash all existing key-value pair or use consistant hashing
        }
        int position = getHashValue(key);
        int quad = 1;   //quadratic number
        while(hashTable[position].key!=key && hashTable[position].key!=-1){
            position = (position + quad*quad)%CAPACITY;
            quad++;
        }
        hashTable[position].key = key;
        hashTable[position].value = value;
        hashTable[position].deleted = false;
    }

    /** returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int position = getHashValue(key);
        int quad = 1;   //quadratic number
        while(hashTable[position].key!=key){
            if(hashTable[position].key==-1){
                return -1;  //no mapping for this key
            }
            position = (position + quad*quad)%CAPACITY;
            quad++;
        }
        if( hashTable[position].deleted){
            return -1;
        }
        return hashTable[position].value;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int position = getHashValue(key);
        int quad = 1;   //quadratic number
        while(hashTable[position].key!=key){
            if(hashTable[position].key==-1){
                return ;  //no mapping for this key
            }
            position = (position + quad*quad)%CAPACITY;
            quad++;
        }
        hashTable[position].deleted = true;
    }
}

class MyHashMap2{
    //linked List
    private static class Entry{
        int key,value;
        Entry next;
        public Entry(int key,int value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    private static int CAPACITY;
    private static int size;
    private static Entry[] table;
    public MyHashMap2(){
        size = 0;
        CAPACITY = 1000;
        table = new Entry[CAPACITY];
        for(int i=0;i<CAPACITY;i++){
            table[i] = new Entry(-1,-1);
        }
    }

    public void put(int key,int value){
        if(size==CAPACITY){
            //we need to rehash all existing key-value pair or use consistent hashing
        }
        int position = key%CAPACITY;
        Entry head = table[position];
        Entry pre = null;
        while(head!=null && head.key!=key){
            pre = head;
            head = head.next;
        }
        if(head==null){
            pre.next = new Entry(key,value);
        }else{
            head.value = value;
        }
        size++;
    }
    public int get(int key){
        int position = key%CAPACITY;
        Entry head = table[position];
        while(head!=null && head.key!=key){
            head = head.next;
        }
        if(head==null){
            return -1;
        }
        return head.value;
    }
    public void remove(int key){
        int position = key%CAPACITY;
        Entry head = table[position];
        Entry pre = null;
        while(head!=null && head.key!=key){
            pre = head;
            head = head.next;
        }
        if(head==null){
            return ;    //no mapping for this key
        }
        pre.next = head.next;
        size--;

    }
}