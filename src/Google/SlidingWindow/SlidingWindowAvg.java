package Google.SlidingWindow;
import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=562960&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class SlidingWindowAvg {
//给定一个数字input流，要求最后last k value的平均值，并且在计算时要求除去在这k个数字中的top5%和bottom5%，有time complexity要求
  //find top M, it support remove/insert,search operation. Balanced Tree. lgN time. duplicates? treemap or treeset
public static void main(String[] args) {
  SlidingWindowAvg ins  =new SlidingWindowAvg();
  List<Double> res = ins.lastKAvg(new int[]{1,2,3,4,5},3);
  ins.print(res);
}

  static final int B = 1;
  TreeMap<Integer,Integer> top = new TreeMap<>();
  TreeMap<Integer,Integer> bottom = new TreeMap<>(Collections.reverseOrder());
  public List<Double> lastKAvg(int[] A,int k){
    //sliding window
    int low=0,hi=0;
    double sum = 0;
    List<Double> res = new ArrayList<>();
    while(hi<A.length){
      sum += A[hi];
      add(A[hi]);
      if(hi>k-1){
        sum -= A[low];
        remove(A[low]);
        low++;
      }
      res.add(getAvg(sum,low,hi));
      hi++;
    }
    return res;
  }
  public void add(int val){
    top.put(val,top.getOrDefault(val,0)+1);
    bottom.put(val,bottom.getOrDefault(val,0)+1);
  }
  public void remove(int val){
    top.put(val,top.get(val)-1);
    bottom.put(val,bottom.get(val)-1);
    if(top.get(val)==0)
      top.remove(val);
    if(bottom.get(val)==0)
      bottom.remove(val);
  }
  public double getAvg(double sum,int low,int hi){
    sum -= getTreeSum(top);
    if(hi-low+1>=2*B)
      sum -= getTreeSum(bottom);
    return sum/(double)(hi-low+1);
  }
  public int getTreeSum(TreeMap<Integer,Integer> tree){
    Iterator<Map.Entry<Integer,Integer>> it = tree.entrySet().iterator();
    int sum = 0;
    for(int i=0;i<B;i++){
      sum += it.next() .getKey();
    }
    return sum;
  }
  public void print(List<Double> res){
    for(double num : res){
      System.out.print(num+" ");
    }
    System.out.println();
  }

}
