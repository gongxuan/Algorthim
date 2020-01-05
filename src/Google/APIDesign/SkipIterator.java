package Google.APIDesign;
import java.util.*;
//第四轮，白人西装大哥，设计一个skip iterator，普通的iterator但是可以调一个skip的函数跳过一些数，这个skip函数可以调很多次，可以skip任何数，
// 重复多次也许，也可以在任何时间调他。
//https://leetcode.com/discuss/interview-question/341818/Google-or-Onsite-or-Skip-Iterator
public class SkipIterator implements Iterator<Integer> {
  Map<Integer,Integer> skipMap;
  int[] it;
  int len;
  int curIdx;
  public SkipIterator(int[] nums) {
    skipMap = new HashMap<>();
    this.it = nums;
    len = nums.length;
    curIdx = 0;
  }

  public boolean hasNext() {
    return curIdx<len;
  }

  public Integer next() {
    if(curIdx==len){
      return null;
    }
    Integer res = it[curIdx++];
    remove();
    return res;
  }
  /**
   * The input parameter is an int, indicating that the next element equals 'val' needs to be skipped.
   * This method can be called multiple times in a row. skip(5), skip(5) means that the next two 5s should be skipped.
   */
  public void skip(int val) {
    skipMap.put(val,skipMap.getOrDefault(val,0)+1);
    remove();
  }
  public void remove(){
    while(curIdx<len){
      int val = it[curIdx];
      if(skipMap.containsKey(val)){
        decreaseFreq(val);
        curIdx++;
      }else{
        break;
      }
    }
  }
  public void decreaseFreq(int val){
    skipMap.put(val,skipMap.get(val)-1);
    if(skipMap.get(val)==0){
      skipMap.remove(val);
    }
  }



  public static void main(String[] args) {
//    List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{2, 3, 5, 6, 5, 7, 5, -1, 5, 10}));
    int[] num = {2, 3, 5, 6, 5, 7, 5, -1, 5, 10};
    SkipIterator itr = new SkipIterator(num);
    System.out.println(itr.hasNext()); // true
    System.out.println(itr.next()); // returns 2
    itr.skip(5);
    System.out.println(itr.next()); // returns 3
    System.out.println(itr.next()); // returns 6 because 5 should be skipped
    System.out.println(itr.next()); // returns 5
    itr.skip(5);
    itr.skip(5);
    System.out.println(itr.next()); // returns 7
    System.out.println(itr.next()); // returns -1
    System.out.println(itr.next()); // returns 10
    System.out.println(itr.hasNext()); // false
    itr.next(); // error
  }
}
