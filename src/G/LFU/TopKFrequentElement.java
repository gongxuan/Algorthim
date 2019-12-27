package G.LFU;

import java.util.*;

public class TopKFrequentElement {
//[4,3,1,2,3,3,4,4] ,
//k=2 -> [4,3]
//k=4 -> [4,3,1,2] 一定要维持原来的顺序
  TreeMap<Integer, TreeMap<Integer,Integer>> freqNum = new TreeMap<>();
  Map<Integer,Integer> numFreq = new HashMap<>();

  public static void main(String[] args) {
    int[] A = {4,3,1,2,3,3,4,4};
    TopKFrequentElement ins = new TopKFrequentElement();
    int[] res = ins.topK(A,2);
    ins.print(res);
    res = ins.topK(A,4);
    ins.print(res);
  }
  public void print(int[] res){
    for(int num : res){
      System.out.print(num+" ");
    }
    System.out.println();
  }
  public int[] topK(int[] A,int k){
    if(k>A.length){
      return null;
    }
    Map<Integer,Integer> numToFirstIdx = new HashMap<>();
    for(int i=0;i<A.length;i++){
      if(!numToFirstIdx.containsKey(A[i])){
        numToFirstIdx.put(A[i],i);
      }
    }
    for(int num : A){
      addNum(num,numToFirstIdx.get(num));
    }
    return getTopK(k);
  }
  public int[] getTopK(int k){
    int[] res = new int[k];
    int idx = 0;
    for(Map.Entry<Integer,TreeMap<Integer,Integer>> e: freqNum.descendingMap().entrySet()){
      for(Map.Entry<Integer,Integer> e2 : e.getValue().entrySet()){
        res[idx++] = e2.getValue();
        if(idx==k){
          return res;
        }
      }
    }
    return null;
  }

  public void addNum(int num,int firstOccIdx){
    int oldFreq = numFreq.getOrDefault(num,0);
    int newFreq = oldFreq+1;
    numFreq.put(num,newFreq);
    if(freqNum.containsKey(oldFreq)){
      freqNum.get(oldFreq).remove(firstOccIdx);
      if(freqNum.get(oldFreq).isEmpty()){//!remember to delete oldFreq!!
        freqNum.remove(oldFreq);
      }
    }
    freqNum.computeIfAbsent(newFreq,v->new TreeMap<>()).put(firstOccIdx,num);
  }
}
