package Google.BinarySearch;

import java.util.*;

public class ShortestWayToFormString1055 {
  public int shortestWay(String source, String target) {
    //greedy + binary search
    Map<Character,List<Integer>> chToIdx = buildMap(source);
    char[] T = target.toCharArray();
    int sLen = source.length();
    int tLen = target.length();
    int sIdx = 0;
    int count = 1;
    for(int i=0;i<tLen;i++){
      if(!chToIdx.containsKey(T[i])){
        return -1;
      }
      List<Integer> idxList = chToIdx.get(T[i]);
      int idxOfList = binarySearch(idxList,sIdx);
      if(idxOfList==-1){
//        System.out.println(sIdx+" "+count);
        count++;
        sIdx = 0;
        i--;
      }else{
        sIdx = idxList.get(idxOfList)+1;
      }
    }
    return count;
  }
  public Map buildMap(String S){
    Map<Character,List<Integer>> chToIdx = new HashMap<>();
    char[] arr = S.toCharArray();
    for(int i=0;i<S.length();i++){
      chToIdx.computeIfAbsent(arr[i],v->new ArrayList<>()).add(i);
    }
    return chToIdx;
  }
  public int binarySearch(List<Integer> idxList,int target){
    //find first index larger than or equal to target
    //list is in asc order
    int low = 0,hi = idxList.size()-1;
    int ceil = -1;
    while(low<=hi){
      int mid = low + (hi-low)/2;
      int mVal = idxList.get(mid);
      if(target<mVal){
        ceil = mid;
        hi  = mid - 1;
      }else if(target>mVal){
        low = mid + 1;
      }else {
        return mid;
      }
    }
    return ceil;
  }

  public static void main(String[] args) {
    ShortestWayToFormString1055 ins=  new ShortestWayToFormString1055();
//    System.out.println(ins.shortestWay("abc","abcbc"));
    System.out.println(ins.shortestWay("xyz","xyzxz"));
    System.out.println(ins.shortestWay("xyz","xyzxx"));
  }
}
