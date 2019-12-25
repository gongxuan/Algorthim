package BackTracking;

import java.util.*;

public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        //Use Trie tree to store suffix of every word, and then use every word
        //to traverse trie.
        //1. word.len > trie.current depth.
        //is remaining substring of word panlindrome? yes, return {word'sindex,branch index}
        //How to know branch index? when node is a leaf, we store the branch index, otherwise
        // index= =-1 default
        //2. word.len< trie current depth
        //How do we know remaing panlindrom substrings' indecis of this node?
        //Every node in trie will store list of indecis which represent a list of remaining
        //pandindrom substrings.
        Container c = new Container();
        for(int i=0;i<words.length;i++){
            c.insert(words[i],i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<words.length;i++){
            res.addAll(c.find(words[i],i,words));
        }
        return res;
    }
    class Trie{
        Map<Character,Trie> next;
        //if current node is a word's end, wordIndex is the index.
        int wordIndex;
        //store a list of panlidrom substring of remaining branches
        List<Integer> plList;
        public Trie(){
            next = new HashMap<>();
            wordIndex = -1;
            plList = new ArrayList<>();
        }
    }
    class Container{
        Trie root;
        public Container(){
            root = new Trie();
        }
        public void insert(String s,int wordIndex){
            Trie tem = root;
            for(int i=s.length()-1;i>=0;i--){
                if(isPan(s,0,i)){
                    tem.plList.add(wordIndex);
                }
                if(!tem.next.containsKey(s.charAt(i))){
                    tem.next.put(s.charAt(i),new Trie());
                }
                tem  = tem.next.get(s.charAt(i));

            }
            tem.wordIndex = wordIndex;
            tem.plList.add(wordIndex);
        }
        public List<List<Integer>> find(String s,int wordIndex,String[] words){
            //s should starts with a prefix string in trie tree
            List<List<Integer>> res = new ArrayList<>();
            Trie tem = root;
            for(int i=0;i<s.length();i++){
                //case1
                if(tem.wordIndex>=0 && tem.wordIndex!=wordIndex && isPan(s,i,s.length()-1)){
                    res.add(Arrays.asList(wordIndex,tem.wordIndex));
                }
                if(!tem.next.containsKey(s.charAt(i))){
                    return res;
                }
                tem = tem.next.get(s.charAt(i));
            }
            //case2
            List<Integer> remainingPanliIndex = tem.plList;
            for(int index :remainingPanliIndex ){
                if(index!=wordIndex){
                    res.add(Arrays.asList(wordIndex,index));
                }
            }
            return res;
        }
    }
    public boolean isPan(String s,int left,int right){
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args){
        PalindromePairs p = new PalindromePairs();
        //["abcd","dcba","lls","s","sssll"]
        //["a","abc","aba",""]
        p.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"});
    }
}
