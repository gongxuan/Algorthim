package Sort;

import java.util.Arrays;

public class CountOfRangeSum {
    //LC327 Merge Sort
    public int countRangeSum(int[] nums, int lower, int upper) {
        //merge sort.
        //After partitioning, left half is smaller than right half.
        //When iterate through the left half with index i,we need two indices j,k in the right half that satisfy:
        //j<=k
        //find first index that sums[k]-sums[i]>upper
        //find first index that sums[j]-sums[i]>=lower
        int len = nums.length;
        long[] sums = new long[len+1];
        long prefixSum = 0L;
        for(int i=0;i<len;i++){
            prefixSum += (long)nums[i];
            sums[i+1] = prefixSum;
        }
        int res =  countWhenSort(sums,0,len,lower,upper);
        for(int i=0;i<sums.length;i++)
            System.out.print(sums[i]+" ");
        System.out.println();
        return res;
    }
    public int countWhenSort(long[] sums,int start,int end,int lower,int upper){
        if(end<=start){
            return 0;
        }
        int mid = start + (end - start) / 2;
        int count = countWhenSort(sums,start,mid,lower,upper) + countWhenSort(sums,mid+1,end,lower,upper);
        return count+merge(sums,start,end,lower,upper,mid);
    }
    public int merge(long[] sums,int start,int end,int lower,int upper,int mid){
        int count = 0;
        long[] cache = new long[end-start+1];
        int low = mid+1,high=mid+1,right=mid+1;
        int ci = 0;
        for(int left=start;left<=mid;left++){
            while(right<=end && sums[right]<sums[left]){
                cache[ci++] = sums[right++];
            }
            //find first index that sums[k]-sums[i]>upper
            while(high<=end && sums[high] - sums[left]<=upper)
                high++;
            while(low<=end && sums[low] - sums[left]<lower)
                low++;
            cache[ci++] = sums[left];
            count += high-low;
        }
        while(right<=end){
            cache[ci++] = sums[right++];
        }
        for(int i=start;i<=end;i++){
            sums[i] = cache[i-start];
        }
        return count;
    }

   

    public static void main(String[] args) {
        CountOfRangeSum c = new CountOfRangeSum();
        int[] nums =  {-2,5,-1};
        int res = c.countRangeSum(nums,-2,2);
        System.out.println(res);
    }
}
