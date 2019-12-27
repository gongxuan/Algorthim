package G.Graph;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=570847&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
import java.util.*;

public class WordLadder {
//binary lock，面经题，但当没看明白，其实就是word ladder，给你一个初始word S，目标word T，allowed list，
// 问能不能从S变到T，每个word是个二进制字符串，一次变化是flip任意一位bit，每次变化后的word都要在allowed list里
//follow up是allowed list不变，然后给一列{S,T}，然后返回一列{能不能变}，就把allowed list里的connected component都预处理出来，
// 然后看各个S,T是不是在同一个component里

  //第二轮 给一堆01组成的字符串 所有字符串都是相同长度 这些字符串表示安全的pattern，每个pattern之间可以通过flip任意一个bit进行互相转换 ，
  // 给定起始pattern和终止pattern，判断能不能成功转换，所有intermediate状态必须是安全的，非常类似word ladder。
  // follow up：给多个起始状态和多个终止状态
  public boolean can(String S,String T, List<String> allowed){
    //duplicates in allowed? target in allowed? length of word?
    //undirected graph, are S and T connected?, Time: allowed list+ E
    Set<String> dict = new HashSet<>(allowed);
    return bfs(S,T,dict);
  }
  public boolean bfs(String S,String T,Set<String> dict){
    Set<String> visited = new HashSet<>();
    visited.add(S);
    Queue<String> queue = new LinkedList<>();
    queue.add(S);
    int steps = 0;
    while(!queue.isEmpty()){
      int size = queue.size();
      while(size-->0){
        String cur = queue.poll();
        if(cur.equals(T)){
          System.out.print("steps:"+steps+" ");
          return true;
        }
        char[] curArr = cur.toCharArray();
        for(int i=0;i<curArr.length;i++){
          char origin = curArr[i];
          for(char ch='a';ch<='z';ch++){
            if(ch==origin)  continue;
            curArr[i] = ch;
            String nbr = String.valueOf(curArr);
            if(dict.contains(nbr) && !visited.contains(nbr)){
              visited.add(nbr);
              queue.offer(nbr);
            }

          }
          curArr[i] = origin;
        }
      }
      steps++;
    }

    return false;
  }

  public static void main(String[] args) {
    String start = "hit";
    String endWord = "cog";
    String[] wordList = {"hot","dot","dog","lot","log","cog"};
    WordLadder ins = new WordLadder();
    System.out.println(ins.can(start,endWord,new ArrayList<>(Arrays.asList(wordList))));
    System.out.println(ins.can2(start,endWord,new ArrayList<>(Arrays.asList(wordList))));
    wordList = new String[]{"hot","dot","dog","lot","log"};
    System.out.println();
    System.out.println(ins.can(start,endWord,new ArrayList<>(Arrays.asList(wordList))));
    System.out.println(ins.can2(start,endWord,new ArrayList<>(Arrays.asList(wordList))));
  }
  class UFContainer{
    //Time: N = allowed size, NlgN
    int[] ids;
    Map<String,Integer> dict = new HashMap<>();
    public UFContainer(int size){
      ids = new int[size];
      for(int i=0;i<ids.length;i++){
        ids[i] = i;
      }

    }
    public void build(List<String> allowed){
      //Time: NlgN * avg of word
      for(int i=0;i<allowed.size();i++){
        dict.put(allowed.get(i),i);
      }
      for(int i=0;i<allowed.size();i++){
        char[] arr = allowed.get(i).toCharArray();
        for(int j=0;j<arr.length;j++){
          char ori = arr[j];
          for(char ch='a';ch<='z';ch++){
            if(ch==ori) continue;
            arr[j] = ch;
            String nbr = String.valueOf(arr);
            if(dict.containsKey(nbr)){
              union(i,dict.get(nbr));
            }
          }
          arr[j] = ori;
        }
      }
    }
    public int find(int a){
      while(a!=ids[a]){
        ids[a] = ids[ids[a]];
        a = ids[a];
      }
      return a;
    }
    public void union(int a,int b){
      int id1 = find(a);
      int id2 = find(b);
      ids[id2] = id1;
    }
    public boolean isConnected(String a,String b){
      if(!dict.containsKey(b)){
        return false;
      }
      char[] arr = a.toCharArray();
      for(int j=0;j<arr.length;j++){
        char ori = arr[j];
        for(char ch='a';ch<='z';ch++){
          if(ch==ori) continue;
          arr[j] = ch;
          String nbr = String.valueOf(arr);
          if(dict.containsKey(nbr)){
            int id1 = ids[dict.get(nbr)];
            int id2 = ids[dict.get(b)];
            if(id1==id2){
              return true;
            }
          }
        }
        arr[j] = ori;
      }

      return false;
    }
  }

  public boolean can2(String S,String T, List<String> allowed){
    //duplicates in allowed? target in allowed? length of word?
    //undirected graph, are S and T connected?, Time: allowed list+ E
    UFContainer uf = new UFContainer(allowed.size());
    uf.build(allowed);
    return uf.isConnected(S,T);
  }
}
