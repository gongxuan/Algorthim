package G.BinarySearch;

public class FloorNumber {

  public static int find(int[] nums, int target) {
    //Excluding target
    int low = 0, hi = nums.length - 1;

    int floor = -1;
    while (low <= hi) {
      int mid = low + (hi - low) / 2;
      if (nums[mid] < target) {
        floor = mid;  //any element before mid couldn't be last one small than target
        low = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    //eventually, floor will be last possible place small than target
    return floor;
  }

  public static void main(String[] args) {
    int[] nums = {1,3,5};
    System.out.println(find(nums,6));
    System.out.println(find(nums,5));
    System.out.println(find(nums,1));
    System.out.println(find(nums,2));
    System.out.println(find(nums,-2));
  }
}
