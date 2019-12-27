package G.Graph;
import java.util.*;

public class ValidTree {
  //2. 给定一组有向边，组成一颗树，返回树的根结点。 Follow up: 如果无法保证一定能组成树，如何check validation
  //1) only one parent (Map)
  //2) no cycle (dfs or topo)
  //3) all nodes r connected(count nodes when searching cycle)
  //4) duplicate edges?
  //  Map<Integer,Integer> parent;  //child,parent
  int countNodes; //don't need count, connected graph only has one root!
  int total = 0;
  Map<Integer,Set<Integer>> graph;
  public boolean isValid(int[][] edges){
    countNodes = 0;
    graph = new HashMap<>();
    int root = findRoot(edges);
    if(root==-1){
      return false;
    }
    Set<Integer> visited = new HashSet<>();
    countNodes++;
    if(!dfs(root,visited)){
      return false;
    }
    return countNodes==total;
  }
  public int findRoot(int[][] edges){
    //indegree is zero
    Map<Integer,Integer> indegree = new HashMap<>();
    for(int[] e : edges){
      indegree.put(e[1],indegree.getOrDefault(e[1],0)+1);
      indegree.put(e[0],indegree.getOrDefault(e[0],0));
      if(graph.containsKey(e[0]) && graph.get(e[0]).contains(e[1])){
        return -1;  //duplicate edge
      }
      graph.computeIfAbsent(e[0],v->new HashSet<>()).add(e[1]);
    }
    int root=-1;
    int countRoots = 0;
    for(Map.Entry<Integer,Integer> e : indegree.entrySet()){
      if(e.getValue()==0){
        root= e.getKey();
        countRoots++;
        if(countRoots>1)
          return -1;
      }
    }
    total = indegree.size();
    return root;
  }
  public boolean dfs( int cur,Set<Integer> visited){
    if(!graph.containsKey(cur)){
      return true;
    }
    for(int nbr : graph.get(cur)){
      if(visited.contains(nbr)){  //cycle or two parents
        return false;
      }
      visited.add(nbr);
      countNodes++;
      if(!dfs(nbr,visited)){
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[][] edges  = {{0,1},{0,2},{1,3},{2,3}};
    ValidTree ins= new ValidTree();
//    System.out.println(ins.isValid(edges));
    edges = new int[][]{{0,1},{0,2},{1,3}};
//    System.out.println(ins.isValid(edges));
    edges = new int[][]{{0,1},{0,2},{1,3},{3,2}};
    System.out.println(ins.isValid(edges));
  }
}
