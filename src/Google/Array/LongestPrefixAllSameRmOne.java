package Google.Array;

import java.util.*;

public class LongestPrefixAllSameRmOne {
//Given an array nums of positive integers, return the longest possible length of an array prefix of nums, such that it is possible to remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.
//If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of occurrences (0).
//
//Input: nums = [2,2,1,1,5,3,3,5]
//Output: 7
//Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4]=5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
//
//Input: nums = [10,2,8,9,3,8,1,5,2,3,7,6]
//Output: 8
//
//Input: nums = [1,1,1,2,2,2]
//Output: 5
  public static void main(String[] args) {
    LongestPrefixAllSameRmOne ins = new LongestPrefixAllSameRmOne();
    int[] A = {1,1,1,2,2,3};
    System.out.println(ins.findLongestPrefix(new int[]{1,1,1,2,2,3}));
    System.out.println(ins.findLongestPrefix(new int[]{1,1,1,1,1,1}));
    System.out.println(ins.findLongestPrefix(new int[]{1,1,1,2,2,2,3}));
  }
  public int findLongestPrefix1(int[] A){
    //case1: find a moment that all freq r same. and freq*c(number of elements with this freq)==length, eg:1 1 2 2
    //result might be length+1
    //case2: freq*c(number of elements with this freq)==length-1 eg:1 1 1 2 2
    //with exactly one more redundant
    return 0;
  }
  public int findLongestPrefix(int[] A){
    if(A==null || A.length==0)
      return 0;
    Map<Integer,Integer> numToFreq = new HashMap<>();
    Map<Integer,Integer> freqToCount = new HashMap<>();
    int max = 1;
    for(int i=0;i<A.length;i++){//1 1
      update(numToFreq,freqToCount,A[i]);	//
       //(1,1)
      if(check(freqToCount)){
        max = i+1;	//1
      }
    }
    return max;
  }
  public boolean check(Map<Integer,Integer> freqToCount){
    if(freqToCount.containsKey(1) && (freqToCount.get(1)==1 && freqToCount.size()==2))  //case1: 1 1 1 2 2 2 4
      return true;
    if(freqToCount.size()==1){
      return true;  //case2:1 1 1 1 1
    }
    if(freqToCount.size()!=2){
      return false;
    }
    Iterator<Map.Entry<Integer,Integer>> it = freqToCount.entrySet().iterator();
    Map.Entry<Integer,Integer> e1 = it.next();
    Map.Entry<Integer,Integer> e2 = it.next();
    int freq1 = e1.getKey(),freq2 = e2.getKey();
    int count1 = e1.getValue(),count2 = e2.getValue();
    if(freq1-freq2==1){ //case3: 1 1 1 2 2
      return count1==1;
    }else if(freq2-freq1==1){
      return count2==1;
    }
    return false;
  }
  public void update(Map<Integer,Integer> numToFreq,Map<Integer,Integer> freqToCount ,int num){	//2
    int oldFreq = numToFreq.getOrDefault(num,0);//1
    if(freqToCount.containsKey(oldFreq)){
      freqToCount.put(oldFreq,freqToCount.get(oldFreq)-1);
      if(freqToCount.get(oldFreq)==0){
        freqToCount.remove(oldFreq);
      }
    }
    int newFreq = oldFreq+1;	//1 2
    freqToCount.put(newFreq,freqToCount.getOrDefault(newFreq,0)+1);	//
    numToFreq.put(num,newFreq);
  }

  //Case 1:
  //frequency * (number of elements with that frequency) == length AND i != nums.length - 1
  //E.g. [1,1,2,2,3]
  //When the iteration is at index 3, the count will be equal to the length. It should update the result with (length + 1) as it should take an extra element in order to fulfil the condition.
  //Case 2:
  //frequency * (number of elements with that frequency) == length - 1
  //E.g. [1,1,1,2,2,3]
  //When the iteration is at index 4, the count will be equal to (length - 1). It should update the result with length as it fulfil the condition.
  public int maxEqualFreq(int[] nums) {
    Map<Integer, Integer> countMap = new HashMap<>();
    Map<Integer, Integer> freqMap = new HashMap<>();
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      // update counts
      countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
      int freq = countMap.get(nums[i]);
      // update counts with that frequency
      freqMap.put(freq, freqMap.getOrDefault(freq, 0) + 1);

      int count = freqMap.get(freq) * freq;
      if (count == i + 1 && i != nums.length - 1) { // case 1
        res = Math.max(res, i + 2);
      } else if (count == i) { // case 2
        res = Math.max(res, i + 1);
      }
    }
    return res;
  }

}
