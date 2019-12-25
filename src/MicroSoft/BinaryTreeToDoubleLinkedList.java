package MicroSoft;

import G.TreeNode;

import java.util.Stack;

public class BinaryTreeToDoubleLinkedList {
    //inorder traversal, concatenate previous node with current node
    public TreeNode convertBT2DDL(TreeNode root){
        TreeNode dummyHead = new TreeNode(0);
        TreeNode pre = dummyHead;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur!=null){
            if(cur!=null){
                while(cur!=null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }else{
                cur = stack.pop();
                pre.right = cur;
                cur.left = pre;
                pre = cur;
                cur = cur.right;
            }
        }
        TreeNode head = dummyHead.right;
        head.left = null;
        return head;
    }
    public static void main(String[] args){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right= new TreeNode(6);
        BinaryTreeToDoubleLinkedList b = new BinaryTreeToDoubleLinkedList();
        TreeNode head = b.convertBT2DDL(root);
        b.print(head);
    }
    public void print(TreeNode head){
        TreeNode tem = head;
        TreeNode pre = null;
        while(tem!=null){
            pre = tem;
            System.out.print(tem.val+" ");
            tem = tem.right;
        }
        System.out.println();
        while(pre!=null){
            System.out.print(pre.val+" ");
            pre = pre.left;
        }
    }
}
