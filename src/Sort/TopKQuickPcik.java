package Sort;

public class TopKQuickPcik {
    //find Kth smaller number
    public int findKth(int[] nums,int K){
        if(K<=0 || K>nums.length){
            return -1;
        }
        sort(nums,0,nums.length-1,K);
        return nums[K-1];
    }
    public void sort(int[] nums,int low,int high,int K){
        if(low>high){
            return ;
        }
        int pivot = partition(nums,low,high);
        if(pivot-low==K-1){
            return ;
        }else if(pivot-low>K-1){
            sort(nums,low,pivot-1,K);
        }else{
            sort(nums,pivot+1,high,K-pivot);
        }
    }
    public int partition(int[] nums,int low,int high){
        int pivotVal = nums[high];
        int i = low;
        for(int j=low;j<high;j++){
            if(nums[j]<=pivotVal){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
            }
        }
        nums[high] = nums[i];
        nums[i] = pivotVal;
        return i;
    }

    public static void main(String[] args) {
        TopKQuickPcik t = new TopKQuickPcik();
        int[] nums = new int[]{4,3,8,1,6};
        int res = t.findKth(nums,4);
        for(int num :nums){
            System.out.print(num+" ");
        }
        System.out.println();
        System.out.println(res);
    }

}
