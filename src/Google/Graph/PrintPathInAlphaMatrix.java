package Google.Graph;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=567634&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class PrintPathInAlphaMatrix {
//算法2 26字母排列如下，写程序给出一个字符串，走到字符串中所有的字母，从A开始走， 移动可以上下左右每次一步，打印出所有移动方向， 比如字符串HD,可以打印drr#ru(d-down, r-right,u-up)
//ABCDE
//FGHIJ
//KLMNO
//PQRST
//UNVXY
//Z
  public String print(String s){
    //s only contains 26 letters?
    //shortest path? move to same place twice?what if no result? multiple result?
    //Z:
    // case1: Leave Z:always go up first,and then right.
    // case2: Reach Z:left first and then down.
    int cols = 5;

    int[][] loc = new int[26][2];
    for(int i=0;i<26;i++){
      int r = i/cols,c = i%cols;
      loc[i] = new int[]{r,c};
    }
    char[] arr = s.toCharArray();
    int[] cur = {0,0};  //start with A
    StringBuffer path = new StringBuffer();
    for(int i=0;i<arr.length;i++){
      dfs(cur[0],cur[1],loc[arr[i]-'a'],path);
      cur = loc[arr[i]-'a'];
    }
    return path.toString();
  }
  public void dfs(int r,int c,int[] target,StringBuffer path){
    if(r==target[0] && c==target[1]){
      path.append('#');
      return ;
    }
    if(r>target[0]){
      //up
      path.append('u');
      dfs(r-1,c,target,path);
    }else if(r==target[0] && c<target[1]){
      //right
      path.append('r');
      dfs(r,c+1,target,path);
    }else if(c>target[1]){
      //left
      path.append('l');
      dfs(r,c-1,target,path);
    }else{
      //down
      path.append('d');
      dfs(r+1,c,target,path);
    }
  }

  public static void main(String[] args) {
    PrintPathInAlphaMatrix ins = new PrintPathInAlphaMatrix();
    System.out.println(ins.print("hd"));
    System.out.println(ins.print(""));
    System.out.println(ins.print("a"));
    System.out.println(ins.print("yzxez"));
  }
}
