package Array;

import Google.Array.MaxSquare;

// https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=568531&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3088%255D%255Bvalue%255D%3D1%26searchoption%255B3088%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=1
public class MaxSizeSubarraySum {
  //find max size of sub array which sum <=k, all elements are non-negative!!
  static int rows;
  static int cols;
  public int maxSubArrayLen(int k, int[] nums) {
    //sliding window
    int sum = 0;
    int nLen = nums.length;
    int left = 0, right = 0;
    int maxSize = 0;
    for (; right < nLen; right++) {
      sum += nums[right];
      while (sum > k) {
        sum -= nums[left];
        left++;
      }
      maxSize = Math.min(maxSize, right - left + 1);
    }
    return maxSize;
  }
  public int maxSquareSum(int[][] A,int k){
    //  第三轮 - 国人哥们：（有点感慨谷歌Sunnyvale是不是快被国人占领了）
    //  lc #209的变体，给一个Integer array，找出最长的subarray使其元素之和小于或等于某一给定值k。确认了输入元素均为非负数后用sliding window扫一遍即可。
    //  我以为follow up会出包含负数的情况：#826，要用ArrayDeque做。结果他出了一道2D的：
    //
    //  给一个2D matrix，其中元素为非负整数。给一个整数k，找出matrix中最大正方形的面积，使得其中所有元素之和小于等于k。例如：
    //      5 5 5 5
    //      5 1 1 1
    //      1 1 1 1
    //      5 5 5 5, k = 6
    //  则返回4
    //  在他的提示下用了prefixSumMatrix，最后勉强写完了正确答案。
    //clairfy: Is single element a square?
    rows = A.length;
    cols = A[0].length;
    dpHelper(A);
    //binary search on each diagonal , time: ?
    int maxArea = 0;
    for(int r=0;r<rows;r++){
      for(int c=0;c<cols;c++){

        for(int r1=r,c1=c;r1>=0&&c1>=0;r1--,c1--){
          int sum = sumRegion(r1,c1,r,c);
          if(sum<=k){
            int area = (r-r1+1)*(c-c1+1);
            maxArea = Math.max(maxArea,area);
          }
        }

      }
    }
    return maxArea;
  }
  public void dpHelper(int[][] A){
    rows = A.length;
    cols = A[0].length;
    dp = new int[rows+1][cols+1]; //dp[i+1][j+1] means sum of rectangle from (0,0) to (i,j)
    for(int r=0;r<rows;r++){
      for(int c=0;c<cols;c++){
        dp[r+1][c+1] = dp[r][c+1] + dp[r+1][c] - dp[r][c] + A[r][c];
      }
    }
  }
  public int sumRegion(int row1, int col1, int row2, int col2) {
    return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
  }

  int[][] dp;
  public int maxRectangle(int[][] A,int k){
    //find max rectangle area which sum<=k
    //convert it to 1D-array,
    rows = A.length;
    cols = A[0].length;
    int maxSum = Integer.MIN_VALUE;
    for(int left=0;left<rows;left++){
      int[] arr = new int[cols];
      for(int right=left;right<rows;right++){

        for(int r=0;r<rows;r++){
            arr[r] += A[r][right];
        }
        //Kadane algorithm for pos+neg problem. sliding window for non-neg problem
        //Tree for no larger than K or no smaller than K problems
        maxSum = Math.max(maxSum,maxSubArrayLen(k,arr));
      }
    }
    return maxSum;

  }

  public static void main(String[] args) {
    MaxSizeSubarraySum ins = new MaxSizeSubarraySum();
    int[][] A = {{5,5,5,5},{5,1,1,1},{1,1,1,1},{5,5,5,5}};
    int[][] B = {{1,3,4,5},{5,4,1,3},{1,1,1,1},{2,2,2,2}};

    MaxSquare maxSquare = new MaxSquare();
    int K = 4;
    System.out.println(maxSquare.findMaxSquare(A,K)+" "+ins.maxSquareSum(A,K));

    System.out.println(maxSquare.findMaxSquare(B,K)+" "+ins.maxSquareSum(B,K));

  }

}
