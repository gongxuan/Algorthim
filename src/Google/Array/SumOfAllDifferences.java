package Google.Array;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=572790&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
import java.util.*;

//1. coding: 问了两题。第一题是sum of all differences 给一个list of unsorted Integers，
// 两两结对求差的绝对值，返回所有这样的差的和。。
public class SumOfAllDifferences {
  //unsorted? length ? given ? negative?
  public static void main(String[] args) {
    int[] A = {1,4,6,7,11};
    int[] B = {1,4,6,7};
    int[] C = {1,1,1,1,1,1};
    int[] D = {};
    Arrays.sort(A);
    SumOfAllDifferences ins = new SumOfAllDifferences();
    System.out.println(ins.dpSolution(A) +" "+ins.solution2(A));
    System.out.println(ins.dpSolution(B) +" "+ins.solution2(B));
    System.out.println(ins.dpSolution(C) +" "+ins.solution2(C));
    System.out.println(ins.dpSolution(C) +" "+ins.solution2(D));
  }
  public int dpSolution(int[] A){
    //dp[i] means sum of all diff for subarray(0,i)
    //dp[i] = dp[i-1] + A[i]*i-prefixSum
    int prefixSum = 0;
    int dp = 0;
    for(int i=0;i<A.length;i++){
      dp += A[i]*i - prefixSum;
      prefixSum += A[i];
    }
    return dp;
  }
  public int solution2(int[] A){
    //A[i] will be used by two ways:
    //1) A[i] - A[j] ,j<i
    //2) A[k] - A[i] ,k>i
    //so A[i] will happen i times
    //-A[i] will happen N-1-i times
    //Time: O(N)
    int len = A.length;
    int sum = 0;
    for(int i=0;i<len;i++){
      sum += A[i]*i - A[i]*(len-1-i);
    }
    return sum;
  }
}
