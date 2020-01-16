package Google.API_Design;
import java.util.*;

public class Cooldown {
//第一轮：设计一个API，给你一个数组，然后要求随机选择一个其中的元素，每个元素有一个cooldown时间，然后选过的元素在cooldownn时间内不能再选择。
  //If pick a element in cooldown time, what will happen? pick another one or throw Exception or return a invalid
  int[] nums;
  int CD;
  int[] times;  //last visited time
  Random r = new Random();
  public Cooldown(int[] nums,int cooldownTime){
    this.nums = nums;
    CD = cooldownTime;
    times = new int[nums.length];
  }
  public Integer pick(int timestamp){
    int rIdx=  r.nextInt(nums.length);
    if(timestamp-times[rIdx]<CD){
      return null;
    }
    times[rIdx] = timestamp;
    return nums[rIdx];
  }

}
