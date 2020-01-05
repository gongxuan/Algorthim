package Google.Array;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=571356&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
//https://leetcode.com/discuss/interview-question/algorithms/285144/google-minimize-the-distance-to-the-farthest-point
public class ClosestBlock {
//给一排街区，可以想象成是数组，不同的街区可能有不同的设施，比如体育馆、餐馆之类的，体育馆是一种，餐厅是一种， 每种设施有多个。
// 求选一个离所有种设施的距离最近的社区。这里距离不是距离和，是离当前街区最远的设施的距离。要求时间复杂度是街区个数乘设施种类数。
  //Assume you're looking to move, and have a set of amenities that you want to have easy access to from your new home. You have found a neighborhood you like, each block of which has zero or more amenities. How would you pick the block to live in such that the farthest distance to any amenity in your list is minimized?
  //Example:
  //Say your list contains {school, grocery}, and the blocks are as follows:
  //1: restaurant, grocery
  //2: movie theater
  //3: school
  //4:
  //5: school
  //
  //The ideal choice would be block 2, such that the distances to the grocery and the nearest school are 1 each. Living on block 1 or 3 would make one of the distances zero, but the other one 2.
  //should return 2
Map<Integer,Integer> winAme;
  public int closestBlock(int[][] B,Integer[] target){
    //what if multiple results? no result?

    //find min size of window that contains all target amenities, and middle position of the window is the result.
    //because left and right bounds of this minSize window will be two target amenities.
    //step:
    //1. add block's amenities to the window
    //2. if window contains all targets, try to shrink window to find min one.
    //3. if current Window is min so far, remember middle block.
    //4. return back to step 1, until the end.
    //Time: B* avg of ame of one block
    int len = B.length;
    winAme = new HashMap<>();
    int left=0,right=0;
    int res = -1,minSize=Integer.MAX_VALUE;
    while(right<len){
      addBlock(B[right]);
      while(containsAll(target)){
        if(right-left+1<minSize){
          minSize = right-left+1;
          res = left + (right-left)/2 +1;
        }
        rmBlock(B[left]);
        left++;
      }
      right++;
    }

    return res;
  }
  public void addBlock(int[] block){
    for(int amenity : block){
      winAme.put(amenity,winAme.getOrDefault(amenity,0)+1);
    }
  }
  public void rmBlock(int[] block){
    for(int amenity : block){
      winAme.put(amenity,winAme.get(amenity)-1);
      if(winAme.get(amenity)==0){
        winAme.remove(amenity);
      }
    }
  }
  public boolean containsAll(Integer[] target){
    Set<Integer> ame = winAme.keySet();
    return ame.containsAll(Arrays.asList(target));
  }

  public static void main(String[] args) {
    int[][] B = {{3,2},{4},{1},{},{1}};
    Integer[] target = {1,2};
    Integer[] target2 = {1,5};
    Integer[] target3 = {3,4};
    ClosestBlock ins = new ClosestBlock();
    System.out.println(ins.closestBlock(B,target));
    System.out.println(ins.closestBlock(B,target2));
    System.out.println(ins.closestBlock(B,target3));
  }
}
