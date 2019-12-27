package G.DP;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=565427&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class TypeOnBoard {
//  国人小姐姐，因为office搬走了远程面
//  有两个手指，一个键盘（n*n board），假设所有character unique。现在我要打出“caption”问全局cost最小是多少
//      当然cost指的就是从一个pos移动到下一个pos的水平距离加垂直距离
  public int minCost(int[][] B,String s){
    updateMap(B);
    int sLen = s.length();
    mem=  new Integer[sLen][sLen][sLen];
//    int minCost = Math.min(dfs(s,0,1,2),dfs(s,1,0,2)) ;
//    return minCost;
    return dfs(s,0,1,2);  //no diff starting from which hands.
  }

  public static void main(String[] args) {
    int[][] B = {{1,2,3},{4,5,6}};
    String s = "1436";
    TypeOnBoard ins = new TypeOnBoard();
    System.out.println( ins.minCost(B,s));
  }
  public void updateMap(int[][] B){
    loc = new HashMap<>();
    for(int r=0;r<B.length;r++){
      for(int c=0;c<B[0].length;c++){
        loc.put(B[r][c],new Node(r,c));
      }
    }
  }
  Integer[][][] mem;
  Map<Integer,Node> loc;
  public int dfs(String s,int left,int right,int curIdx){
    if(curIdx==s.length()){
      return 0;
    }
    if(mem[left][right][curIdx] !=null){
      return mem[left][right][curIdx];
    }
    //use left hand to type current char
    Node curLoc = loc.get(s.charAt(curIdx)-'0');
    int minCost = dis(curLoc,loc.get(s.charAt(left)-'0'))+dfs(s,curIdx,right,curIdx+1);
    minCost = Math.min(minCost,dis(curLoc,loc.get(s.charAt(right)-'0'))+dfs(s,left,curIdx,curIdx+1) );
    mem[left][right][curIdx] = minCost;
    return minCost;
  }
  class Node {
    int r,c;
    public Node(int r,int c){
      this.r = r;this.c = c;
    }
  }
  public int dis(Node a,Node b){
    return Math.abs(a.r-b.r) + Math.abs(a.c-b.c);
  }
}
