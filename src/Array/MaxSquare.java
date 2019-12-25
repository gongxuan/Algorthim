package Array;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=573287&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class MaxSquare {
//  亚裔小哥，也是高频题。给一个array of house prices，和一个budget，找出能买的连续的最长的房子是多少，
//  我讲了two pointer的思路，然后很快写了代码，然后讲了一下我的代码怎么handle一些corner cases。
//  followup是把array换成matrix，找出能买的最大的square。我给的还是一个two pointer的方法，不过这个是two pointer along diagonal，
//  每个square的总cost可以用prefix sum O(1)算出来。另外的话这里的two point要从每个up edge 和 left edge 的元素开始。
//  最后的复杂度就是O((m + n) min(m, n))，可以近似为n^2的复杂度
  public static void main(String[] args) {
    //find max square which sum< K
    //simplify to 1D array: Sliding window
    //2D array: have two pointers left & right on cols, compute sum for every row and store in a 1D-array with length == rows
    //For now edge of square would be right-left+1, we can use fixed size of window to scan 1D-array,to check if there is a
    //valid window which sum<K,
    //Time: cols*cols*rows
    //solution2: prefixSum, DP cols^2 * row^2
    int[][] A = {{5,5,5,5},{5,1,1,1},{1,1,1,1},{5,5,5,5}};
    int[][] B = {{1,3,4,5},{5,4,1,3},{1,1,1,1},{2,2,2,2}};

    MaxSquare maxSquare = new MaxSquare();
    int K = 5;
    System.out.println(maxSquare.findMaxSquare(A,K));
    System.out.println(maxSquare.findMaxSquare(B,K));
  }
  public int findMaxSquare(int[][] A,int K){
    int rows = A.length;
    int cols = A[0].length;
    int maxEdge = 0;
    for(int left=0;left<cols;left++){
      int[] arr = new int[rows];
      for(int right=left;right<cols;right++){
        for(int r=0;r<rows;r++){
          arr[r] += A[r][right];
        }
        int curEdge = right-left+1;
        if(foundValidWindow(arr,K,curEdge)){
          maxEdge = Math.max(maxEdge,curEdge);
        }
      }
    }
    return maxEdge*maxEdge;
  }
  public boolean foundValidWindow(int[] arr,int K,int winSize){
    //find out if there is a valid window which sum<=K with fixed window size
    int low=0,hi=0;
    int sum = 0;
    while(hi<arr.length){
      sum += arr[hi];
      if(hi>=winSize-1){
        if(sum<=K){
          return true;
        }
        sum -= arr[low];
        low++;
      }
      hi++;
    }
    return false;
  }
}
