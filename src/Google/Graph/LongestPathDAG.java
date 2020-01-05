package Google.Graph;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=570261&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class LongestPathDAG {
  //input是一组string，找出符合规则的最长chain。chain的规则为后一个string由前一个string在任意位置插入任意字符得出，
  // 如: a->ab->acb->acbd。自己实现所有方法，不得调库
  //eg：input ['bad', 'ad', 'ccc', 'd', 'bacd'] -> output ['d', 'ad', 'bad', 'bacd']
  //topo Time:O(V+E)+O(N2)
  List<Integer>[] graph;
  int[] indegree;
  int maxLen,wLen;
  int maxLastWord;
  Map<Integer,Integer> childToP;

  public List<String> findLongestPath(String[] words){
    //multiple results? what kind of chars
    //topo, store <child,parent> , last parent will belongs to longest path.
    wLen = words.length;
    childToP = new HashMap<>();
    maxLen = 0;
    maxLastWord = -1;
    Arrays.sort(words,(a,b)->a.length()-b.length());
    buildGraph(words);
    topo();
    return printPath(words);
  }
  public void buildGraph(String[] words){
    Map<Integer,Set<String>> lenToWords = new HashMap<>();
    graph = new List[wLen];
    indegree = new int[wLen];
    for(int i=0;i<wLen;i++){
      graph[i] = new ArrayList<>();
    }
    for(int i=0;i<words.length;i++){
      for(int j=i+1;j<words.length;j++){
        if(isNbr(words[i],words[j])){
          graph[i].add(j);
          indegree[j]++;
        }
      }
    }

  }
  public boolean isNbr(String w1,String w2){
    if(w1.length()!=w2.length()-1){
      return false;
    }
    int diff = 0;
    for(int i=0,j=0;i<w1.length();i++,j++){
      if(w1.charAt(i)!=w2.charAt(j)){
        diff++;
        if(diff>1)
          return false;
        i--;  //i doesn't change
      }
    }
    return true;
  }

  public void topo(){
    Queue<Integer> queue = new LinkedList<>();
    for(int i=0;i<wLen;i++){
      if(indegree[i]==0){
        queue.offer(i);
        maxLastWord = i;
        maxLen = 1;
      }
    }
    //path length ending with index i, find path by <child,parent> map. map stores last one reach the index i.
    int[] pathL = new int[wLen];
    int depth = 0;
    while(!queue.isEmpty()){
      int size = queue.size();
      while(size-->0){
        int cur = queue.poll();
        for(int nbr : graph[cur]){
          indegree[nbr]--;

          if(indegree[nbr]==0){
            queue.offer(nbr);
            childToP.put(nbr,cur);
            pathL[nbr] = depth+1;
            if(pathL[nbr]>maxLen){
              maxLen = pathL[nbr];
              maxLastWord = nbr;
            }
          }
        }
      }
      depth++;
    }

  }

  public List<String> printPath(String[] words){
    List<String> path = new LinkedList<>();
    int idx = maxLastWord;
    while(childToP.containsKey(idx)){
      path.add(0,words[idx]);
      idx = childToP.get(idx);
    }
    path.add(0,words[idx]);
    for(int i=0;i<path.size();i++){
      System.out.print(path.get(i)+" ");
    }
    System.out.println();
    return path;
  }

  public static void main(String[] args) {
    String[] words = {"bad", "ad", "ccc", "d", "bacd","ba","bac"};
    LongestPathDAG ins = new LongestPathDAG();
    ins.findLongestPath(words);
  }

}
