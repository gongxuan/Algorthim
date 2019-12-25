package MicroSoft.o;

public class MinMoves {
  public int func(String s){
    //greedy, swap as less as possible
    int sLen = s.length();
    int min = 0;
    for(int i=0;i<sLen;){
      int j=i+1;
      int occurrence = 1;
      while(j<sLen && s.charAt(j)==s.charAt(i)){
        occurrence++;
        j++;
      }
      min += occurrence/3;
      i = j;
    }
    return min;
  }

  public static void main(String[] args) {
    MinMoves minMoves  =new MinMoves();
    System.out.println(minMoves.func("baaaaa"));
    System.out.println(minMoves.func("baaabbaabbba"));
    System.out.println(minMoves.func("baabab"));
    System.out.println(minMoves.func("aaa"));
    System.out.println(minMoves.func(""));
  }
}
