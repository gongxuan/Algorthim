package Google.Tree;

import java.util.*;
class NNode {
  public int val;
  public List<NNode> children;

  public NNode() {}

  public NNode(int _val) {
    val = _val;
  }

  public NNode(int _val, List<NNode> _children) {
    val = _val;
    children = _children;
  }
}

public class SerializeandDeserializeNaryTree {


  public static void main(String[] args) {
    NNode root = new NNode(1);
//    root.children = new ArrayList<>();
//    NNode first = new NNode(3);
//    first.children = new ArrayList<>();
//    root.children.add(first);
//    root.children.add(new NNode(2));
//    root.children.add(new NNode(4));
//    first.children.add(new NNode(5));
//    first.children.add(new NNode(6));
    SerializeandDeserializeNaryTree ins = new SerializeandDeserializeNaryTree();
    String s = ins.serialize(root);
    System.out.println(s);
    NNode root1 = ins.deserialize(s);
    System.out.println(root1.val);
  }
  //know children num for every node
  //how many nodes in tree?
  //BFS: 1,3,3,2,2,0,4,0,5,0,6,0,
  //step:

  public String serialize(NNode root) {
    if(root==null){
      return "";
    }
    Queue<NNode> queue = new LinkedList<>();
    StringBuffer res = new StringBuffer();
    queue.offer(root);
    if(root==null){
      System.out.print("root null");
    }
    while(!queue.isEmpty()){
      int size = queue.size();
      while(size-->0){
        NNode cur = queue.poll();

//        System.out.print(cur.val+" ");
        //assume children not null
        if(cur.children==null){
          res.append(cur.val+","+0+",");
        }else{
          res.append(cur.val+","+cur.children.size()+",");
          for(NNode child : cur.children){
            queue.offer(child);
          }
        }

      }
//      System.out.println(" ");
    }

//    System.out.println(" Ser");

    return res.toString();
  }

  public NNode deserialize(String data) {
    if(data.equals("")){
      return null;
    }
    // System.out.println(data);
    //data always ends with ","
    String[] arr = data.split(",");
    NNode root = new NNode(Integer.parseInt(arr[0]));
    root.children = new ArrayList<>();
    int len = arr.length;
    System.out.println(len);

    Queue<DNode> queue = new LinkedList<>();
    queue.offer(new DNode(root,Integer.parseInt(arr[1])));
    int i = 2;
    while(i<len-1){
      DNode cur = queue.poll();
      // if(cur.numChd==0){
      //     continue;
      // }
      cur.node.children = new ArrayList<>();  //even for leaf

      for(int c=0;c<cur.numChd;c++){

        NNode chd = new NNode(Integer.parseInt(arr[i++]));
        chd.children = new ArrayList<>();
        cur.node.children.add(chd);

//        if(Integer.parseInt(arr[i])==0){
//          i++;
//          continue;
//        }
        queue.offer(new DNode(chd,Integer.parseInt(arr[i++])));
      }
    }
    System.out.println("end");
    // serialize(root);
    return root;
  }
  class DNode{
    NNode node;
    int numChd;
    public DNode(NNode v,int n){
      node = v; numChd = n;
    }
  }
}
