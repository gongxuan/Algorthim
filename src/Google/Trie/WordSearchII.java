package Google.Trie;
import java.util.*;

public class WordSearchII {
  //212 variants: 8 directions, can reuse cells but not continuously reuse a cell. Given a word list,
  // find the words that can be find in the matrix
  static int R,C;
  public List<String> findWords(char[][] B, String[] words) {
    R = B.length;
    C = B[0].length;
    Container3 ctn = new Container3();
    ctn.buildTree(words);
    List<String> res = new ArrayList<>();
    for(int r=0;r<R;r++){
      for(int c=0;c<C;c++){
        List<String> w = ctn.search(B,r,c);
        if(!w.isEmpty()){
          res.addAll(w);
        }
      }
    }

    return res;
  }

}
class Container3{
  class Trie{
    Map<Character,Trie> c;
    boolean isWord;
    String word;
    public Trie(){
      c = new HashMap<>();
      word = "";
      isWord = false;
    }
  }
  public Container3(){
    root = new Trie();
  }
  Trie root;
  static int[][] DIC = {{0,1},{1,0},{0,-1},{-1,0}};
  static int R,C;
  public List<String> search(char[][] B,int r,int c){
    R = B.length;C = B[0].length;
    Trie curr = root;
    visited = new int[R][C];
    words = new ArrayList<>();
    visited[r][c] = 1;
    searchHelper(B,r,c,curr);
    return words;
  }
  List<String> words;
  int[][] visited;
  public void searchHelper(char[][] B,int r,int c,Trie curr){
    if(!curr.c.containsKey(B[r][c])){
      return ;
    }
    curr = curr.c.get(B[r][c]);
    if(curr.isWord){
      curr.isWord = false;
      words.add(curr.word);
    }
    for(int i=0;i<4;i++){
      int nr = DIC[i][0] + r;
      int nc = DIC[i][1] + c;
      if(nr<0 || nr>=R || nc<0 || nc>=C || visited[nr][nc]==1)  continue;
      visited[r][c] = 1;
      searchHelper(B,nr,nc,curr);
    }
  }
  public void buildTree(String[] words){
    for(String word : words){
      Trie curr = root;
      char[] arr = word.toCharArray();
      for(char ch : arr){
        if(!curr.c.containsKey(ch)){
          curr.c.put(ch,new Trie());
        }
        curr = curr.c.get(ch);
      }
      curr.isWord = true;
      curr.word = word;
    }
  }

}
