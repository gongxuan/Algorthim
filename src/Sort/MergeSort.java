package Sort;

import java.util.Arrays;

public class MergeSort {
    public void sort(int[] nums,int start,int end){
        if(end<=start){
            return ;
        }
        int mid = start + (end-start)/2;
        sort(nums,start,mid);
        sort(nums,mid+1,end);
        //merge
        merge(nums,start,end,mid);
    }
    public void merge(int[] nums,int start,int end,int mid){
        int[] lNums = Arrays.copyOfRange(nums,start,mid+1);
        int[] rNums = Arrays.copyOfRange(nums,mid+1,end+1);
        int l = 0,r = 0;
        int ni = start;
        while(l<lNums.length && r<rNums.length){
            if(lNums[l]<rNums[r]){
                nums[ni++] = lNums[l++];
            }else{
                nums[ni++] = rNums[r++];
            }
        }
        while(l<lNums.length){
            nums[ni++] = lNums[l++];
        }
        while(r<rNums.length){
            nums[ni++] = rNums[r++];
        }
    }
    public static void main(String[] args) {
        int[] nums = {3,-2,2,0};
        MergeSort m  =new MergeSort();
        m.sort(nums,0,nums.length-1);
        for(int i=0;i<nums.length;i++)
            System.out.print(nums[i]+" ");
        System.out.println();
    }
}
