package G.SlidingWindow;

import java.util.*;

public class ShortestDisTwoCh {
//第二轮：一个字符串里面给定两个字符，求最短的距离.
  Map<Character,Integer> freq = new HashMap<>();
  public int minDis(String s,char a,char b){
    //any duplicates in s?
    //what if a or b is not in s? -1
    int left=0,right=0;
    int minDis = Integer.MAX_VALUE;
    char[] arr = s.toCharArray();
    while(right<arr.length){
      freq.put(arr[right],freq.getOrDefault(arr[right],0)+1);
      while(hasTwoCh(a,b)){
        if(right-left<minDis){
          minDis = right - left;
        }
        freq.put(arr[left],freq.get(arr[left])-1);
        if(freq.get(arr[left])==0){
          freq.remove(arr[left]);
        }
        left++;
      }
      right++;
    }
    return minDis==Integer.MAX_VALUE?-1:minDis;
  }
  public boolean hasTwoCh(char a,char b){
    return freq.containsKey(a) && freq.containsKey(b);
  }

  public static void main(String[] args) {
    ShortestDisTwoCh ins = new ShortestDisTwoCh();
    String s = "bxxxxaxxbxx";
    char a  ='a';
    char b = 'b';
    System.out.println(ins.minDis(s,a,b));
  }
}
