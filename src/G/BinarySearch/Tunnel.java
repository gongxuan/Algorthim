package G.BinarySearch;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=570847&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=568876&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3088%255D%255Bvalue%255D%3D1%26searchoption%255B3088%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=3
public class Tunnel {
  //给到两个数列，一个是隧道各点的高度，最左是入口，最右是底部，一个是一堆货物的高度，要求把尽可能多的货物塞进隧道，
  // 最后返回赛进隧道的货物高度数列，任意最长数列即可
  //比如隧道是[6,4,5,7,3,5,8]，货物是[2,3,5,6,7,7,8,8,9]
  //限制条件是，货物不能通过比自身高度低的隧道点，以及某个隧道点被放置货物之后，其余货物只能放在该点左侧

  //给定一堆不同高度的objects和一堆仓库storage的空间。东西只能从左向右推进仓库，如果有个位置太低了，那么后面的位置都会被前面的位置局限住。
  // 例如仓库是[1,5], 那么第二个位置最多也只能放1。
  //问最多能把多少objects放进仓库。
  //例如,
  //objects = [3,3,1,4,5]
  //storage = [4,5,1,5]
  //例如这里最多能放3个,[3,3,0,1]
//  follow up1 是如果storage的空间远远小于object的数量。
//  follow up2 是如果storage左右都能推进去。
//  我说是维护一个从左到右的min height和从右往左的min height,然后能放的高度是两个min height中大的那个，他说应该可以
  //follow up3 - 货物array 很大不适合sort.
  //maintain a minHeap which size equals to tunnel's size, scan item array find top k lowest items.

public static void main(String[] args) {
  Tunnel t= new Tunnel();
  int[] H = {6,4,5,7,3,5,8};
  int[] items = {2,3,5,6,7,7,8,8,9};
  List<Integer> res = t.addItems(H,items);
  //0  1 2 3 4 5 6
  //[6,4,4,4,3,3,3]
  for(int x :res){
    System.out.print(x+" ");
  }
  //case2:
  System.out.println();
  items = new int[]{7,7,8,8,9};
  res = t.addItems(H,items);
  // 0 1 2 3 4 5 6
  //[6,4,4,4,3,3,3]
  for(int x :res){
    System.out.print(x+" ");
  }
  //case3:
  System.out.println();
  items = new int[]{1,3,3,3,3,3,3,3,3,3,3};
  res = t.addItems(H,items);
  // 0 1 2 3 4 5 6
  //[6,4,4,4,3,3,3]
  for(int x :res){
    System.out.print(x+" ");
  }
}
  public List<Integer> addItems(int[] H,int[] items){
    Arrays.sort(items); //[2,3,5,6,7,7,8,8,9]
    for(int i=1;i<H.length;i++){  //[6,4,4,4,3,3,3]
      if(H[i]>H[i-1]){
        H[i] = H[i-1];
      }
    }
    List<Integer> list = new LinkedList<>();
    int right = H.length-1;
    for(int i=0;i<items.length;i++){
      int idx = addToTunnel(H,items[i],right);
      if(idx!=-1){
        list.add(0,idx);
        right = idx-1;
      }else{
        break;
      }
    }
    return list;
  }

  public int addToTunnel(int[] H,int item,int right){
    //place the item to rightmost position but smaller than or equal to height of tunnel
    int left = 0;
    while(left<right){
      int mid = left + (right-left)/2;
      if(item>H[mid]){
        right = mid - 1;
      }else{
        left = mid + 1;
      }
    }
    //two cases
    if(left==right){
      if(H[left]<item){
        return left-1;
      }
      return left;
    }
    //left=right+1
    return -1;  //
  }
}
