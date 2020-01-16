package Google.DP;

import java.util.*;

public class TargetSumII {
//1. 给定一个数组，和一个target，要求返回这个数组中能不能有subset的sum是target，followup问的是返回有多少这样的subset，
// 以及如果不仅仅是和，还可以减，或者乘除怎么办，还有如果是要求返回subset本身怎么办
//E.g Given [3,5,6,7,3,2,6,1] t=9
//Return [5,3,1], [7,2] etc.
public static void main(String[] args) {
  int[] A = {3,2,1,5};
  TargetSumII ins = new TargetSumII();
  System.out.println(ins.countSubset(A,5));
}
  static final double THRESHOLD = 0.00005;
  List<String> paths;
  public int countSubset(int[] A,int target){
    //duplicates? reuse item? target range? int/ double/floating points?
    int len = A.length;
    mem = new HashMap<>();
    paths = new ArrayList<>();
    return dfs(A,0,0,0,target,"");
  }

  Map<String,Integer> mem;
  public int dfs(int[] A,int curIdx,double prev, double cur, double target,String path){
    if(Math.abs(cur+prev-target)<=THRESHOLD){
      System.out.println(path);
      paths.add(path);
      return 1;
    }
    if(curIdx==A.length){
      return 0;
    }
    String hash = prev+" "+cur+" " + curIdx;
    if(mem.containsKey(hash)){
      return mem.get(hash);
    }
    int total = 0;
    for(int i=curIdx;i<A.length;i++){

      //+
      total += dfs(A,i+1,prev+cur,A[i],target,path+"+"+A[i]);
      if(path.equals("")){
        continue;
      }
      //-
      total += dfs(A,i+1,prev+cur,-A[i],target,path+"-"+A[i]);
      //*
      total += dfs(A,i+1,prev,cur*A[i],target,path+"*"+A[i]);
      // /
      if(A[curIdx]!=0)
        total += dfs(A,i+1,prev,cur/A[i],target,path+"/"+A[i]);
    }
    mem.put(hash,total);
    return total;
  }
}
