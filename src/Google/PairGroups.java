package Google;

import com.sun.tools.javac.util.Pair;
import java.util.*;


//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=573578&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
//第一轮：给一个vector<pair<string, string>>，一个pair中的两个string属于同一组，问一共有几组。答得不好。
public class PairGroups {
  //clarify: list length, string len? repeating pairs?
  //convert question to number of unconnected sub-graphs
  public static void main(String[] args) {
    PairGroups pairGroups = new PairGroups();
    List<Pair<String,String>> data = new ArrayList<>();
    data.add(new Pair<>("A","B"));
    data.add(new Pair<>("A","B"));
    data.add(new Pair<>("A","C"));
    data.add(new Pair<>("D","E"));
    System.out.println(pairGroups.countByUF(data) +" "+ pairGroups.countDFS(data));

  }
  public int countDFS(List<Pair<String,String>> data){
    //construct graph
    //dfs+ visited Time:O(V+E+N), N is list length
    Map<String,Set<String>> graph = new HashMap<>();
    for(Pair<String,String> pair:data){
      String str1 = pair.fst;
      String str2=  pair.snd;
      graph.computeIfAbsent(str1,v->new HashSet<>()).add(str2);
      graph.computeIfAbsent(str2,v->new HashSet<>()).add(str1);
    }
    Set<String> strNodes = graph.keySet();
    int count = 0;
    Set<String> visited = new HashSet<>();
    for(String str : strNodes){
      if(visited.contains(str)){
        continue;
      }
      visited.add(str);
      dfs(graph,str,visited);
      count++;
    }
    return count;
  }

  public void dfs(Map<String,Set<String>> graph,String cur,Set<String> visited){

    for(String nbr : graph.get(cur)){
      if(visited.contains(nbr)){
        continue;
      }
      visited.add(nbr);
      dfs(graph,nbr,visited);
    }
  }

  public int countByUF(List<Pair<String,String>> data){
    Set<String> set= new HashSet<>();
    for(Pair<String,String> pair:data){
      set.add(pair.fst);
      set.add(pair.snd);
    }
    //list of unique Strings
    Map<String,Integer> strIdx = new HashMap<>();
    int idx = 0;
    for(String str: set){
      strIdx.put(str,idx++);
    }
    UF uf = new UF(strIdx.size());
    for(Pair<String,String> pair:data){
      int idx1 = strIdx.get(pair.fst);
      int idx2 = strIdx.get(pair.snd);
      uf.union(idx1,idx2);
    }
    return uf.groups;
  }
  class UF{
    //NlgN if do path compression
    int N;
    public UF(int N){
      this.N = N;
      groups = N;
    }
    int groups;
    Map<Integer,Integer> ids = new HashMap<>(); //<index in strList,id>

    public int find(int a){
      if(!ids.containsKey(a)){
        ids.put(a,a);
        return a;
      }
      while(ids.get(a)!=a){
        a = ids.get(a);
      }
      return a;
    }
    public void union(int p,int q){
      int id1 = find(p);
      int id2 = find(q);
      if(id1!=id2){
        groups--;
        ids.put(id2,id1);
      }
    }
  }
}
