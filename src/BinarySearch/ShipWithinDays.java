package BinarySearch;

import java.util.LinkedHashMap;

public class ShipWithinDays {
  public int shipWithinDays(int[] weights, int D) {
    int left = 0, right = 0;
    for (int w: weights) {
      left = Math.max(left, w);
      right += w;
    }
    while (left < right) {
      int mid = (left + right) / 2, need = 1, cur = 0;
      for (int w: weights) {
        if (cur + w > mid) {
          need += 1;
          cur = 0;
        }
        cur += w;
      }
      if (need > D) left = mid + 1;
      else right = mid;
    }
    return left;
  }

  public static void main(String[] args) {
    int[] w = {1,Integer.MAX_VALUE};
    int D = 2;
    ShipWithinDays s = new ShipWithinDays();
    System.out.println(s.shipWithinDays(w,D));
    LinkedHashMap<String,Integer> set  =new LinkedHashMap<>();

  }
}
