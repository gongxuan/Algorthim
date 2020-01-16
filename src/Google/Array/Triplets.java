package Google.Array;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=573776&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
import java.util.*;

//给一个正整数array，返回所有满足条件的triplets的数量，条件是，triplet的第二个数是第一个数的两倍，第三个数是第二个数的两倍，
// 并且triplet里面的数还是保留原来的顺序。比如input是[1, 2, 4, 8], 那么返回2，因为1, 2, 4和2, 4, 8都满足条件。
public class Triplets {
  //clarify: array given? Integer? duplicates? [1, 1,2, 4, 8]? sorted? print or count ?overflow?
  public int countTriplets(int[] arr){
    //use hashtable to store <number,list of occurrence index>

    //for every number: num1, for every occurrence index of num1
    //2*number to find second number's occurrence indices.
    //count number of indices > first one: freq2
    //4*number to find third number's occurrence indices.
    //count number of indices > second one.
    //triplets starting with num1, will be known,
    Map<Integer, List<Integer>> numIdx = new HashMap<>();

    for(int i=0;i<arr.length;i++){
      numIdx.computeIfAbsent(arr[i],v->new ArrayList<>()).add(i);

    }
    int count = 0;
    Set<Integer> numSet = numIdx.keySet();
    for(int num : numSet){
      List<Integer> idxList1 = numIdx.get(num);
      if(!numIdx.containsKey(num*2) || !numIdx.containsKey(num*4)){
        continue;
      }
      List<Integer> idxList2 = numIdx.get(num*2);
      List<Integer> idxList3 = numIdx.get(num*4);

      for(int idx1 : idxList1){
        //find a idx2 >idx1
        for(int idx2 : idxList2){
          if(idx2<idx1){
            continue;
          }
          for(int i=0;i<idxList3.size();i++){
            //find a idx3 > idx2
            int idx3 = idxList3.get(i);
            if(idx3<idx2){
              continue;
            }
//            System.out.println("num:"+num*4+" count:"+(idxList3.size() - i));
            count += idxList3.size() - i;
            break;
          }
        }
      }
    }

    return count;
  }

  public static void main(String[] args) {

    Triplets triplets = new Triplets();
//    int[] arr = {1,2,4,8};
//    int[] arr1 = {1,1,2,4,8};
    int[] arr2 = {1,2,2,2,4,4,4,4,8};
    int[] arr3 = {8,8,4,4,2,2,1,1};
//    System.out.println(triplets.countTriplets(arr));
//    System.out.println(triplets.countTriplets(arr1));
    System.out.println(triplets.countTriplets(arr2));
    System.out.println(triplets.countTriplets(arr3));
  }
}
