package Google.DP;
//https://www.1point3acres.com/bbs/thread-572862-1-1.html
public class PickItemFromTwoEnds {

  //第一轮，给一个数组list，每次从最左或者最右取一个数字，总共取k个，求这k个数的最大值
//follow up是再给定另外一个长度为k的数组，每次取的数字与数组的对应值相乘，求最大值。
  public static void main(String[] args) {
    PickItemFromTwoEnds ins = new PickItemFromTwoEnds();
    int[] arr = {0,1,5,2,1};

    System.out.println(ins.maxNum(arr,2));
  }
  int[] product = {1,2,3,2,1};
  public int maxNum(int[] arr,int k) {
    mem = new Integer[arr.length][arr.length][k+1];
    return dfs(arr, 0, arr.length - 1,k);
  }

  Integer[][][] mem;

  public int dfs(int[] arr, int left, int right,int k) {
    if (left > right || k==0) {
      return Integer.MIN_VALUE;
    }
    if (mem[left][right][k] != null) {
      return mem[left][right][k];
    }
    int max = Integer.MIN_VALUE;
    max = Math.max(arr[left]*product[left], dfs(arr, left + 1, right,k-1));
    max = Math.max(arr[right]*product[right], max);
    max = Math.max(max, dfs(arr, left, right - 1,k-1));
    mem[left][right][k] = max;
    return max;
  }
}
