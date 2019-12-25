package DP;

import java.util.Arrays;

public class LongestPalindromicSubstring {
  public String longestPalindrome(String s) {
    //claim multiple results? char range? s range?
    //similar wtih longest palindromic subsequence
    int sLen = s.length();
    if(sLen==0){
      return "";
    }
    boolean[][] dp = new boolean[sLen][sLen];   //dp[i][j] is true means substring(i,j) is palindrom
    int resL = 0,resR = 0;
    int max = 1;
    for(int i=0;i<sLen;i++){
      Arrays.fill(dp[i],true);
    }
    for(int len=2;len<=sLen;len++){
      for(int l=0;l<=sLen-len;l++){
        int r = l + len - 1;
        if(s.charAt(l)==s.charAt(r)){
          dp[l][r] = dp[l+1][r-1];
          if(dp[l][r] && len>max){
            resL = l;
            resR = r;
            max = len;
          }
        }else{
          dp[l][r] = false;
        }

      }
    }
    return s.substring(resL,resR+1);
  }

  public static void main(String[] args) {
    LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
    System.out.println(lps.longestPalindrome("abacab"));
  }
}
