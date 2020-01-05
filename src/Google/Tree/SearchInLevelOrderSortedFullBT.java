package Google.Tree;
//            1
//      3           5
//   6     9     14   15

//16 17 18 22  24 26 30 35
class Node{
  Node left,right;
  int val;
  public Node(int v){
    val = v;
  }
}
public class SearchInLevelOrderSortedFullBT {
  //Search given element in a level order sorted full binary tree
  //Given root node, return true if the given element exists, otherwise return false.
  //brute force: BFS O(N)
  //Steps:
  //1. find out which row target in.
  //2. find mid in that row
  //3. do binary search
  //define helper functions
  public static void main(String[] args) {
    SearchInLevelOrderSortedFullBT ins = new SearchInLevelOrderSortedFullBT();
    Node root = new Node(1);
    root.left = new Node(3);
    root.right = new Node(5);
    root.left.left= new Node(6);
    root.left.right= new Node(9);
    root.right.left= new Node(14);
    root.right.right= new Node(15);


//    System.out.println(ins.find(root,3));
//    System.out.println(ins.find(root,14));
//    System.out.println(ins.find(root,15));
//    System.out.println(ins.find(root,9));
//    System.out.println("##################");
//    System.out.println(ins.find(root,2));
//    System.out.println(ins.find(root,18));
//    System.out.println(ins.find(root,10));
//    System.out.println(ins.find(root,0));

    System.out.println(ins.find1(root,3));
    System.out.println(ins.find1(root,14));
    System.out.println(ins.find1(root,15));
    System.out.println(ins.find1(root,9));
    System.out.println("##################");
    System.out.println(ins.find1(root,2));
    System.out.println(ins.find1(root,18));
    System.out.println(ins.find1(root,10));
    System.out.println(ins.find1(root,0));
  }

  boolean find(Node root,int target){
    if(root==null){
      return false;
    }else if(root.val==target){
      return true;
    }
    int row = findRow(root,target,0);
    if(row==-1){
      return false;
    }
    if(root.left==null || target<root.left.val){
      return false;
    }
    int mid = findMid(root.left,row-1);
    if(mid==target){
      return true;
    }else if(mid>target){
      return find(root.left,target);
    }
    return find(root.right,target);
  }

  int findRow(Node root,int target,int depth){
    if(root==null){
      return -1;
    }
    if(target<=root.val){
      return depth;
    }
    return findRow(root.right,target,depth+1);
  }

  int findMid(Node root,int depth){
    Node temp = root;
    while(depth>0){
      temp = temp.right;
      depth--;
    }
    return temp.val;
  }


  public boolean find1(Node root,int target){
    if(root==null){
      return false;
    }else if(root.left==null && root.right==null){
      return root.val==target;
    }

    Node right = root.right;
    Node left = root.left;
    if(left.val>target){
      return false;
    }
    int depth = 0;

    while(right!=null && target>right.val){		//
      right = right.right;
      depth++;
    }
    if(right==null){
      return false;	//!
    }
    //find mid
    Node mid = left;	//3

    while(depth>0){	//0
      mid = mid.right;
      depth--;
    }
    if(mid.val==target){	//true
      return true;	//!
    }else if(mid.val>target){
      return find(root.left,target);
    }
    return find(root.right,target);
  }



}
