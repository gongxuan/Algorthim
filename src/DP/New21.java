package DP;

public class New21 {
  public static double new21Game(int N, int K, int W) {
    //dp[i] denotes how many ways to get i pointes
    int[] dp = new int[W+K];
    dp[0] = 1;
//    System.out.println("W:"+W+" K:"+K+" N:"+ N);
    for(int i=1;i<=W+K-1;i++){
      for(int j=1;j<=W && j<=i;j++){
//        System.out.println("i:"+i+" j:"+j+" i-j:"+ (i-j));
        if(i-j<K)
          dp[i] += dp[i-j];
      }

    }

    int sum = 0;
    int total = 0;
    for(int point=K;point<=N;point++){
      sum += dp[point];
    }
    total += sum;
    for(int point=N+1;point<=W+K-1;point++){
      total += dp[point];
    }
     for(int point=0;point<=W+K-1;point++){
         System.out.print(dp[point]+" ");
     }
    return (double)sum/(double)total;
  }

  public static void main(String[] args) {
    System.out.println(New21.new21Game(10,1,10));
  }
}
