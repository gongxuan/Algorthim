package SegmentTree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountofRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        //maintain a list of prefix sums, call it sums
        //sums[i] - sums[j] is the range sum in [j,i]
        //when traversing the list, we know current prefix sum.
        //we want to find all previous prefix sum that satisfy:
        //low<= currentPrefixSum - previousPrefixSum<=upper
        //=>  currentPrefixSum-upper<=previousPrefixSum<=currentPrefixSum-low
        //We need to search by ranges [currentPrefixSum-upper,currentPrefixSum-low]
        //How many previousPrefixSum are in this range?
        //segment tree support insert,query operations in Olgn ,build in Onlgn
        //Every node in the tree stores prefixSum in lower position and prefixSum in upper's.
        //store the number of prefixSum in range of [lower,upper]
        int len = nums.length;
        Set<Long> prefixSumsSet = new HashSet<>();
        long prefixSum = 0;
        for(int i=0;i<len;i++){
            prefixSum += (long)nums[i];
            prefixSumsSet.add(prefixSum);
        }
        Long[] prefixSums = prefixSumsSet.toArray(new Long[0]);
        Arrays.sort(prefixSums);
        SegmentTree root = build(prefixSums,0,prefixSums.length-1);
        int total = 0;
        for(int i=len-1;i>=0;i--){
            update(root,prefixSum);
            prefixSum -= (long)nums[i];   //previous prefixSum
            //We need to find number of prefixSums in the tree is in range of [currentPrefixSum-upper,currentPrefixSum-low]
            total += query(root,(long)prefixSum+lower,(long)prefixSum+upper);
        }
        return total;
    }
    class SegmentTree{
        //segment tree
        SegmentTree left,right;
        //number of prefixSums in range of [lower,upper]
        int count;
        //represent prefixSum segment
        long lower,upper;
        public SegmentTree(long lower,long upper){
            this.lower = lower;
            this.upper = upper;
            count = 0;
        }
    }
    public SegmentTree build(Long[] prefixSums,int lowerIndex,int upperIndex){
        if(lowerIndex>upperIndex){
            return null;
        }
        SegmentTree root = new SegmentTree(prefixSums[lowerIndex],prefixSums[upperIndex]);
        if(lowerIndex==upperIndex){
            return root;
        }
        int mid = lowerIndex + (upperIndex-lowerIndex)/2;
        root.left = build(prefixSums,lowerIndex,mid);
        root.right = build(prefixSums,mid+1,upperIndex);
        return root;
    }
    public int query(SegmentTree root,long lower,long upper){
        //get number of prefixSum in this tree that in range of [lower,upper]
        if(root==null)
            return 0;
        if(lower>root.upper || upper<root.lower){
            return 0;
        }else if(lower<=root.lower && upper>=root.upper){
            return root.count;
        }
        return query(root.left,lower,upper) + query(root.right,lower,upper);
    }
    public void update(SegmentTree root,long val){
        if(root==null)
            return ;
        if(root.lower<=val && val<=root.upper){
            root.count++;
            update(root.left,val);
            update(root.right,val);
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,-1,1,2,-3,-3};
        CountofRangeSum c = new CountofRangeSum();
        int res  = c.countRangeSum(nums,-3,1);
        System.out.println(res);
    }
}
