package Google.Array;

//221. Maximal Square
//第四轮：给你一个m*n大小数组，只有0和1，问一共有多少个只包含1的正方形，比如3*3的数组，都是1，那就有9+4+1=14个
public class NumOf1Sqr2DMatrix {
  //time: col^2*rows
  public int numOf1Sqrs(int[][] M){
    int rows = M.length,cols=  M[0].length;
    int count = 0;
    for(int left=0;left<cols;left++){
      int[] arr = new int[rows];
      for(int right=left;right<cols;right++){
        for(int r=0;r<rows;r++){
          arr[r] += M[r][right];
        }
        count += numWin(arr,right-left+1);
      }
    }
    return count;
  }
  public int numWin(int[] arr,int winSize){
    //count number of windows which all values are winSize
    int left=0,right=0;
    int count = 0;
    int target = winSize;
    while(right<arr.length){
      if(arr[right]!=target){
        right++;
        left = right;
        continue;
      }
      if(right-left+1>=winSize){
        count++;
        left++;
      }
      right++;
    }
    return count;
  }

  public static void main(String[] args) {
    NumOf1Sqr2DMatrix ins = new NumOf1Sqr2DMatrix();
    int[] arr = {1,0,1,1,1,1,0,1,0,1,1,1};
    int[] arr1 = {1,1,1,1};
    int[] arr2 = {0,0,0};
//    System.out.println(ins.numWin(arr,2));
//    System.out.println(ins.numWin(arr1,5));
//    System.out.println(ins.numWin(arr2,2));
    int[][] M = {{1,1,1},{1,1,1}};
    System.out.println(ins.numOf1Sqrs(M)+" "+ins.dp(M));
    M = new int[][]{{1,0,1},{0,1,1}};
    System.out.println(ins.numOf1Sqrs(M)+" "+ins.dp(M));
    M = new int[][]{{0,0,0},{0,0,0}};
    System.out.println(ins.numOf1Sqrs(M)+" "+ins.dp(M));
  }

  public int dp(int[][] M){
    int rows = M.length,cols=  M[0].length;
    int count = 0;
    //sqr[i][j] means number of sqrs ending at position[i,j]

    for(int r=0;r<rows;r++){
      for(int c=0;c<cols;c++){
        if(M[r][c]==0)
          continue;
        if(r==0 || c==0){
          count++;
          continue;
        }
        int curMin = M[r-1][c];
        if(curMin>M[r][c-1]){
          curMin = M[r][c-1];
        }
        if(r>0 && c>0 && curMin>M[r-1][c-1]){
          curMin = M[r-1][c-1];
        }
        M[r][c] = curMin + 1;
        count += M[r][c];
      }
    }
    return count;
  }
}
