package G.SegmentTree;

public class RangeSum {
  static class NumArray {
    class SegTree{
      Node root;
      int len;
      public SegTree(int[] m){
        len = m.length;
        root = build(m,0,len-1);
        System.out.println(root.sum);
      }
      class Node{
        int sum;
        int low,hi;
        Node left,right;
        public Node(int sum,int low,int hi){
          this.sum = sum;
          this.low = low;
          this.hi = hi;
        }
      }

      public Node build(int[] m,int start,int end){
        //divide and conquer
        if(start>end){
          return null;
        }else if(start==end){
          return new Node(m[start],start,start);
        }
        int mid = start + (end-start)/2;
        Node left = build(m,start,mid);
        Node right = build(m,mid+1,end);
        Node root = new Node(left.sum+right.sum,start,end);
        root.left = left;
        root.right = right;
        return root;
      }
      public int search(Node root,int low,int hi){
        //three cases
        if(root.low==low && root.hi==hi){
          return root.sum;
        }
        int mid = root.low+(root.hi-root.low)/2;
        if(hi<=mid){
          return search(root.left,low,mid);
        }else if(low>=mid+1){
          return search(root.right,mid+1,hi);
        }else{
          return search(root.left,low,mid) + search(root.right,mid+1,hi);
        }
      }

      public void update(Node root,int i,int val){
        if(root.low==i && root.hi==i){
          root.sum = val;
          return ;
        }
        int mid = root.low + (root.hi-root.low)/2;
        if(i<=mid){
          update(root.left,i,val);
        }else{
          update(root.right,i,val);
        }
        //update root's sum!
        root.sum = root.left.sum + root.right.sum;
      }
    }
    SegTree tree;
    public NumArray(int[] array) {
      tree = new SegTree(array);
    }

    public void update(int i, int val) {
      tree.update(tree.root,i,val);
    }

    public int sumRegion(int i, int j) {
      return tree.search(tree.root,i,j);
    }
  }

  public static void main(String[] args) {
    int[] matrix = {1,3,5};
    NumArray ins = new NumArray(matrix);
    System.out.println(ins.sumRegion(0,2));
    ins.update(1,2);
    System.out.println(ins.sumRegion(0,2));
  }
}
