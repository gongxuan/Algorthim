package BackTracking;

import java.util.*;

public class StickerstoSpellWord {
    public String getHash(int[] arr){
        boolean allZero = true;
        StringBuffer value  = new StringBuffer();
        for(int i=0;i<26;i++){
            if(arr[i]>0){
                value.append((char)(i+'a')+":"+arr[i]+",");
                allZero = false;
            }
        }
        if(allZero)
            return "";
        return value.toString();
    }
    public int minStickers(String[] stickers, String target) {
        int sLen = stickers.length;

        int[][] letters = new int[sLen][26];
        //pre-processing: count up the frequency of occurrence of letters of every sticker
        for(int i=0;i<sLen;i++){
            String sticker  =stickers[i];
            for(int l=0;l<sticker.length();l++){
                letters[i][sticker.charAt(l)-'a']++;
            }
        }
//        int[] tLetters = new int[26];
//        for(int i=0;i<target.length();i++){
//            tLetters[target.charAt(i)-'a']++;
//        }
        //dfs: return minimum number of stickers used.
        Map<String,Integer> mem = new HashMap<>();
//        return dfs(letters,target,mem);
        return bfs(letters,target);
    }

    public int dfs(int[][] letters,String tStr,Map<String,Integer> mem){
        if(tStr.length()==0){
            return 0;
        }
        if(mem.containsKey(tStr)){
            return mem.get(tStr);
        }
        int[] tLetters = new int[26];
        for (char c : tStr.toCharArray())
            tLetters[c - 'a'] ++;
        int min = Integer.MAX_VALUE;

        for(int i=0;i<letters.length;i++){
            if(letters[i][tStr.charAt(0)-'a']==0){
                continue;
            }
            StringBuffer nxtTarget = new StringBuffer();
            for(int c=0;c<26;c++){
                for (int j = 0; tLetters[c] > 0 && j < tLetters[c] - letters[i][c]; j++){
                    nxtTarget.append((char)(c+'a'));
                }
            }
            int nxtMinStep = dfs(letters,nxtTarget.toString(),mem);
            if(nxtMinStep!=-1){
                min = Math.min(min,nxtMinStep);
            }
        }
        int curStep = (min == Integer.MAX_VALUE) ? -1 : 1 + min;
        mem.put(tStr,curStep);
//        System.out.println(hv+":"+mem.get(hv));
        return curStep;
    }
    public static void main(String[] args){
        StickerstoSpellWord s  = new StickerstoSpellWord();
        String[] sti  = {"safe","tire","gather","street","enter","believe"};
        System.out.println(s.minStickers(sti,"eventfat"));

    }
    public int bfs(int[][] letters,String target){
        Queue<String> queue = new LinkedList<>();
        queue.offer(target);
        int step = 0;
        Set<String> visited = new HashSet<>();
        while(!queue.isEmpty()){
            int size = queue.size();
            while(--size>=0){
                String curTarget  = queue.poll();
                int[] tLetters = new int[26];
                for (char c : curTarget.toCharArray())
                    tLetters[c - 'a'] ++;
                for(int i=0;i<letters.length;i++){
                    if(letters[i][curTarget.charAt(0)-'a']==0)  //pruning
                        continue;
                    StringBuffer nxtTarget = new StringBuffer();
                    for(int c=0;c<26;c++){
                        if(tLetters[c]==0)
                            continue;
                        for(int j=0;j<tLetters[c]-letters[i][c];j++){
                            nxtTarget.append((char)(c+'a'));
                        }
                    }
                    if(nxtTarget.length()==0){
                        return step+1;
                    }
                    String nxt = nxtTarget.toString();
                    if(!visited.contains(nxt)){
                        visited.add(nxt);
                        queue.offer(nxt);
                    }

                }
            }
            step++;
        }
        return -1;
    }
//    public boolean isSpelled(int[] target){
//
//    }
}
