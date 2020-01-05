package Google.String;
//https://www.1point3acres.com/bbs/thread-561726-1-1.html
public class Palindrome {
//第一轮 给两个字符串A和B
//现在在A B的同一个位置cut
//问A的前半部分和B的后半部分 或者A的后半部分和B的前半部分能不能构成Palindrome
//follow up： 优化time complexity到O(n)， space complexity到O(1)
  //https://cs.stackexchange.com/questions/109662/divide-two-strings-to-form-palindrome
  public boolean isPalindrome(String A,String B){
    //A0 | A1
    //B0 | B1
    //let's assume B0 and A1 can make up a palindrome. len(B0)=len(A1)
    //and we need to cut in same place. so len(B0)=len(A0) , so len(B0)=len(A0)=len(A1),
    // and len(B1) doesn't have to be same. it could be longer or shorter than other 3.
    //this means we need to cut A in half.
    //similarly, if we want to make up a palindrome by A0 and B1. we need to cut B from middle.
    return false;
  }
  public boolean isPHelper(String A,String B){
    int mid = A.length()/2;
    return false;
  }


}
