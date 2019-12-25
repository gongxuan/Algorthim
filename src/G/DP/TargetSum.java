package G.DP;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=571351&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class TargetSum {
//Round 1 coding: 输入一个数组nums和一个上限limit，输出一个同nums等长的0/1数组results。
// 要求为：如果能找到数组中的某些数之和为limit，则这些数对应在results的index位置为1，其余为0，输出results；其余为0；
// 若不能找到，则找到小于limit能拼出的最大和，并以同样的规则输出results。若有多个答案，输出任意合法results即可。(真的很难描述啊╮(￣▽￣)╭）
//例1：nums = [1, 2, 5, 2], limit = 4 => results = [0, 1, 0, 1]
//例2：nums = [1, 2, 5, 2], limit = 3 => results = [1, 1, 0, 0] or results = [1, 0, 0, 1]
  public int[] func(int[] nums,int limit){
    //knapsack problem
    //find a subset that sum is limit or largest sum smaller than limit
    //multiple result? sorted? range of limit and nums?
    //for every number i,
    //include it in subset when sum+i<=limit,
    //exclude it for current subset.
    //recursion tree:
    //                          [1, 2, 5, 2] T:4
    //        [2,5,2] T:3                                       [2,5,2] T:4
    //                                        [5,2] T:2                 [5,2] T:4
    //                                                [5,2] T:2\...
    //two changing values curIdx and limit
    int len = nums.length;
    boolean[][] dp = new boolean[len+1][limit+1]; //dp[i][s]= 1 means we can find a subset in subarray [0,i] that add up to s
    int[][] path = new int[len+1][limit+1];

    for(int i=0;i<=len;i++)
      dp[i][0] = true; //for every subarray, we can find a empty subset that add up to 0
    for(int i=0;i<len;i++){
      for(int s=0;s<=limit;s++){
        dp[i+1][s] = dp[i][s];  //exclude nums[i]
        if(dp[i+1][s]){
          path[i+1][s] = 0;
        }
        if(s>=nums[i]){
          dp[i+1][s] |= dp[i][s-nums[i]];
          if(dp[i][s-nums[i]]){
            path[i+1][s] = 1;
          }
        }

      }
    }

//    return findPath(nums,path,limit);
    return findPath2(nums,dp,limit);
  }

  public int[] findPath(int[] nums,int[][] path,int limit){
    int len = nums.length;
    int[] res = new int[len];
    int sum = limit;
    //find largest sum we can build
    while(path[len][sum]==-1){
      sum--;
    }
    int i = len;
    while(i>0){
      if(path[i][sum]==0){
        //exclude nums[i]
        res[i-1] = 0;
      }else{
        //include nums[i]
        res[i-1] = 1;
        sum = sum - nums[i-1];
      }
      i--;
    }
    return res;
  }
  public int[] findPath2(int[] nums,boolean[][] dp,int limit){
    int len = nums.length;
    int[] res = new int[len];
    int sum = limit;
    //find largest sum we can build
    while(dp[len][sum]==false){
      sum--;
    }
    int i = len;
    while(i>0){
      if(dp[i][sum]==dp[i-1][sum]){
        //exclude nums[i]
        res[i-1] = 0;
      }else{
        //include nums[i]
        res[i-1] = 1;
        sum = sum - nums[i-1];
      }
      i--;
    }
    return res;
  }

  public static void main(String[] args) {
    TargetSum ins = new TargetSum();
    int[] nums = {1,2,5,2};
    int limit = 4;
    int[] res = ins.func(nums,limit);
    for(int i=0;i<res.length;i++){
      System.out.print(res[i]+" ");
    }
    System.out.println();
    res = ins.func(nums,3);
    for(int i=0;i<res.length;i++){
      System.out.print(res[i]+" ");
    }
  }
}
