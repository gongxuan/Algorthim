package Graph.Topo;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=572485&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
import java.util.*;
//一个2d matrix，每行每列有数字，都不相同，然后让重新编辑，就是每个数字换成一个新的正整数，要求保持行列顺序，且用到的正整数尽可能小。
//比如
//[[6, 4, 10],
//  [3, 5, 20]]
//变成
//[[2, 1, 3],
//[1, 2, 4]]
//每一行每一列原来的大小关系不变，用的数字尽可能小
public class ReEditMatrix {
  int rows,cols;
  List<Integer>[] graph;
  int[] indegree;
  public int[][] convert(int[][] M){
    //duplicates?
    //topological sorting
    //Steps:
    //1. for every row and col, create a directed edge from smaller number to larger number
    //2. for every number in matrix, only if in-degree is zero, we can assgin a value to it.
    //3. for every number in same depth in topo, we assign same value to them.
    //The key is there are dependencies between numbers, we can't assign values to a number if its indegree is not zero.
    //Time: E: R2+C2  V:R*C  V+E, buildgraph R*C2 + CR2
    rows = M.length;
    cols = M[0].length;
    indegree = new int[rows*cols];
    graph = new List[rows*cols];
    buildGraph(M);
    return topo();
  }
  public int[][] topo(){
    int[][] res = new int[rows][cols];
    Queue<Node> queue = new LinkedList<>();
    int total = rows*cols;

    for(int i=0;i<total;i++){
      if(indegree[i]==0){
        queue.offer(new Node(i));
      }
    }
    int num = 1;
    while(!queue.isEmpty()){
      int size = queue.size();
      while(size-->0){
        Node cur = queue.poll();
        res[cur.idx/cols][cur.idx%cols] = num;
        for(int nbr : graph[cur.idx]){
          indegree[nbr]--;
          if(indegree[nbr]==0){
            queue.offer(new Node(nbr));
          }
        }
      }
      num++;
    }
    return res;
  }
  class Node{
    int idx;
    public Node(int i){
      idx = i;
    }
  }
  public void buildGraph(int[][] M){
    for(int i=0;i<rows*cols;i++){
      graph[i] = new ArrayList<>();
    }
    for(int r=0;r<rows;r++){
      for(int c1=0;c1<cols-1;c1++){
        for(int c2=c1+1;c2<cols;c2++){
          if(M[r][c1]<M[r][c2]){
            indegree[r*cols+c2]++;
            graph[r*cols+c1].add(r*cols+c2);
          }else if(M[r][c1]>M[r][c2]){
            indegree[r*cols+c1]++;
            graph[r*cols+c2].add(r*cols+c1);
          }
        }
      }
    }
    for(int c=0;c<cols;c++){
      for(int r1=0;r1<rows-1;r1++){
        for(int r2=r1+1;r2<rows;r2++){
          if(M[r1][c]<M[r2][c]){
            int idx = r2*cols+c;
            indegree[idx]++;
            graph[r1*cols+c].add(idx);
          }else if(M[r1][c]>M[r2][c]){
            int idx = r1*cols+c;
            indegree[idx]++;
            graph[r2*cols+c].add(idx);
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    ReEditMatrix ins = new ReEditMatrix();
    int[][] M = {{6,4,10},{3,5,20}};
    int[][] res = ins.convert(M);
    for(int r=0;r<res.length;r++){
      for(int c=0;c<res[0].length;c++){
        System.out.print(res[r][c]+" ");
      }
      System.out.println();
    }
  }

}
