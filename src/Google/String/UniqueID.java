package Google.String;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=564964&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class UniqueID {
  //写一个class可以get server ID，要求每个ID尽可能短，要是unique的，要求可以使用最多n个重复的character。
  static int N;
  String lastID;
  public UniqueID(int N){
    //assume can only use 26 chars
    //base 26, calculator
    this.N = N;
    lastID = "a";
  }

  public String getID(){
    String res = lastID;
    lastID = plusOne();
    return res;
  }
  public String plusOne(){
    char[] arr = lastID.toCharArray();
    StringBuffer next= new StringBuffer();
    int carry = 1;
    for(int i=arr.length-1;i>=0;i--){
      int cur = (arr[i]-'a') + carry;
      carry = cur/26;
      cur = cur%26;
      next.insert(0,(char)(cur+'a'));
    }
    if(carry>0){
      next.insert(0,(char)(carry+'a'-1));
    }
    return next.toString();
  }

  public static void main(String[] args) {
    UniqueID ins = new UniqueID(2);
    for(int i=1;i<=27*26;i++){
      System.out.print(ins.getID()+",");
    }

  }
}
