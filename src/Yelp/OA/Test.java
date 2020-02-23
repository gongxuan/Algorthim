package Yelp.OA;

import java.util.*;

public class Test {

  public static void main(String[] args) {
    List<List<Integer>> grid = new ArrayList<>();
    grid.add(Arrays.asList(new Integer[]{1,0,0}));
    grid.add(Arrays.asList(new Integer[]{0,1,0}));
    grid.add(Arrays.asList(new Integer[]{0,0,1}));
    Test ins = new Test();
    System.out.println(ins.mininumDays(3,3,grid));
    grid = new ArrayList<>();
    grid.add(Arrays.asList(new Integer[]{0,1,1,0,1}));
    grid.add(Arrays.asList(new Integer[]{0,1,0,1,0}));
    grid.add(Arrays.asList(new Integer[]{0,0,0,0,1}));
    grid.add(Arrays.asList(new Integer[]{0,1,0,0,0}));
    System.out.println(ins.mininumDays(4,5,grid));
    grid = new ArrayList<>();
    grid.add(Arrays.asList(new Integer[]{0,0,1,0,0,0}));
    grid.add(Arrays.asList(new Integer[]{0,0,0,0,0,0}));
    grid.add(Arrays.asList(new Integer[]{0,0,0,0,0,1}));
    grid.add(Arrays.asList(new Integer[]{0,0,0,0,0,0}));
    grid.add(Arrays.asList(new Integer[]{0,1,0,0,0,0}));
    System.out.println(ins.mininumDays(5,6,grid));
  }

  static final int[][] DIRC = {{1,0},{0,1},{0,-1},{-1,0}};
  public int mininumDays(int rows,int columns,List<List<Integer>> grid){
    if(grid==null || grid.size()==0 || grid.get(0).size()==0 || rows<=0 || columns<=0){
      return -1;
    }
    //do BFS starting from all updated servers
    Queue<Node> queue = new LinkedList<>();
    //add all updated servers to the queue
    for(int r=0;r<rows;r++){
      for(int c=0;c<columns;c++){
        if(grid.get(r).get(c)==1){
          queue.add(new Node(r,c));
        }
      }
    }
    if(queue.isEmpty()){
      //all servers r out of date
      return -1;
    }
    //BFS
    int minDays = 0;
    while(!queue.isEmpty()){
      int depthSize = queue.size();
      while(depthSize-->0){
        Node curr = queue.poll();
        for(int i=0;i<4;i++){
          int nextR = DIRC[i][0] + curr.r;
          int nextC = DIRC[i][1] + curr.c;
          if(nextR<0 || nextR>=rows || nextC<0 || nextC>=columns
              || grid.get(nextR).get(nextC)!=0){
            //out of boundary or visited
            continue;
          }
          queue.offer(new Node(nextR,nextC));
          grid.get(nextR).set(nextC,1); //update the server
        }
      }
      minDays++;
    }
    minDays--;
    return minDays;
  }
  class Node{
    int r,c;
    public Node(int r,int c){
      this.r = r;
      this.c = c;
    }
  }
}
