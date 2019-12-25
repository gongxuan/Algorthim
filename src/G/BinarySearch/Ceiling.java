package G.BinarySearch;

public class Ceiling {
  public static int find(int[] nums,int target){
    //find smallest number that is strictly larger than target
    int ceil= -1;
    int low = 0, hi = nums.length - 1;
    while (low <= hi) {
      int mid = low + (hi - low) / 2;
      if (nums[mid] <= target) {
        low = mid + 1;
      } else{
        ceil = mid;  //any element after mid couldn't be first one larger than target
        hi = mid - 1;
      }
    }
    //eventually, ceil will be last possible place larger than target
    return ceil;
  }

  public static void main(String[] args) {
    int[] nums = {1,3,5};
    System.out.println(find(nums,6));
    System.out.println(find(nums,5));
    System.out.println(find(nums,1));
    System.out.println(find(nums,2));
    System.out.println(find(nums,4));
    System.out.println(find(nums,-2));
  }
}
