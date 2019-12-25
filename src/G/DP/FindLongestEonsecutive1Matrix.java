package G.DP;

public class FindLongestEonsecutive1Matrix {
//round 1
//Find longest consecutive 1 in matrix (only 0 and 1 in matrix)
//不是dfs那种绕弯的path 是横 竖 or 斜对角的直的length里找最长的
public int longestLine(int[][] M) {
  if(M==null || M.length==0||M[0].length==0){
    return 0;
  }
  int rows = M.length,cols = M[0].length;
  //h[r][c] means longest horizontal path ending at this pos
  int[][][] dp = new int[4][rows][cols];  //[h,v,d,d2]
  int max = 0;
  for(int r=0;r<rows;r++){
    if(M[r][0]==0)  continue;
    for(int i=0;i<4;i++){
      dp[i][r][0] = 1;
    }
    max = 1;
  }
  for(int c=0;c<cols;c++){
    if(M[0][c]==0)  continue;
    for(int i=0;i<4;i++){
      dp[i][0][c] = 1;
    }
    max = 1;
  }
  for(int r=0;r<rows;r++){
    for(int c=0;c<cols;c++){
      if(M[r][c]==0)  continue;

      if(c>0){
        dp[0][r][c] = dp[0][r][c-1]+1;
        max = Math.max(max,dp[0][r][c]);
      }
      if(r>0){
        dp[1][r][c] = dp[1][r-1][c]+1;
        max = Math.max(max,dp[1][r][c]);
      }
      if(c>0 && r>0){
        dp[2][r][c] = dp[2][r-1][c-1]+1;
        max = Math.max(max,dp[2][r][c]);
      }
      if(c+1<cols && r>0){
        dp[3][r][c] = dp[3][r-1][c+1]+1;
        max = Math.max(max,dp[3][r][c]);
      }
    }
  }
  return max;
}

}
