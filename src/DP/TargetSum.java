package DP;

public class TargetSum {
//给一个N，问有多少方法得到用正整数加和方式，不考虑顺序，
// 5 = 1 + 4 = 2 + 3 = 1 + 1 + 3 = 1 + 2 + 2 = 1 + 1 + 1 + 1 + 1 =  1 + 1 + 1 + 2
  //unbounded knapsack problem.
  int N;
  public int totalWays(int N){
    this.N = N;
    return dfs(N,1);
  }
  public int dfs(int total,int curNum){
    if(total==0){
      return 1;
    }
    if(curNum>=N){
      return 0;
    }
    int sum = 0;
    //including item, recursively process all numbers, including current number
    if(curNum<=total)
      sum += dfs(total-curNum,curNum);
    //exclude item,
    sum += dfs(total,curNum+1);
    return sum;
  }

  public static void main(String[] args) {
    TargetSum ins = new TargetSum();
    System.out.println(ins.totalWays(5));
  }
}
