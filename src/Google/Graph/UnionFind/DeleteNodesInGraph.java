package Google.Graph.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteNodesInGraph {
  // 给出一个图G，以及点集V，将V中的点从G里移除。如果G中的任意两点移除前是连通的，移除后变得不连通，则加上一条边将它俩重新相连。返回修复后的图。
  //eg: A-B,A-C,A-D, we need to add at least 3 edges between B,C,D
  //directed or undirected?
  public static void main(String[] args) {
    DeleteNodesInGraph ins = new DeleteNodesInGraph();
    int N = 4;
    List<Integer>[] graph = new List[N];
    for(int i=0;i<N;i++){
      graph[i] = new ArrayList<>();
    }
    graph[0].addAll(Arrays.asList(new Integer[]{1,2,3}));
    graph[1].add(0);graph[2].add(0);graph[3].add(0);
    int[] delete = {0};
    ins.fixGraphAfterDeletion(graph,delete);
    System.out.println();
  }
  public void fixGraphAfterDeletion(List<Integer>[] graph,int[] delete){
    int N = graph.length;
    UF uf = new UF(N);
    uf.build(graph);
    for(int node : delete){
      if(!graph[node].isEmpty())
        graph[graph[node].get(0)].remove(node);
      for(int i=1;i<graph[node].size();i++){
        int prev = graph[node].get(i-1);
        int cur = graph[node].get(i);
        uf.union(prev,cur,graph);
        graph[cur].remove(node);

      }
      graph[node].clear();
    }
  }

}
class UF{
  int[] ids;
  int N;
  public UF(int N){
    this.N = N;
    ids = new int[N];
    for(int i=0;i<N;i++){
      ids[i] = i;
    }
  }
  public int find(int i){
    while(i!=ids[i]){
      ids[i] = ids[ids[i]];
      i = ids[i];
    }
    return i;
  }
  public void union(int a,int b,List<Integer>[] graph){
    int id1 = find(a);
    int id2 = find(b);
    //add a edge
    graph[a].add(b);
    graph[b].add(a);
  }
  public void build(List<Integer>[] graph){
    for(int i=0;i<N;i++){
      for(int nbr : graph[i]){
        ids[nbr] = i; //union
      }
    }
  }

}
