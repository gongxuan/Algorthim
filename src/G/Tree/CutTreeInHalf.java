package G.Tree;
import java.util.*;

class UnDNode{
  List<UnDNode> nbrs;
  int val;
  public UnDNode(int v){
    val = v;
    nbrs = new ArrayList<>();
  }
}
public class CutTreeInHalf {
//第四个问的是一个undirected tree，然后从中间截断，两边node相等，问在哪儿截断。
  public UnDNode[] cut(UnDNode root){
    //does node in root contain other info,number of nodes in subtree?
    //number of nodes is odd?
    //scan tree twice: 1. find total num, 2. find a node that subtree contains total/2;
    res  = new UnDNode[2]; //[Parent,child]
    Set<UnDNode> visited = new HashSet<>();
    visited.add(root);
    total = countNodes(root,visited);
    visited = new HashSet<>();
    visited.add(root);
    find(root,visited);
    return res;
  }
  UnDNode[] res;
  int total;
  public int countNodes(UnDNode root,Set<UnDNode> visited){
    if(root==null){
      return 0;
    }
    visited.add(root);
    int count = 1;
    for(UnDNode nbr : root.nbrs){
      if(visited.contains(nbr)){
        continue;
      }
      visited.add(nbr);
      count += countNodes(nbr,visited);
    }
    return count;
  }
  public int find(UnDNode root,Set<UnDNode> visited){
    if(root==null){
      return 0;
    }

    visited.add(root);
    int count = 1;
    for(UnDNode nbr : root.nbrs){
      if(visited.contains(nbr)){
        continue;
      }
      visited.add(nbr);
      int nbrCount = countNodes(nbr,visited);
      if(nbrCount==total/2){
        res[0] = root;
        res[1] = nbr;
      }
      count += nbrCount;
    }
    return count;
  }

  public static void main(String[] args) {
    CutTreeInHalf ins = new CutTreeInHalf();
    UnDNode root = new UnDNode(1);
    UnDNode left = new UnDNode(2);
    UnDNode right = new UnDNode(5);
    UnDNode ll = new UnDNode(4);
    root.nbrs.add(left);
    root.nbrs.add( right);
    left.nbrs.add(root);
    left.nbrs.add(ll);
    right.nbrs.add(root);
    ll.nbrs.add(left);
    UnDNode[] res=  ins.cut(root);
    System.out.println(res[0].val+" "+res[1].val);  //1,2
  }
}
