package Google.Subset;

public class MaxScoreFormedByLetters {


  int max;
  int[] score;
  public int findMaxScore(String[] words,Character[] letters,int[] score){
    max = 0;
    this.score = score;
    int[] freq  = new int[26];

    for(int i=0;i<letters.length;i++){
      freq[letters[i]-'a']++;
    }
    dfs(words,0,freq,0);
    return max;
  }

  public int  dfs(String[] words,int start,int[] freq,int curScore){
    if(start==words.length){
      max = Math.max(max,curScore);
      return curScore;
    }
    int curMax = 0;

    int len = words.length;
    for(int i=start;i<len;i++){
      String word = words[i];

      int tempScore = useWord(word,freq);
      if(tempScore!=-1){
        curMax = Math.max(curMax,
            dfs(words,i+1,freq, curScore+tempScore));
      }
    }

    return curMax;
  }


  public int useWord(String word,int[] freq){
    int[] copy = freq.clone();
    int sum = 0;
    for(int i=0;i<word.length();i++){
      char ch = word.charAt(i);
      if(freq[ch-'a']>0){
        freq[ch-'a']--;
        sum += score[ch-'a'];
      }else{
        freq = copy;
        return -1;
      }
    }
    return sum;
  }

}
