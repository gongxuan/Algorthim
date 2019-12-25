package G.Array;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=572790&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
//第二题是https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
//find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum.
// 然后follow-up是如果变成是同时找三个这样的non-overlapping sub-array。
// follow-up没来得及做出来下一个面试官就来了，面试的小哥还鼓励我说没关系已经很好了，人很nice了。
//问题不清楚啊

public class LargestSumContigousSubArraySplit3 {
  //brute force way is to split array to 3 partitions, N^2 * N, divide-and-conquer
  //prefix sum array. add all subarray sum with low/hi bound to maxheap, take the top one from heap. no it's not greedy.
  //pick top3 without conflict bounds. Time: O(N) + O(N^2lgN^2)
  //DP? dp[i][j][k] means max sum of largest sum of subarray of k partitions in subarray(0,i)
  //j<i, dp[i][j][k] = dp[j][
  //binary search? minimize largest sum for each partition?
  //Every time we guess a largest sum f, and verify if the array can be split to 3 partitions. Emm.... no.
  //largest sub-array sum is known. upper bound is global one. lower bound is sum up all neg or min positive

  public static void main(String[] args) {
    LargestSumContigousSubArraySplit3 ins = new LargestSumContigousSubArraySplit3();
    int[] A = {1,2,-1,2,6,4,-7,5,1,-1,2};
    int k = 3;
    int[] res = ins.maxSumOfThreeSubarrays(A,k);
    for(int i=0;i<3;i++){
      System.out.print(res[i]+" ");
    }
  }

  int maxSubArraySum(int a[])
  {
    int maxSum = Integer.MIN_VALUE, curMax = 0;
    int size = a.length;
    for (int i = 0; i < size; i++)
    {
      curMax = curMax + a[i];
      if (maxSum < curMax)
        maxSum = curMax;

      if (curMax < 0)
        curMax = 0;
    }
    return maxSum;
  }
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    //brute force N^3
    //use dp to store max sum of M subarrays for subarray[0,i]
    //pick last subarray, i-j+1=k, dp[i][m] = dp[j-1][m-1] + SUM(j,i) => j = i+1-k
    //pick prev subarray by skipping current element, dp[i][m] = dp[i-1][m]
    int len = nums.length;
    int[] prefixSum = new int[len];
    int sum = 0,idx = 0;
    for(int num : nums){
      sum += num;
      prefixSum[idx++] += sum;
    }
    int[][] dp = new int[len][4];   //dp[i][j] means max sum of m subarrays for subarray[0,i]
    //store dp path, last entry's start index which can get max sum of m entries in subarray[0,i]
    int[][] path = new int[len][4];
    for(int i=k-1;i<len;i++){
      for(int m=1;m<=3;m++){
        if(i>=k){
          int j = i-k+1;  //last subarray's start index
          dp[i][m] = dp[j-1][m-1] + prefixSum[i]-prefixSum[j-1];
          path[i][m] = j;
        }else{
          dp[i][m] = prefixSum[i];
          path[i][m] = 0;
        }
        if(i>=1 && dp[i][m]<=dp[i-1][m]){
          dp[i][m] = dp[i-1][m];
          path[i][m] = path[i-1][m];
        }

      }
    }

    int lastIdx = path[len-1][3];
    int secIdx = path[lastIdx-1][2];
    int firstIdx = path[secIdx-1][1];
    int[] res = new int[]{firstIdx,secIdx,lastIdx};
    return res;
  }
}
