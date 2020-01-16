package Google.Tree;

public class ConstructBinaryTreeWithInorderList {
//lc 1110，follow up 是给一个balenced binary search tree 的 list of inorder traversal，重建树，
// 再follow up是如果inorder traversal不是list是streaming，并且已知树的总节点个数，重建树。
  public TreeNode build(int[] list,int low,int hi){
    if(low>hi){
      return null;
    }
    int mid = low + (hi-low)/2;
    TreeNode root = new TreeNode(list[mid]);
    root.left = build(list,low,mid-1);
    root.right = build(list,mid+1,hi);
    return root;
  }

  public static void main(String[] args) {
    int[] list = {1,2,3,4,5,6,7};
    ConstructBinaryTreeWithInorderList ins  = new ConstructBinaryTreeWithInorderList();
    TreeNode root = ins.build(list,0,list.length-1);
    ins.print(root);
  }
  public void print(TreeNode root){
    if(root==null)
      return ;
    print(root.left);
    System.out.println(root.val+" ");
    print(root.right);
  }

}
