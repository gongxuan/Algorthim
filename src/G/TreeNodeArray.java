package G;
import java.util.*;

//http://www.1point3acres.com/bbs/thread-439911-1-1.html
//第一题是给出一个array of treenode，判断array里面的节点是否属于同一棵树并且包含这棵树的所有节点。这道题非常简单，set秒掉。
public class TreeNodeArray {
    public boolean check(TreeNode[] arr) {
        int len = arr.length;
        Map<TreeNode,Integer> indegree = new HashMap<>(len);
        Set<TreeNode> arrSet = new HashSet<>(len);
        Set<TreeNode> childrenSet = new HashSet<>(len);
        for(TreeNode node : arr) {
            arrSet.add(node);
            indegree.put(node, 0);	//initialize indegree as 0
        }
        for(TreeNode node :arr) {
            if(node.left!=null) {
                if(childrenSet.contains(node.left)) {	//loop, a child can not have two parents
                    return false;
                }
                childrenSet.add(node.left);
                if(!indegree.containsKey(node.left)) {	//arr doesn't contain all nodes in tree
                    return false;
                }
                indegree.put(node.left, indegree.get(node.left)+1);
            }
            if(node.right!=null) {
                if(childrenSet.contains(node.right)) {	//loop, a child can not have two parents
                    return false;
                }
                childrenSet.add(node.right);
                if(!indegree.containsKey(node.right)) {	//arr doesn't contain all nodes in tree,
                    return false;
                }
                indegree.put(node.right, indegree.get(node.right)+1);
            }
        }
        if(len-1!=childrenSet.size()){  //check the number,but this is only a necessary condition
            return false;
        }
        TreeNode root = null;
        for(TreeNode node : indegree.keySet()) {
            if(indegree.get(node)==0) {
                if(root!=null) {		//multiple roots
                    return false;
                }
                root = node;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TreeNode root1=  new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(4);
//		G.TreeNode root2=  new G.TreeNode(2);
        root1.left.left  =new TreeNode(5);
        root1.left.right  =new TreeNode(6);
        TreeNode[] arr = new TreeNode[4];
        arr[0] = root1;
        arr[1] = root1.left.left;
        arr[2] = root1.left;
        arr[3] = root1.right;
        TreeNodeArray ta = new TreeNodeArray();
        System.out.println( ta.check(arr) );
    }

}