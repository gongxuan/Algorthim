package Graph.BellmanFord;

//一轮是给一个2darray求从左上走到右下的求最小cost的路径，里面有负数，可以上下左右走。
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=571356&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class MinCostPathMatrix {
  int rows,cols;
  static int[][] D = {{0,1},{1,0},{-1,0},{0,-1}};
  public int bellmanFord(int[][] M){
    rows = M.length;
    cols = M[0].length;
    Integer[][] dis = new Integer[rows][cols];
    dis[0][0] = M[0][0];
    //iterate all edges guarantees to give a shortest path with at most R*C-1 edges.
    //a simple path can contains at most R*C-1 edges.
    for(int r=0;r<rows;r++){
      for(int c=0;c<cols;c++){
        for(int i=0;i<4;i++){
          int nr = r + D[i][0];
          int nc = c + D[i][1];
          if(nr<0 || nr>=rows || nc<0 || nc>=cols )
            continue;
          if(dis[r][c]!=null &&(dis[nr][nc]==null || dis[r][c]+M[nr][nc] <dis[nr][nc] )){
            dis[nr][nc] = dis[r][c]+M[nr][nc];
          }
        }
      }
    }

    for(int r=0;r<rows;r++){
      for(int c=0;c<cols;c++){
        for(int i=0;i<4;i++){
          int nr = r + D[i][0];
          int nc = c + D[i][1];
          if(nr<0 || nr>=rows || nc<0 || nc>=cols )
            continue;
          if(dis[r][c]!=null && dis[r][c]+M[nr][nc] <dis[nr][nc]){
            System.out.print("negative loop, ");
            return 0;
          }
        }
      }
    }
    return dis[rows-1][cols-1];
  }

  public static void main(String[] args) {
    //1,2,3
    //-1,5,1
    //1,1,1
    int[][] M = {{1,2,3},{-1,5,1},{1,1,1}};
    int[][] M1 = {{1,2,3},{-1,5,1},{0,1,1}};
    MinCostPathMatrix ins = new MinCostPathMatrix();
    System.out.println(ins.bellmanFord(M));
    System.out.println(ins.bellmanFord(M1));
  }
}
