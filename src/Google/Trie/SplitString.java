package Google.Trie;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=562776&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
//https://www.1point3acres.com/bbs/thread-561726-1-1.html
public class SplitString {
//给一个String, 例如ababcccccc 把它分成一个list of substrings
//要求是 如果这个substring没出现过 就直要这个substring, 如果出现过 就需要一直找到这个substring没有出现过为止
//这题目里面 答案应该是 a,b,ab,c,cc,ccc
//follow up: 优化到O(N)的time complexity
public static void main(String[] args) {
  SplitString ins  = new SplitString();
//  List<String> res = ins.split("abababababababababab");
  //Time: N*sqr root of N
  String s = "aaaaaaaaaaaaaaa";
  List<String> res = ins.split(s);
  System.out.println(res.get(res.size()-2).length()+" "+s.length());
  ins.print(res);
}
  public List<String> split(String s){
    char[] arr = s.toCharArray();
    int len = arr.length;
    int left = 0;
    List<String> res = new ArrayList<>();
    Container1 ctn = new Container1();
    while(left<len){
      int right = left+1;
      //can we split here?
      while(right<len && ( ctn.contains(s.substring(left,right)) || ctn.contains(s.substring(right)) ||
          s.substring(left,right).equals(s.substring(right)))){
        //can't split from here,

        right++;
      }
      String cur = s.substring(left,right);
      ctn.insert(cur);
      res.add(cur);
      left = right;
    }
    return res;
  }
  public void print(List<String> res){
    for(String s : res){
      System.out.print(s+" ");
    }
    System.out.println();
  }
}

class Container1{
  class Trie{
    Map<Character,Trie> c;  //children
    boolean isWord;
    public Trie(){
      c = new HashMap<>();
      isWord = false;
    }
  }
  public Container1(){
    root = new Trie();
  }
  Trie root;
  public boolean contains(String word){
    char[] arr = word.toCharArray();
    Trie temp = root;
    for(int i=0;i<arr.length;i++){
      if(!temp.c.containsKey(arr[i])){
        return false;
      }
      temp = temp.c.get(arr[i]);
    }
    return temp.isWord;
  }
  public void insert(String word){
    char[] arr = word.toCharArray();
    Trie temp = root;
    for(int i=0;i<arr.length;i++){
      if(!temp.c.containsKey(arr[i])){
        temp.c.put(arr[i],new Trie());
      }
      temp = temp.c.get(arr[i]);
    }
    temp.isWord = true;
  }

}
