package G.Array;
import java.util.*;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=570629&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline

public class HandOfStraights {
//Round2:
//给N个数字，代表cards。问cards能否形成长度5的hands of straight。(846)
// followup：问能否形成hands of straight of length K（K不固定）。例子：[1,2,3,3,4,5]能够形成K=3的straight([1,2,3]和[3,4,5])。
// [1,2,3,4,3,4,5,6]能形成K=4的straight([1,2,3,4]和[3,4,5,6])
//条件：每个card的大小范围是[1,N]。
  //Problem2:
  //https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=569922&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
  // 给你一叠扑克牌N张（但是牌的种类不是A到K，而是1到max_int）， 然后问能不能用所有的牌组成 N/5个straight（德普里面的顺子， 5个连续的正整数序列）,
// 牌不能重复用 不能不用：算法很容易，他expect的算法是 scan一下所有N张牌， 然后统记每种牌的数量， 然后sort一下所有的Unique的牌
// (假设Given Deck是1,1,1,2,2,2,3,3,3, 那Unique的就是1，2，3)， 然后从最小的开始遇到一个比如说1， 就batch perform一个操作
// （for i = 2; i <=5, i++; count [ i ]-= count[ 1 ]）, 然后最后检查一下有没有那种牌的count等于负数了, 如果有那就False， 没有就True。
  //我想的差不多 但是我不是sort unique而是 sort原数组（最后一轮 有点蠢了 小哥万般提示， 我就是没有意识到）， 但是基本操作差不多，
// 最后先说出了他要的优化, 然后就walk 我out了
  public boolean isNStraightHand(int[] hand, int K) {
    //Problem: each group contains at least K cards
    //use all cards
    //for every card, we have two options:
    //1. add to previous straight
    //2. create new straight and set current card as first one.
    //always to option1 first, so we can say
    Map<Integer,Integer> freq = new HashMap<>();
    Arrays.sort(hand);
    for(int num : hand){
      freq.put(num,freq.getOrDefault(num,0)+1);
    }
    for(int h : hand){
      if(!freq.containsKey(h))
        continue;
      reduceFreq(freq,h);

    }
    return true;
  }
  public void reduceFreq(Map<Integer,Integer> freq,int num){
    freq.put(num,freq.get(num)-1);
    if(freq.get(num)==0){
      freq.remove(num);
    }
  }
  //659.Split Array into Consecutive Subsequences
  public boolean isPossible(int[] hand) {
    //duplicates
    //sorted
    //for every number x, two options:
    //1.add x to prev contiguous seq
    //2. make it as first element for next cs.
    //Time: ON
    //test cases:
    //1: 12345,  2: 1233445 3. 123345
    Map<Integer,Integer> prevLast = new HashMap<>();    //<previous cs's last element,freq>
    Map<Integer,Integer> freq = new HashMap<>();    //<remaining number,freq>
    for(int h : hand){
      freq.put(h,freq.getOrDefault(h,0)+1);
    }
    for(int h : hand){
      if(!freq.containsKey(h))
        continue;
      reduceFreq(freq,h);
      if(prevLast.containsKey(h-1)){
        reduceFreq(prevLast,h-1);
        prevLast.put(h,prevLast.getOrDefault(h,0)+1);
      }else{
        for(int i=1;i<3;i++){
          int nxt = i+h;
          if(!freq.containsKey(nxt)){
            return false;
          }
          reduceFreq(freq,nxt);
        }
        prevLast.put(h+2,prevLast.getOrDefault(h+2,0)+1);
      }

    }
    return true;
  }

}
