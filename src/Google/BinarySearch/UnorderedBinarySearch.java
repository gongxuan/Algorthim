package Google.BinarySearch;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=563431&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class UnorderedBinarySearch {
  //乱序binary search：对一个乱序数组用普通的binary search，多少个数能按照原算法被正确搜出来？比如[2,5,1,3,4]，1是可以搜到的，因为它是mid，
  // 然后3和4也是可以搜到的，因为它们都大于1，binary search会往右边走。2和5都搜不到，因为已开始算法会以为2和5都在1的右边。所以最后答案是3 （1，3，4）。
  //similar with problem "is this a valid binary search tree"
  //更正后，小哥又问现在如果不是取(l +r) // 2，而是随机在(l, r)内部取一个点，哪些数是一定能搜索出来的。
  // 我先答在正确位置的数，能被搜出来，被小哥[3,2,1]的例子反驳，然后我说还是用之前mid的性质判断左右。问应该怎么做，当时已经没时间了
  //我先说brute force，最后说了dp，小哥说你没时间写了。
  //5,1,2,3,1,5,6 => only 6
  //1,2,3,4=> 1,2,3,4
  int count = 0;
  List<Integer> res;
  public static void main(String[] args) {
    int[] A = {2,5,1,3,4};
    UnorderedBinarySearch ins = new UnorderedBinarySearch();

    ins.maxCanFound(A);
    A = new int[]{2,2,2,2,2};
    ins.maxCanFound(A);
    A = new int[]{5,4,3,2,1};
    ins.maxCanFound(A);
    A = new int[]{1,2,3,4,5};
    ins.maxCanFound(A);
  }
  public int maxCanFound(int[] A){
    //given? duplicates? int/ double/ floating?
    count=0;
    res = new ArrayList<>();
    bs(A,0,A.length-1,Integer.MIN_VALUE,Integer.MAX_VALUE);
    for(int num : res){
      System.out.print(num+" ");
    }
    System.out.println();
    return count;
  }

  public void bs(int[] A,int left,int right, int low,int up){
    if(left>right){
      return ;
    }
    int mid = left + (right-left)/2;
    if(low<=A[mid] && A[mid]<=up){
      count++;
      res.add(A[mid]);
    }
    bs(A,left,mid-1,low,Math.min(up,A[mid]-1));
    bs(A,mid+1,right,Math.max(low,A[mid]+1),up);
  }
}
