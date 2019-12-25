package LinkedIn;

import java.util.*;

public class RandomizedCollection {
    Map<Integer,Integer> idxMap;  //<idx,val>
    Map<Integer, Set<Integer>> valMap;  //<value,indices>
    int count;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        idxMap = new HashMap<>();
        valMap = new HashMap<>();
        random = new Random();
        count  = 0;
    }

    /** Inserts a value to the collection. returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        count++;
        if(valMap.containsKey(val)){
            valMap.get(val).add(count-1);
            idxMap.put(count-1,val);
            return false;
        }else{
            Set<Integer> indices = new HashSet<>();
            indices.add(count-1);
            valMap.put(val,indices);
            idxMap.put(count-1,val);
            return true;
        }
    }

    /** Removes a value from the collection. returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!valMap.containsKey(val)){
            return false;
        }
        count--;
        Set<Integer> oldIndices = valMap.get(val);
        int oldIdx = oldIndices.iterator().next();
        oldIndices.remove(oldIdx);
        if(count>0){
            int lastVal = idxMap.get(count);
            idxMap.put(oldIdx,lastVal);
            Set<Integer> lastIndices = valMap.get(lastVal);
            if(!lastIndices.isEmpty()){ //It's possible remove element==last element
                lastIndices.remove(count);
                lastIndices.add(oldIdx);
            }
            idxMap.remove(count);
        }else{
            idxMap.remove(0);
        }
        if(oldIndices.isEmpty()){
            valMap.remove(val);
        }

        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {

        return idxMap.get(random.nextInt(count));
    }

    public static void main(String[] args) {
        RandomizedCollection r = new RandomizedCollection();
        r.insert(0);
        r.remove(0);
        r.insert(-1);
        r.remove(0);
//        r.insert(2);
//        r.insert(2);
//        r.remove(1);
//        r.remove(2);
//        r.remove(2);
//        r.remove(2);
    }
}
