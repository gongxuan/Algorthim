package Google.Tree;
//https://www.1point3acres.com/bbs/thread-577888-1-1.html
public class FindRightSibling {
  static class Node {
    int data;
    Node left, right, parent;
    public Node(int data, Node parent)
    {
      this.data = data;
      left = null;
      right = null;
      this.parent = parent;
    }
  }
  public Node findRightSibling(Node curr,int[] lvl){
    if(curr==null){
      return null;
    }
    Node node = findAncestorWithRightChild(curr,lvl);
    Node res = findFirstNode(node,lvl[0]);
    if(res!=null){
      //found
      return res;
    }
    //couldn't find
    return findRightSibling(node,lvl);
  }
  public Node findAncestorWithRightChild(Node curr,int[] lvl){
    if(curr==null){
      return null;
    }
    while(curr.parent!=null &&(curr.parent.right==curr || curr.parent.right==null) ){
      curr = curr.parent;
      lvl[0]--;
    }
    if(curr.parent==null){
      return null;
    }
    return curr.parent.right;
  }

  public Node findFirstNode(Node root, int lvl){
    //find first node at level 0 with pre-order traversal
    if(root==null){
      return null;
    }
    if(lvl==0){
      return root;
    }
    Node left = findFirstNode(root.left,lvl+1);
    if(left!=null){
      return left;
    }
    return findFirstNode(root.right,lvl+1);
  }
  public static void main(String args[])
  {
    Node root = new Node(1, null);
    root.left = new Node(2, root);
    root.right = new Node(3, root);
    root.left.left = new Node(4, root.left);
    root.left.right = new Node(6, root.left);
    root.left.left.left = new Node(7, root.left.left);
    root.left.left.left.left = new Node(10, root.left.left.left);
//    root.left.right.right = new Node(9, root.left.right);
//    root.left.right.left = new Node(11, root.left.right);
    root.right.right = new Node(5, root.right);
    root.right.right.right = new Node(8, root.right.right);
//    root.right.right.right.right = new Node(12, root.right.right.right);
    FindRightSibling ins  =new FindRightSibling();
    Node res;
    // passing 10
    res = ins.findRightSibling(root.left.left.left.left, new int[]{0});
    System.out.println(res!=null?res.data:"null");
    //7
    res = ins.findRightSibling(root.left.left.left, new int[]{0});
    System.out.println(res.data);
  }
}
