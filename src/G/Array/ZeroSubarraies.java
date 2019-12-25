package G.Array;
//第四轮：
//    国人姐姐，人巨好，疯狂引导，鼓励我找规律，亲切得让我想起了本科上过课的助教。
//    给一个由0，1组成的array；找到只有0的subarraies的数目。比如[0,0] 就是2，[0,0,1,0,0]
//    就是6，用DP做。followup是array 变成 matrix， 让说下思路，我一时无语凝噎，执手相看泪眼。
//    只好说了每行找一遍，然后找矩形个数之类的，姐姐说思路对了，也不知道是不是安慰我。估计最后也看我可怜，没叫我写，让说了下时间复杂度。
//    （后来在领英上查到居然是本科校友，不管feedback如何我都想给人美心善的姐姐献上洁白的哈达，这轮体验最亲切了）
public class ZeroSubarraies {

  public static void main(String[] args) {
    ZeroSubarraies ins = new ZeroSubarraies();
//    int[] A = {0,0,1,0,0};
//    int[] B = {1};
//    int[] C = {0,0,0,0,0};
//    int[] X = {0,0,0};
//    System.out.println(ins.count(A));
//    System.out.println(ins.count(B));
//    System.out.println(ins.count(C));
//    System.out.println(ins.count(X));
    int[][] D = {{0,0,0},{0,1,1},{1,0,1},{0,0,0},{0,0,0}};

    int[][] E = {{0,0},{0,0},{0,0}};
    System.out.println(ins.count2D(D));
    System.out.println(ins.count2D(E));
    int[][] F = {{1,0,1},{0,0,0},{0,0,0}};
    int[][] G = {{1,1,1},{1,1,1},{1,1,1}};
    System.out.println("F:"+ins.count2D(F));
    System.out.println("G:"+ins.count2D(G));
  }
  public int count(int[] A){
    //in mem? length of A?
    int len = A.length;
    int countZero = 0;
    int[] dp = new int[len];  //dp[i+1] denotes number of zero subarries end with A[i]
    for(int i=0;i<len;i++){
      if(A[i]!=0){  //be careful!! for 2D!!!
        dp[i] = 0;
        continue;
      }
      if(i>0 && A[i-1]==0){
        dp[i] += dp[i-1];
      }
      dp[i]++;  //self
      countZero += dp[i];
    }
    return countZero;
  }

  public long count2D(int[][] A){
    int rows = A.length;
    int cols = A[0].length;
    long count = 0;
    for(int left=0;left<cols;left++){
      int[] arr = new int[rows];
      for(int right=left;right<cols;right++){

        for(int r=0;r<rows;r++){
          arr[r] += A[r][right];
        }
        count += count(arr);

      }
    }
    return count;
  }
}
