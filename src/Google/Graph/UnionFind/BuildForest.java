package Google.Graph.UnionFind;
import java.util.*;

public class BuildForest {
//第一轮：感觉很高级一个的三哥。很nice
//给一个file，file的每行代表 parent id， child id， value。指代一个树里一个node的关系。要求建一个forest然后forest里每个tree都valid
public static void main(String[] args) {
  List<int[]> file = new ArrayList<>();
  file.add(new int[]{0,1,1});
  file.add(new int[]{0,2,2});
  file.add(new int[]{3,4,4});
  file.add(new int[]{1,2,2});
  BuildForest ins = new BuildForest();
  List<Node> res  = ins.buildForest(file);
  ins.print(res);
}
  public List<Node> buildForest(List<int[]> file){
    Forest forest = new Forest();
    while(!file.isEmpty()){
      List<int[]> next = new ArrayList<>();
      Map<Integer,Integer> childToParent = new HashMap<>(); //<child,parent>
      Map<Integer,Node> idToNode = new HashMap<>();
      UF uf = new UF();
      for(int[] line : file){
        int p = line[0],c = line[1],val=line[2];
        if(childToParent.containsKey(c) && childToParent.get(c)!=p){
          //add to next tree or create new tree
          next.add(line);
          continue; //skip this line
        }else if(!uf.union(c,p)){
          next.add(line); //cycle
          continue;
        }

        childToParent.put(c,p);
        if(!idToNode.containsKey(c)){
          idToNode.put(c,new Node(val));
        }
        if(!idToNode.containsKey(p)){
          idToNode.put(p,new Node(p));
        }
        //create edge
        idToNode.get(p).children.add(idToNode.get(c));
      }
      forest.roots.addAll(uf.findRoots(idToNode));
      file = next;
    }
    return forest.roots;
  }

  class Forest{
    List<Node> roots = new ArrayList<>();
  }
  class UF{
    Map<Integer,Integer> ids;
    public UF(){
      ids = new HashMap<>();
    }
    public int find(int id){
      if(!ids.containsKey(id)){
        ids.put(id,id);
        return id;
      }
      return ids.get(id);
    }
    public boolean union(int i1,int i2){
      int id1 = find(i1);
      int id2 = find(i2);
      if(id1==id2){
        return false; //can't union
      }
      ids.put(id1,id2);
      return true;
    }
    public List<Node> findRoots(Map<Integer,Node> idToNode){
      List<Node> roots = new ArrayList<>();
      for(Map.Entry<Integer,Integer> e:ids.entrySet()){
        if(e.getKey()==e.getValue()){
          //root
          roots.add(idToNode.get(e.getKey()));
        }
      }
      return roots;
    }
  }
  public void print(List<Node> list){
    for(Node node : list){
      System.out.print(node.val+" ");
    }
    System.out.println();
  }
}
class Node{
  List<Node> children;
  int val;
  public Node(int v){
    this.val = v;
    children = new ArrayList<>();
  }
}