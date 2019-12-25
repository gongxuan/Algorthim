package G.Array;

public class MoveZero {
//姐姐给的题类似 move 0，把0交换到array最前端 但同时要把非0的move到后端
  public int[] move(int[] nums){
    int i=0,j=nums.length-1;
    //use i to find first non-zero num, use j to find first zero num ,
    while(i<j){
      while(i<j && nums[i]==0){
        i++;
      }
      while(i<j && nums[j]!=0){
        j--;
      }
      //swap
      swap(nums,i,j);
      i++;
      j--;
    }
    for(int x=0;x<nums.length;x++){
      System.out.print(nums[x]+" ");
    }
    System.out.println();
    return nums;
  }
  public void swap(int[] nums,int i,int j){
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    MoveZero ins = new MoveZero();
    int[] nums = {1,1,0,4,5,0,1,4,0};
    ins.move(nums);
    nums = new int[]{1,1,4,5,1,4};
    ins.move(nums);
    nums = new int[]{0,0,0,0,0};
    ins.move(nums);
  }

}
