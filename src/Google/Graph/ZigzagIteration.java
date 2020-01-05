package Google.Graph;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=569643&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
import java.util.*;

//Input：
//1   2   6    7
//3   5   8   13
//4   9  12  14
//10 11   15  16
//
//output：
//[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]

public class ZigzagIteration {
  static int[][] D = {{1,0},{0,1}};
  static int[][] D2 = {{0,1},{1,0}};
  public int[] iterator(int[][] grid){
    Stack<Node> stack = new Stack<>();
    int rows = grid.length;
    int cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];
    stack.push(new Node(0,0));
    int[] res = new int[rows*cols];
    int idx = 0;
    visited[0][0] = true;
    boolean isEvenLayer = true;
    res[idx++] = grid[0][0];
    while(!stack.isEmpty()){
      int size = stack.size();
      Stack<Node> nxt = new Stack<>();
      while(size-->0){
        Node cur = stack.pop();
        for(int i=0;i<2;i++){
          int nbrR = cur.r + (isEvenLayer?D2[i][0]:D[i][0]);
          int nbrC = cur.c + (isEvenLayer?D2[i][1]:D[i][1]);
          if(nbrR<0 || nbrR>=rows || nbrC<0 || nbrC>=cols || visited[nbrR][nbrC]){
            continue;
          }
          visited[nbrR][nbrC] = true;
          res[idx++] = grid[nbrR][nbrC];
          nxt.push(new Node(nbrR,nbrC));
        }
      }
      stack = nxt;
    }
    return res;
  }
  public void print(int[] res){
    for(int num:res){
      System.out.print(num+" ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    ZigzagIteration ins = new ZigzagIteration();
    int[][] grid = {{1,2,6,7},{3,5,8,13},{4,9,12,14},{10,11,15,16}};
    int[] res = ins.iterator(grid);
    ins.print(res);
  }
}
class Node{
  int r,c;
  public Node(int r,int c){
    this.r = r;this.c = c;
  }
}
