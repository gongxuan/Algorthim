package Google.Array;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=572643&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
//给出两个input和output要满足3个要点
//    1. R only can go right
//    2. L only can go left
//    3. R and L can't cross
//
//    ie.
//
//    Input:  R _ _ _ L _ _ R _
//    output: R L _ _ _ _ _ _ R
//    return True
//    [explanation: input L went to left, and input R went to right become output.]
//
//    ie.
//    Input:   R _ _ _ L _ _ R _
//    output:  L R _ _ _ _ _ _ R
//    return False
//
//    (follow up： what if one cross is allowed, how to solve it)
public class SwapAdjacentinLRString {

  public static void main(String[] args) {
    //can letters cross each other? mv out of boundary?
    //
  }
  public boolean isValidMove(String input,String output){
    char[] arr1 = input.toCharArray();
    char[] arr2 = output.toCharArray();
    List<Letter> list = new ArrayList<>();
    for(int i=0;i<arr1.length;i++){
      if(arr1[i]=='R' || arr1[i]=='L'){
        list.add(new Letter(arr1[i],i));
      }
    }
    int idx = 0;
    int i=0;
    for(;i<arr2.length;i++){
      if(arr2[i]!='R' && arr2[i]!='L'){
        continue;
      }
      if(idx>=list.size())
        return false;
      Letter cur = list.get(idx);
      if(cur.l!=arr2[i]){
        return false;
      }else if(cur.l=='R' && cur.pos<i){
        return false;
      }else if(cur.l=='L' && cur.pos>i){
        return false;
      }

      idx++;

    }
    //number of letter might not be same
    if(idx<list.size())
      return false;

    return true;
  }
  class Letter{
    char l;
    int pos;
    public Letter(char l,int p){
      this.l = l; pos = p;
    }
  }
}
