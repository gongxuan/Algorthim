package G.Graph;

import java.util.*;

public class Ant {
//直角坐标系上面： y=1, y=0 这两条直线之间相夹的面 表示一条路，然后给我很多边平行于x轴 y轴的长方形，可以overlap，
// 问这条路有没有被block住， 没有block定义为：假设有一个无限小的蚂蚁从(-inf，0.5)点出发 不能穿越和经过y=0， y=1的直线，
// 也不能接触任意长方形的边或顶点， 能否爬道（inf, 0.5)点
  List<Integer>[] graph;
  int len;
  int line1 = 0,line2=10;
  public boolean canPass(int[][] recs){
    //assume recs are parallel to the coordinate axis.
    //transform problem to find a path from line1 to line2, bfs, dfs or uf, undirected graph
    len = recs.length;
    graph = new List[len];
    buildGraph(recs);
    List<Integer> starts = findStart(recs);
    Set<Integer> visited = new HashSet<>();
    for(int start :starts){
      visited.add(start);
      if(dfs(recs,start,visited)){
        return true;
      }
    }
    return false;
  }
  public void buildGraph(int[][] recs){

    for(int i=0;i<len;i++){
      graph[i] = new ArrayList<>();
    }
    for(int i=0;i<len;i++){
      for(int j=i+1;j<len;j++){
        if(isConnected(recs[i],recs[j]) || isConnected(recs[j],recs[i])){
          graph[i].add(j);
          graph[j].add(i);
        }
      }
    }
  }
  public boolean isConnected(int[] rec1,int[] rec2){
    //check 4 vertices if it's in other rec
    for(int i=0;i<=2;i+=2){
      for(int j=1;j<=3;j+=2){
        int r = rec1[i],c = rec1[j];
        if(rec2[0]<=r && rec2[2]>=r && c>=rec2[1] && c<=rec2[3]){
          return true;
        }
      }
    }
    return false;
  }
  public List<Integer> findStart(int[][] recs){
    //find all recs that overlap with line1
    List<Integer> list = new ArrayList<>();
    for(int i=0;i<recs.length;i++){
      int[] rec = recs[i];
      if(connectToLine(rec,line1)){
        list.add(i);
      }
    }
    return list;
  }
  public boolean connectToLine(int[] rec,int c){
    return rec[1]<=c && rec[3]>=c;
  }
  public boolean dfs(int[][] recs,int start,Set<Integer> visited){
    if(connectToLine(recs[start],line2)){
      return true;
    }
    for(int nbr : graph[start]){
      if(visited.contains(nbr)) continue;
      visited.add(nbr);
      if(dfs(recs,nbr,visited)){
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[][] recs = {{0,0,1,1},{1,1,10,10}};
    Ant ant = new Ant();
    System.out.println(ant.canPass(recs));
  }

}
