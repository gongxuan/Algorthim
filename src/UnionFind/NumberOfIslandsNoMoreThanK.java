package UnionFind;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=574829&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
//很白的印度人，考了一个union find的题， 大概是一个下棋的游戏，二维board，给一个K，是number of islands的上限，
// 一个棋子下下去如果超过了K就算invalid，写一个validate function，可调用多次。
public class NumberOfIslandsNoMoreThanK {
  //Clarify: how's grid given? in mem? If valid,add new point to grid?multi-thread? diagonal cells adjacent?
  //what if existing grid already have more than k islands? how big is this grid?
  //valid method: given a invalid input? given a island position? given a water position?

  int[] ids;
  int rows;
  int cols;
  int islandNum;  //island number
  int K;
  static final int[] DIRC_X = {0,1,-1,0};
  static final int[] DIRC_Y = {1,0,0,-1};
  int[][] grid;
  public NumberOfIslandsNoMoreThanK(int[][] grid, int k){
    rows = grid.length;
    cols = grid[0].length;
    ids = new int[rows*cols];
    this.grid = grid;
    K = k;

    islandNum = 0;
    for(int r=0;r<grid.length;r++){
      for(int c=0;c<grid[0].length;c++){
        if(grid[r][c] ==1){
          ids[r*cols+c] = r*cols+c;
          islandNum++;
        }
      }
    }
    for(int r=0;r<grid.length;r++){
      for(int c=0;c<grid[0].length;c++){
        if(grid[r][c] ==1){
          for(int i=0;i<4;i++){
            int nxtR = DIRC_X[i] + r;
            int nxtC = DIRC_Y[i] + c;
            if(nxtR<0 || nxtR>=rows|| nxtC<0 || nxtC>=cols){
              continue;
            }
            if(grid[nxtR][nxtC]==1){
              union(nxtR*cols+nxtC,r*cols+c);
            }
          }
        }
      }
    }

  }
  public boolean valid(int[] point){
    int r = point[0],c= point[1];
    if(r<0||r>=rows || c<0 || c>=cols){
//      throw new IllegalArgumentException("invalid point");
      return false;
    }
    if(grid[r][c]==0){
      ids[r*cols+c] = r*cols+c;
      islandNum++;
    }
    for(int i=0;i<4;i++){
      int nxtR = DIRC_X[i] + r;
      int nxtC = DIRC_Y[i] + c;
      if(nxtR<0 || nxtR>=rows || nxtC<0 || nxtC>=cols){
        continue;
      }
      if(grid[nxtR][nxtC]==1){
        union(nxtR*cols+nxtC,r*cols+c);
      }
    }
//    System.out.println(islandNum);
    return islandNum<=K;
  }

  public int find(int p){
    while(p!=ids[p]){
      ids[p] = ids[ids[p]];
      p = ids[p];
    }
    return p;
  }
  public void union(int node1,int node2){
    int id1 = find(node1);
    int id2 = find(node2);
    if(id1!=id2){
      islandNum--;
      ids[id2] = id1;
    }
  }

  public static void main(String[] args) {
    int[][] grid = {{1,1,1,0,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}};
    NumberOfIslandsNoMoreThanK ins = new NumberOfIslandsNoMoreThanK(grid,1);
    System.out.println(ins.valid(new int[]{-1,-1}));
//    System.out.println(ins.valid(new int[]{0,3}));
    System.out.println(ins.valid(new int[]{0,0}));
  }
}
