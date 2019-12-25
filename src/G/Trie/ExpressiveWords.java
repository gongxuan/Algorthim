package G.Trie;

import java.util.Map;

//https://www.1point3acres.com/bbs/thread-572862-1-1.html
//第二轮，给定一个字符串list， 判断输入的字符是否在其中，输入时可能有字母重复，比如hello可能打成heeellllooooo.
public class ExpressiveWords {
  //dfs on trie tree, if no children,
  //1) ch==prev,skip ch
  //otherwise return false
  //Time: build: O(list length* string length) + O ( length of input string)
  class Container{
    class Trie{
      Map<Character,Trie> children;
      boolean isWord;
    }
    Trie root;
    public void build(String[] dict){

    }
    public boolean search(){
      //iterative approch
      return false;
    }
  }
}
