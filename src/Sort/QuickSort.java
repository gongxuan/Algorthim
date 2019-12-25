package Sort;

public class QuickSort {

    public void sort(int[] arr, int left, int right){
        if(left>=right)    return ;
        int pivot = partition(arr,left,right);
        sort(arr,left,pivot-1);
        sort(arr,pivot+1,right);
    }

    public int partition(int[] arr,int left, int right){
        int pivot = arr[right];
        int i = left;
        for(int j=left;j<right;j++){
            if(arr[j]<=pivot){
                //swap
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
        }
        //swap arr[i] and pivot
        arr[right] = arr[i];
        arr[i] = pivot;
        return i;
    }


    public static void main(String[] args) {
        int[] arr = {9,1,4,5,7,2,3,8,5};
        QuickSort qs = new QuickSort();
        int pi = qs.partition(arr,0,arr.length-1);
        System.out.println(pi);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
