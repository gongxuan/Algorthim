package Google.Array;
//第二题是https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
// 然后follow-up是如果变成是同时找三个这样的non-overlapping subarray。follow-up没来得及做出来下一个面试官就来了，
// 面试的小哥还鼓励我说没关系已经很好了，人很nice了。

public class LargestSumContigousSubArray {
  public int func(int[] A){
    //neg? given? all neg? result len could be 0 or1?
    //Time: O(N),
    //for every number in A, two options:
    //include it in prevSum
    //exclude it: because this number will cause prevSum become smallest(this num is too small)
    int maxSum = Integer.MIN_VALUE;
    int maxNum = Integer.MIN_VALUE;
    int curSum = 0;
    for(int i=0;i<A.length;i++){
      curSum += A[i];
      maxNum = Math.max(maxNum,A[i]); //in case of all neg numbers
      if(curSum>=0){
        maxSum = Math.max(curSum,maxSum);
      }else{
        curSum = 0;
      }
    }
    if(maxNum<0){
      return maxNum;
    }
    return maxSum;
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

  public int split3(int[] A){
    //N2 to split  totally Time = N3
    return 0;
  }

  public static void main(String[] args) {
    LargestSumContigousSubArray ins = new LargestSumContigousSubArray();
    int[] A = {-1,-3,-5};
    int[] B = {1,3,-4,5,-7,4};
    System.out.println(ins.func(A)+" "+ins.maxSubArraySum(A));
    System.out.println(ins.func(B)+" "+ins.maxSubArraySum(B));

  }
}
