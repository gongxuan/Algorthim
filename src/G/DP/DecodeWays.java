package G.DP;

import java.util.*;

//给一串数字，输出可以得到的所有英文字母组合的list，例如"126"->{"ABF", "LF", "AZ"}，follow up是优化time。
public class DecodeWays {
  List<String>[] mem;
  String s;
  public List<String> numDecodings(String s) {
    //leading zero?
    //dfs to try all the ways to decode input
    //eg:226 -> 2+26, 22+6
    //2+26-> 2+2+6, 22+6->2+2+6
    //use mem to overome overlapping subproblems
    //changing values is currIdx
    if(s==null || s.length()==0)
      return new ArrayList<>();
    this.s = s;
    mem = new List[s.length()];
    return dfs(s,0);
  }

  public List<String> dfs(String s,int curIdx){
    if(curIdx==s.length()){
      return null;
    }
    if(mem[curIdx]!=null){
      return mem[curIdx];
    }
    List<String> res = new ArrayList<>();
    for(int i=curIdx;i<s.length();i++){
      int num = Integer.valueOf(s.substring(curIdx,i+1));
      if(num==0 || num>26){
        break;
      }
      List<String> temp = dfs(s,i+1);
      if(temp==null){
        res.add( String.valueOf((char)(num-1+'A')));
      }else{
        for(String str : temp){
          res.add(String.valueOf((char)(num-1+'A'))+str);
        }
      }
    }
    mem[curIdx] = res;
    return res;
  }

  public static void main(String[] args) {
    String s = "12131023126";
    DecodeWays ins = new DecodeWays();

    List<String> res =  ins.numDecodings(s);
    for(int i= 0;i<res.size();i++){
      System.out.println(res.get(i));
    }
    System.out.println(res.size());
  }

}
