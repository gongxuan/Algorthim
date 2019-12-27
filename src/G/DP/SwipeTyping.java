package G.DP;

import java.util.*;

public class SwipeTyping {
  //手机上滑动打字的feature。安卓一直都有的，ios13新加的那个。例如说用户输入的,bhjiojnbgfdsa，
  // 返回所有可能的词。“boba”是其中之一。不能保证首字母和结尾字母是开头和结尾。“bago”这个input要返回，[bag, go] 两个词。
  // 假设手机内置有一个存有所有valid的词的词典。

  public static void main(String[] args) {
    Set<String> dict = new HashSet<>();
    dict.add("dog");
    dict.add("go");
    dict.add("goo");
    dict.add("dgo");
    dict.add("dgd");
    SwipeTyping ins = new SwipeTyping();
    List<String> res = ins.findAll("dogo",dict);
    print(res);
  }
  public List<String> findAll(String s,Set<String> dict){
    //How big is this dict? how long is this s?
    //if s is short, we can find all subsequence and check if it's in dict or not.
    //if dict is not too big, we can check every word in dict if it's a subsequence of s
    List<String> res = new ArrayList<>();
    for(String word : dict){
      if(isSubseq(s,word))
        res.add(word);
    }
    return res;
  }
  public boolean isSubseq(String s,String word){
    //Time: s
    int wIdx = 0;
    char[] sArr = s.toCharArray();
    char[] wArr = word.toCharArray();
    for(int i=0;i<sArr.length;i++){
      if(sArr[i]==wArr[wIdx]){
        wIdx++;
        if(wIdx==word.length()){
          return true;
        }
      }
    }
    return false;
  }


  public static void print(List<String> res){
    for(String s : res){
      System.out.print(s+" ");
    }
    System.out.println();
  }
//  public List<String> findAll(String s,Set<String> dict){
//    //TLE
//    dfs(s,0,new StringBuffer(),dict);
//    return res;
//  }
//  List<String> res = new ArrayList<>();
//  public void dfs(String s,int curIdx,StringBuffer cur,Set<String> dict){
//    if(dict.contains(cur.toString()))
//      res.add(cur.toString());
//    for(int i=curIdx;i<s.length();i++){
//      cur.append(s.charAt(i));
//      dfs(s,i+1,cur,dict);
//      cur.deleteCharAt(cur.length()-1);
//    }
//  }
}
