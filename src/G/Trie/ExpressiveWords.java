package G.Trie;

import java.util.HashMap;
import java.util.Map;

//https://www.1point3acres.com/bbs/thread-572862-1-1.html
//第二轮，给定一个字符串list， 判断输入的字符是否在其中，输入时可能有字母重复，比如hello可能打成heeellllooooo.
public class ExpressiveWords {
  //dfs on trie tree, if no children,
  //1) ch==prev,skip ch
  //otherwise return false
  //Time: build: O(list length* string length) + O ( length of input string)


  public static void main(String[] args) {
    String[] dict = {"hello", "hi", "helo"};
    String S = "heeellooo";
    Container container = new Container();
    container.build(dict);
    System.out.println(container.search(S));
  }
}
class Container{
  class Trie{
    Map<Character,Trie> children;
    boolean isWord;
    public Trie(){
      children = new HashMap<>();
      isWord = false;
    }
  }
  public Container(){
    root = new Trie();
  }
  Trie root;
  public void build(String[] dict){
    for(String word : dict){
      Trie temp = root;
      for(char ch : word.toCharArray()){
        if(!temp.children.containsKey(ch)){
          temp.children.put(ch,new Trie());
        }
        temp = temp.children.get(ch);
      }
      temp.isWord = true;
    }
  }
  public boolean search(String s){
    //iterative approach
    Trie temp = root;
    char[] arr = s.toCharArray();
    for(int i=0;i<arr.length;i++){
      if(!temp.children.containsKey(arr[i]) && (i==0 ||arr[i]!=arr[i-1])){
        return false;
      }else if(!temp.children.containsKey(arr[i]) && (i>0 && arr[i]==arr[i-1])){
        continue;
      }
      temp = temp.children.get(arr[i]);
    }
    return temp.isWord;
  }
}
