package G.DP;

import java.util.Stack;

public class ProductionLine {
// 给两个数组Rev1 Rev2，表示在Rev1, Rev2 表示在i天能获得的价值 只能二选一， 可以任意从Rev1和Rev2开始，
// 但是如果前一天在1，第二天到了2就会有一些travel cost 然后这个cost一开始是常数. 问总共N天能拿到的最大价值，

  public int maxProfit(int[][] R,int cost){
    //profit pos? cost >0, len same?
    mem = new Integer[2][R[0].length];
    return Math.max(dfs(R,0,0,cost),dfs(R,0,1,cost));
  }
  Integer[][] mem;
  public int dfs(int[][] R,int curIdx,int line,int cost){
    if(curIdx==R[line].length){
      return 0;
    }
    if(mem[line][curIdx]!=null){
      return mem[line][curIdx];
    }
    int maxProfit =  0;
    maxProfit = dfs(R,curIdx+1,line,cost)+R[line][curIdx];
    maxProfit = Math.max(maxProfit,dfs(R,curIdx+1,(line+1)%2,cost)+R[line][curIdx]-cost);
    mem[line][curIdx] = maxProfit;
    return maxProfit;
  }

  public int bottomUpDP(int[][] R,int cost){
    int[] dp = new int[2]; //dp[0][i+1] means max profit end at line 0 and index i
    int len = R[0].length;
    int[][] path = new int[2][len+1];
    for(int i=0;i<len;i++){
      int temp0 = Math.max(dp[0],dp[1]-cost) + R[0][i];
      if(temp0==dp[0]+ R[0][i]){
        path[0][i] = 0;
      }else{
        path[0][i] = 1;
      }
      int temp1 = Math.max(dp[1],dp[0]-cost) + R[1][i];
      if(temp1==dp[1]+R[1][i]){
        path[1][i] = 1;
      }else{
        path[1][i] = 0;
      }
      dp[0] = temp0;
      dp[1] = temp1;
    }
    int res =  Math.max(dp[0],dp[1]);
    printPath(R,path,res==dp[0]?0:1);
    return res;
  }
  public int bottomUpDP1(int[][] R,int cost){
    int len = R[0].length;
    int[][] dp = new int[2][len+1]; //dp[0][i+1] means max profit end at line 0 and index i
    for(int i=0;i<len;i++){
      dp[0][i+1] = Math.max(dp[0][i],dp[1][i]-cost) + R[0][i];
      dp[1][i+1] = Math.max(dp[1][i+1],dp[0][i+1]-cost) + R[1][i];
    }
    return Math.max(dp[0][len],dp[1][len]);
  }
  public void printPath(int[][] R,int[][] path,int line){
    int len = path[0].length;
    System.out.println("");
    System.out.print("Path:");
    Stack stack = new Stack();
    for(int i=len-1;i>0;i--){
      stack.push(R[line][i-1]);
       line = path[line][i-1];
    }
    while(!stack.isEmpty()){
      System.out.print(stack.pop()+" ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[][] R = {{1,0,3,7},{2,1,1,3}};
    ProductionLine ins =  new ProductionLine();
    int cost = 1;
    System.out.println(ins.maxProfit(R,cost)+" "+ ins.bottomUpDP(R,cost));
    R = new int[][]{{1,0,4,1},{2,1,1,3}};
    System.out.println(ins.maxProfit(R,cost)+" "+ ins.bottomUpDP(R,cost));
  }
}
