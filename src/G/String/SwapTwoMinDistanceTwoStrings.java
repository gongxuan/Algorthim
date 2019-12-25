package G.String;

import java.util.*;

//https://www.1point3acres.com/bbs/thread-578232-1-1.html
//第一轮：
//给两个字符串a, b，长度相等，定义两者之间的距离为，如果同一位置字符不一样，距离加一。返回b的两个index，将这两个index的字符互换可以让距离最小。
public class SwapTwoMinDistanceTwoStrings {

  public static void main(String[] args) {
    SwapTwoMinDistanceTwoStrings ins = new SwapTwoMinDistanceTwoStrings();
    String A = "cfd";
    String B = "amc";
    int[] res  = ins.find(A,B);
    System.out.println(res[0]+" "+res[1]);

  }
  public int[] find(String A,String B){
    //multiple results? result index?
    //brute force way N2, try to swap all pair of same chars in two strings
    Map<Character, List<Integer>> aIdxMap = buildOccMap(A);
    char[] b = B.toCharArray();
    char[] a = A.toCharArray();
    int[] res = new int[2];
    for(int bIdx=0;bIdx<b.length;bIdx++){
      if(!aIdxMap.containsKey(b[bIdx])) continue;
      List<Integer> occ = aIdxMap.get(b[bIdx]);
      for(int j=0;j<occ.size();j++){
        int aIdx = occ.get(j);
        if(a[bIdx]==b[aIdx]){
          res[0] = bIdx;
          res[1] = aIdx;

          return res;
        }else{
          res[0] = bIdx;
          res[1] = aIdx;
        }
      }
    }
    return res;
  }

  public Map buildOccMap(String A){
    Map<Character, List<Integer>> map  = new HashMap<>();
    char[] arr = A.toCharArray();
    for(int i=0;i<arr.length;i++){
      map.computeIfAbsent(arr[i],v->new ArrayList<>()).add(i);
    }
    return map;
  }
}
