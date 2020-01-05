package Google.Graph.Bipartite;

import java.util.*;

//国人小哥。他貌似有点忙，一直在打字。题目是给一个log array， 格式是 log_id token1 token2，
// 比如 ["1,a,b", "2,b,c", "3,a,d", "4,g,h"，"5,g,k"], 任意两条log中，只要有相同的token就认为是同一个user产生的log
// 比如这个例子中是最少有两个user， 问题就是问最少有几个user。一开始说union-find，但是因为要n^2的time所以没让写code，
// 后来说用dfs，直接写了。其实这个题不难，但是我和小哥在题目理解上有点不一样，导致我一开始一直在想union-find，浪费了一点时间，
// 不过dfs很快写完，followup没有。
public class MinUserNum {
  //O(Tokens+LogIDs+E)  E=2*len of logs
  Map<String, Set<String>> graph;
  Set<String> tokens;
  public int minUsers(String[] logs){
    tokens = new HashSet<>();
    buildGraph(logs);

    int count = 0;
    while(!tokens.isEmpty()){
      Set<String> visited = new HashSet<>();
      String start = tokens.iterator().next();
      tokens.remove(start);
      visited.add(start);
      dfs(start,visited);
      count++;
    }
    return count;
  }
  public void buildGraph(String[] logs){
    //undirected graph
    graph = new HashMap<>();
    for(String log : logs){
      String[] arr = log.split(",");
      graph.computeIfAbsent(arr[0],v->new HashSet<>()).add(arr[1]);
      graph.get(arr[0]).add(arr[2]);
      graph.computeIfAbsent(arr[1],v->new HashSet<>()).add(arr[0]);
      graph.computeIfAbsent(arr[2],v->new HashSet<>()).add(arr[0]);
      tokens.add(arr[1]);
      tokens.add(arr[2]);
    }
  }
  public void dfs(String cur,Set<String> visited){
    if(!graph.containsKey(cur))
      return ;
    for(String nbr : graph.get(cur)){
      if(!visited.add(nbr)){
        continue;
      }
      tokens.remove(nbr);
      dfs(nbr,visited);
    }
  }

  public static void main(String[] args) {
    MinUserNum ins = new MinUserNum();
    String[] logs = {"1,a,b", "2,b,c", "3,a,d", "4,g,h","5,g,k"};
    int res = ins.minUsers(logs);
    System.out.println(res);
  }
}
