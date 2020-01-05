package Google.BinarySearch;

public class KClosestNum {
//Given a list of sorted integers, an integer target and an integer n,
// output the range of n integers from the list that’s closest to the target
  //Input: [1,3,11,19,23], target = 11, n = 4
  //Output [0,3]
  //[0,3]代表在0到3 index的数[1,3,11,19]也就是距离target 11最近的四个数
  //followup: target不在list里面怎么办
  public int[] findKClosestToTarget(int[] A,int target,int K){
    //given? int,floating pointers? negs,zero, positives? repeating element? K > A.length? K<=0
    int len = A.length;
    int[] res = new int[2];
    if(K>len || K<=0){
      return new int[]{-1,-1};
    }
    int closest = findFloor(A,target);
    int l = closest,r = closest+1;


    while(K>0){
      if(l>=0 && r<len){
        if(isCloser(A,l,r,target)){
          l--;
        }else{
          r++;
        }
      }else if(l>=0){
        l--;
      }else{
        r++;
      }
      K--;
    }
    return new int[]{l+1,r-1};
  }
  public int findFloor(int[] A,int target){
    int low = 0,hi= A.length-1;
    int floor = -1;
    while(low<=hi){
      int mid = low + (hi-low)/2;
      if(A[mid]==target){
        return mid;
      }else if(A[mid]>target){
        hi = mid - 1;
      }else{
        floor = mid;
        low = mid +1;
      }
    }
    return floor;
  }
  public boolean isCloser(int[] A,int a,int b,int target){
    return Math.abs(A[a]-target)<=Math.abs(A[b]-target);
  }

  public static void main(String[] args) {
    int[] A = {1,3,5,7};
    KClosestNum ins =  new KClosestNum();
    int[] res = ins.findKClosestToTarget(A,4,3);
    System.out.println(res[0]+" "+res[1]);
    res = ins.findKClosestToTarget(A,-10,3);
    System.out.println(res[0]+" "+res[1]);
    res = ins.findKClosestToTarget(A,10,3);
    System.out.println(res[0]+" "+res[1]);
    res = ins.findKClosestToTarget(A,5,4);
    System.out.println(res[0]+" "+res[1]);
  }
}
