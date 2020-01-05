package Google;

//http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=437743&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
public class TrieTree {
    private class Trie{
        //space 27^n   the nth power of 27
        Trie[] children;
        boolean isLeaf;
        public Trie(){
            isLeaf = false;
            children = new Trie[128];
        }
    }
    public TrieTree() {
        root = new Trie();
    }
    Trie root;
    public void insert(String word) {
        if(word==null) {
            return ;
        }
        Trie tem = this.root;
        insertHelper(word.toCharArray(),0,tem);
    }
    private void insertHelper(char[] arr,int index,Trie trie) {
        //O(2^n)    Space O(n)
        if(index==arr.length) {
            trie.isLeaf = true;
            return ;
        }
        if(trie.children[arr[index]]==null) {
            trie.children[arr[index]] = new Trie();
        }
        if(trie.children[(int)'.']==null) {	// .
            trie.children[(int)'.'] = new Trie();
        }
        insertHelper(arr,index+1,trie.children[arr[index]]);
        insertHelper(arr,index+1,trie.children[(int)'.']);
    }
    public boolean search(String word) {
        //T=O(n)
        if(word==null) {
            return false;
        }
        Trie tem = this.root;
        for(int i=0;i<word.length();i++) {
            if( tem.children[word.charAt(i)]!=null) {
                tem  = tem.children[word.charAt(i)];
            }else {
                return false;
            }
        }
        return tem.isLeaf;
    }
    public void delete(String word){
        //1. is this word in the tree?
        //2.
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TrieTree ad = new TrieTree();
        ad.insert("cat");
        ad.insert("cad");
        System.out.println(ad.search("...."));
        System.out.println(ad.search(".."));
        System.out.println(ad.search("..."));
    }

}