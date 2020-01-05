package Google.String;

//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=568082&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
public class RmRedundantParentheses {
  //valid parentheses string
  //- given a valid parenthesis string, return the string without redundant parentheses;
  //((ab)) -> (ab)
  //(ab(x)) -> (ab(x)) (there is no redundant)
  //((a)bx) -> ((a)bx)
  //() ?
  //redundant: contiguous
  public String rm(String s){
    //two cases we need to rm P:
    //1. P are adjacent
    //2. Distance between curr ( and prev ( a
    return null;
  }
}
