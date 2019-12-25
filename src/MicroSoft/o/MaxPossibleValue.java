package MicroSoft.o;
//https://leetcode.com/discuss/interview-question/398050/
public class MaxPossibleValue {
  public int func(int N){
    StringBuffer digits = new StringBuffer(Integer.toString(N));
    if(digits.charAt(0)=='-'){
      digits.deleteCharAt(0);
      return -buildNum(digits,false);
    }else{
      return buildNum(digits,true);
    }

  }

  public int buildNum(StringBuffer digits,boolean findMax){
    for(int i=0;i<digits.length();i++){
      int digit = digits.charAt(i) - '0';
      if(findMax && 5>digit){
        digits.insert(i,(char)(5+'0'));
        return Integer.parseInt(digits.toString());
      }else if(!findMax && 5<digit){
        digits.insert(i,(char)(5+'0'));
        return Integer.parseInt(digits.toString());
      }
    }

    digits.append((char)(5+'0'));
    return Integer.parseInt(digits.toString());
  }
  public static void main(String[] args) {
    MaxPossibleValue maxPossibleValue = new MaxPossibleValue();
    System.out.println(maxPossibleValue.func(-999));
    System.out.println(maxPossibleValue.func(999));
    System.out.println(maxPossibleValue.func(0));
    System.out.println(maxPossibleValue.func(-5536));
    System.out.println(maxPossibleValue.func(-5562));
    System.out.println(maxPossibleValue.func(5562));
    System.out.println(maxPossibleValue.func(5536));
    System.out.println(maxPossibleValue.func(268));
    System.out.println(maxPossibleValue.func(670));
  }

}
