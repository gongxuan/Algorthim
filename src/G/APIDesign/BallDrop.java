package G.APIDesign;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=570261&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class BallDrop {
//模拟小球从board中滑落，假设board由n*m个cell组成，每个cell有对角线或者反对角线的墙壁，
// 设计class模拟这个过程，class需要提供flip(row, col)方法翻转墙壁，dropBall(col)方法得出小球从顶端放入时从低端掉出的坐标(或者掉不出)。
// follow up 如果现场景dropBall()有million次的调用但是flip()调用很少该怎么优化
  public static void main(String[] args) {
    int n = 2,m = 2;
    Board b = new Board(n,m);
    b.board = new Cell[n][m];
    for(int i=0;i<n;i++){
      for(int j=0;j<m;j++){
        b.board[i][j] = new Cell(i,j,0);
      }
    }
    int[] res = b.dropBall(0);
    System.out.println(res[0]+" "+res[1]);
  }
}
class Cell{
  int r,c;
  int status; //0:diagonal 1:back-diagonal
  public Cell(int r,int c,int s){
    this.r = r;this.c = c;this.status = s;
  }
}

class Board{
  Cell[][] board;
  int rows,cols;
  public Board(int n,int m){
    rows = n;cols = m;
    board = new Cell[n][m];
  }
  public void flip(int r,int c){
    board[r][c].status = (board[r][c].status+1)%2;
  }
  public int[] dropBall(int c){
    return dfs(0,c);
  }
  public int[] dfs(int r,int c){
    //only one path
    if(r==rows-1){
      return new int[]{r,c};
    }
    if(isBlocked(r,c)){
      return new int[]{-1,-1};
    }
    if(board[r][c].status==0){
      return dfs(r+1,c+1);
    }else{
      return dfs(r+1,c-1);
    }
  }
  public boolean isBlocked(int r,int c){
    return (board[r][c].status==0 && (c+1>=cols||  board[r][c+1].status==1))
        || (board[r][c].status==1 && (c-1==0 ||  board[r][c-1].status==0));
  }
}
