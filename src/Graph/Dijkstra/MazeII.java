package Graph.Dijkstra;

import java.util.*;

public class MazeII {
  static final int[][] DIRC = {{0,1},{0,-1},{1,0},{-1,0}};    //right, left, down ,up
  static int rows;
  static int cols;

  class Node{
    int r,c;
    int dirc;   //0:R 1:L 2:D 3:U
    int dis;
    public Node(int r,int c,int d,int dis){
      this.r = r;
      this.c= c;
      this.dirc = d;
      this.dis = dis;
    }
    public boolean equals(Object o){
      Node other = (Node)o;
      return r==other.r && c==other.c && dirc==other.dirc;
    }
    public int hashCode(){
      return 7*r+13*c+17*dirc;
    }
  }

  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    return bfs(maze,start,destination);
  }

  public int bfs(int[][] maze,int[] start,int[] desti){
    //Dijkstra Time complexity : O\big(mn*log(mn)\big)O(mn∗log(mn))
    if(maze==null || maze.length==0 || maze[0].length==0){
      return -1;
    }
    rows = maze.length;
    cols = maze[0].length;

    PriorityQueue<Node> queue = new PriorityQueue<>((a,b)->a.dis-b.dis);
    Set<Node> visited = new HashSet<>();
    for(int i=0;i<4;i++){
      Node startNode = new Node(start[0],start[1],i,0);
      visited.add(startNode);
      queue.offer(startNode);
    }

    int steps = 0;
    while(!queue.isEmpty()) {
      int size = queue.size();
      while(size-->0){
        Node cur = queue.poll();
        int nbrR = cur.r + DIRC[cur.dirc][0];
        int nbrC = cur.c + DIRC[cur.dirc][1];
        if(nbrR<0 || nbrR>=rows || nbrC<0 || nbrC>=cols || maze[nbrR][nbrC]==1){
          //stop
          if(cur.r==desti[0] && cur.c==desti[1]){
            System.out.println("steps:"+steps);
            return cur.dis;
          }

          //choose a new direc
          for(int i=0;i<4;i++){
            Node nxt = new Node(cur.r,cur.c,i,cur.dis);
            if(visited.contains(nxt))
              continue;

            visited.add(nxt);
            queue.offer(nxt);
          }
        }else{
          //keep rolling
          Node nbr = new Node(nbrR,nbrC,cur.dirc,cur.dis+1);//reuse current dirc
          if(visited.contains(nbr)){  //visited means didn't find destination
            continue;
          }
          visited.add(nbr);   //mark visited immediately
          queue.offer(nbr);
        }
      }
      steps++;
    }
    return -1;
  }

  public static void main(String[] args) {
    MazeII maze  =new MazeII();
    int[][] m = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
    int[] s = {0,4};
    int[] d = {1,3};
    System.out.println(maze.shortestDistance(m,s,d));
  }
}
