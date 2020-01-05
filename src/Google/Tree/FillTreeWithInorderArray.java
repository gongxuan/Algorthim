package Google.Tree;

import java.util.*;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=564964&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class FillTreeWithInorderArray {

  // 如何建树；然后把树清空，每个node变成null，但是保留structure；如何把树的value保存成array，pre，in，post；
  // 已知已经保存好的inorder的array，如何按层来填满树。
  public static void main(String[] args) {
    int[] A = {1,2,3,4,5,6,7};
    FillTreeWithInorderArray ins  =new FillTreeWithInorderArray();
    Node cur = ins.buildTree(A);
    System.out.println(cur.val);
  }
  Node root;
  public Node buildTree(int[] A){
    Queue<Range> rangeQueue = new LinkedList();
    Queue<Node> treeQueue = new LinkedList<>();
    rangeQueue.add(new Range(0,A.length-1));
    while(!rangeQueue.isEmpty()){
      int val = cutRange(rangeQueue,A);
      build(treeQueue,val);
    }
    return root;
  }
  public int cutRange(Queue<Range> queue,int[] A){
    Range range = queue.poll();
    int mid = (range.low+range.hi)/2;
    //generate new range if possible
    if(range.low<mid){
      queue.add(new Range(range.low,mid-1));
    }
    if(mid<range.hi){
      queue.add(new Range(mid+1,range.hi));
    }
    return A[mid];
  }
  public void build(Queue<Node> treeQueue,int val){
    //if empty, create root and add it to the queue
    //every time, take one node from queue, and add a child to it.
    //if node doesn't have two children, we put it in the queue
    if(root==null){
      root = new Node(val);
      treeQueue.add(root);
      return ;
    }
    Node cur = treeQueue.peek();
    if(cur.left==null){
      cur.left = new Node(val);
      treeQueue.add(cur.left);
    }else if(cur.right==null){
      cur.right = new Node(val);
      treeQueue.add(cur.right);
      treeQueue.poll();
    }
  }
  class Range{
    int low,hi;
    public Range(int l,int h){
      this.low = l;
      this.hi = h;
    }

  }
  class Node{
    Node left,right;
    int val;
    public Node(int v){
      this.val = v;
    }

  }
}
