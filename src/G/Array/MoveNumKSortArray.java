package G.Array;
//https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=566313&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3088%255D%255Bvalue%255D%3D1%26searchoption%255B3088%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=1
public class MoveNumKSortArray {
//给定一个数组，长度为n，每次操作可以选择一个数将其向前或向后移动最多k个位置，要求用m次操作将序列排得尽可能有序。
  public int[] move(int[] A,int k,int m){
    //Let's say we move a number to the front. This move can reduce x number of unsorted pairs.
    // This move won't affect any other pairs sequence.So total number of unsorted pairs should minus x .
    // If two moves can reduce same number of unsorted pairs. we can take any one of them.
    // 所以每次move 只要能保证，能减少最多的 unsorted pairs，  无论移哪个都一样的。
    // the key is to reduce unsorted pairs as much as possible  by each move.
    // In each move，we don't need to compare all pairs, we only compare values in a window which size is K.
    // In this window, we enumerate all pairs, it takes K^2.  so. it takes  k^2 * N time  in this turn.
    // Totally  (k^2 * N)*m
    return null;
  }
}
