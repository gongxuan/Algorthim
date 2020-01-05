package Google.DP;

public class CoinInaLine {
  // There are n coins with different value in a line. Two players take turns to take one or two coins
  // from left side until there are no more coins left. The player who take the coins with the most value wins.
  //Could you please decide the first player will win or lose?
  //If the first player wins, return true, otherwise return false.
  public static void main(String[] args) {
    CoinInaLine ins = new CoinInaLine();
    System.out.println(ins.canWin(new int[]{1,2,2}));
    System.out.println(ins.canWin(new int[]{1,2,4}));
    System.out.println(ins.canWin(new int[]{1,2,3}));
    System.out.println(ins.canWin(new int[]{1,2,1,3}));
  }
  public boolean canWin(int[] coins){
    int total = 0;
    for(int coin : coins){
      total += coin;
    }
    mem = new Integer[coins.length];
    int max = dfs(coins,0);
    return max>total/2;
  }
  Integer[] mem;
  public int dfs(int[] coins,int curIdx){
    if(curIdx>=coins.length){
      return 0;
    }
    if(mem[curIdx]!=null){
      return mem[curIdx];
    }
    //pick one
    int max = coins[curIdx] + Math.min(dfs(coins,curIdx+2),dfs(coins,curIdx+3));
    //pick two
    if(curIdx<coins.length-1){
      max = Math.max(max,coins[curIdx]+coins[curIdx+1] + Math.min(dfs(coins,curIdx+3),dfs(coins,curIdx+4)) );
    }
    mem[curIdx] = max;
    return max;
  }

}
