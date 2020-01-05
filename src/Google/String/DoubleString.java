package Google.String;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=571351&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class DoubleString {
//给定一个超级大的字符串s，找到他的最大 “double-string” 子字符串的长度。
//例：s = “abcabccd” => 6 （”abcabc”和“cc”均为“double-string”，输出更长的长度6）
  //can't use KMP, because input string is too big, space is limited
  //brute force N^3
  public int findLargestDoubleString(String s){
    int m = s.length()/2;
    while(m>0){
      if(hasDoubleStrWithSizeM(s,0,s.length()-1,m)){
        return m;
      }
      m--;
    }
    return 0;
  }
  public boolean hasDoubleStrWithSizeM(String s,int left,int right,int m){
    int p1=left,p2=left+m;
    int matched = 0;
    while(p2<=right){
      if(s.charAt(p1)==s.charAt(p2)){
        matched++;
        if(matched==m){
          return true;
        }
      }else{
        matched = 0;
      }
      p1++;
      p2++;
    }
    return false;
  }

  public static void main(String[] args) {
    String s = "abcabccd";
    DoubleString ins = new DoubleString();
    System.out.println(ins.findLargestDoubleString(s));
  }
}
