package Google.Graph;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=566124&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3088%255D%255Bvalue%255D%3D1%26searchoption%255B3088%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=1
public class CompareAttributes {
//1st round: Given each object has four attribute, and each attribute can be value 0 or 1 or 2.
// First question: if you are given 3 objects, assuming for each attribute, the objects either all have
// different attribute value, or they have all same attribute value. Determine whether an object list is valid.
// For example:
//A valid example:          An invalid example:
//obj1: 0, 1, 2, 0            obj1: 0, 1, 2, 0
//obj2: 1, 1, 0, 0            obj2: 1, 1, 1, 0
//obj3: 2, 1, 1, 0            obj3: 1, 0, 0, 0
//就是每组你竖着看，each column either all same, or all different. 每一列如果既有一样的又有不一样的，就不valid
//其实准确的说应该是4个attribute都valid （因为我们是每次对比一个column，一个column代表一个attribute）
//follow up, if you are given a list of objects, with 4 attributes, and each attribute have value 1 or 2 or 3.
// Design an algorithm to choose 3 objects from the list such that they form a valid set of objects.
  //brute force: pick random 3 objs from list N^2

  public boolean isValid(int[][] objs){
    //we can check one column once a time.
    //use a set to store values. if size is 0 or 3, it's valid, otherwise it's invalid
    //another way is to use binary integer to represent values
    //column values: 0,1,2 => 1,2,4 => & => 7(111)
    //obj1: 0, 1, 2, 0            obj1: 0, 1, 2, 0
    //obj2: 1, 1, 0, 0            obj2: 1, 1, 1, 0
    //obj3: 2, 1, 1, 0            obj3: 1, 0, 0, 0
    //      7, 2, 7, 1                  3, 3, 7, 2

    return false;
  }
}
