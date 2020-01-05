package Google.Graph;

import java.util.*;
//第三轮：给一个长度n，代表着长度为n的二进制串（只有0和1），然后给一个起始串，一个结束串，和一个合法路径串的集合，
// 每次允许翻转二进制串的一位，问从起始到结束的最短步数。
//follow-up1: 不用字符串，用整数怎么表示
//follow-up2: 这个api可能会调用多次，怎么高效
//store all distances between numbers: int[][] minDis; minDis[i][j] means minDis between i and j
public class WordLadderII {

  public int minDis(int n,int start,int end,Set<Integer> dict){
    //lens are diff? leading zero?
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    visited.add(start);
    int steps = 0;
    while(!queue.isEmpty()){
      int size = queue.size();
      while(size-->0){
        int cur = queue.poll();
        if(cur==end){
          return steps;
        }
        //flip 1 bit every time
        for(int i=0;i<n;i++){
          int nbr = (cur^(1<<i));
          if(!visited.contains(nbr) && dict.contains(nbr)){
            queue.offer(nbr);
            visited.add(nbr);
          }
        }
      }
      steps++;
    }
    return -1;
  }

  public static void main(String[] args) {
    WordLadderII ins = new WordLadderII();
    Set<Integer> dict = new HashSet<>();
    dict.addAll(Arrays.asList(new Integer[]{5,4,2,1,0}));
    System.out.println(ins.minDis(3,5,1,dict));
    System.out.println(ins.minDis(3,5,2,dict));
  }
}
