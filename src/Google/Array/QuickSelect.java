package Google.Array;

public class QuickSelect {
    public double findMedian(int[] arr){
        int len = arr.length;
        if(len%2==0){
            return (findMedianHelper(arr,len/2)
                    + findMedianHelper(arr,len/2+1))/2.0;
        }else{
            return findMedianHelper(arr,len/2+1);
        }
    }
    public int findMedianHelper(int[] arr,int k){
        if(k>arr.length || k<=0){
            return 0;
        }
        int left = 0;
        int right = arr.length -1;
        while(left<=right){
            int pivot = partition(arr,left,right);
            if(pivot-left==k-1){
                return arr[pivot];
            }else if(pivot-left>k-1){
                right = pivot - 1;
            }else{
                k = k - (pivot - left + 1);
                left = pivot + 1;
            }
        }
        return 0;
    }
    public int partition(int[] arr,int left,int right){
        int pivot = arr[right];
        int smallerIndex = left;
        for(int i=left;i<right;i++){
            if(arr[i]<=pivot){
                swap(arr,smallerIndex,i);
                smallerIndex++;
            }
        }
        swap(arr,smallerIndex,right);
        return smallerIndex;
    }
    public void swap(int[] arr,int ida,int idb){
        int tem = arr[ida];
        arr[ida] = arr[idb];
        arr[idb] = tem;
    }
    public static void main(String[] arg){
        QuickSelect qs = new QuickSelect();
        int[] arr = {3,1,2,4};
        System.out.println(qs.findMedian(arr));
    }
}
