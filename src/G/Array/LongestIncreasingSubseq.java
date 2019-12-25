package G.Array;

import java.util.Arrays;

public class LongestIncreasingSubseq {

  //354.Russian Doll Envelopes
  public int maxEnvelopes(int[][] E) {
    if (E == null || E.length == 0) {
      return 0;
    }
    //sort by length, if same sort by width
    Arrays.sort(E, (a, b) -> a[0] == b[0] ? b[1] - a[1]: a[0] - b[0]);
    //find longest increasing subsequence
    int len = E.length;
    int count = 0;
    int[] dp = new int[len];    //store longest increasing subsequence
    int lisLen = 0;
    for (int i = 0; i < len; i++) {

      //replace left-most number in lis where value is greater than it.
      int idx = Arrays.binarySearch(dp, 0, lisLen, E[i][1]);
      if (idx < 0) {  //current width is greater than all widths in subsequence
        //insert to the end of lis
        idx = -(idx + 1);
      }
      dp[idx] = E[i][1];
      if (idx == lisLen) {
        lisLen++;
      }
    }
    for(int i=0;i<lisLen;i++){
      System.out.print(dp[i]+" ");
    }
    System.out.println();
    return lisLen;
  }

  public static void main(String[] args) {
    LongestIncreasingSubseq ins = new LongestIncreasingSubseq();
    int[][] E = {{2,3},{5,4},{6,4},{6,5},{6,7},{7,1}};
    System.out.println(ins.maxEnvelopes(E));
  }
}
