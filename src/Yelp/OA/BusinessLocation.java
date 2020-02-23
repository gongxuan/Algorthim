package Yelp.OA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BusinessLocation {
//题目的意思是输入一堆stores，里面存了商店名称，城市，ID。一个城市可能有多个不同ID但是相同名字的store。
//    输出一个List，里面是某个城市拥有的所有store名称和对应的数量，按照数量从少到多排列（这里存疑，不知道是从少到多还是从多到少），如果数量相同则按照字母顺序排列。
//
//    像这样：
//        stores.add(new Store("Walmart", "San Jose", 1));
//        stores.add(new Store("Walmart", "Los Angeles", 2));
//        stores.add(new Store("Bestbuy", "San Jose", 3));
//        stores.add(new Store("Bestbuy", "Los Angeles", 4));
//        stores.add(new Store("Bestbuy", "Dallas", 5));
//        stores.add(new Store("Bed&Bath", "San Jose", 6));
//        stores.add(new Store("Bestbuy", "San Jose", 7));
//        stores.add(new Store("Bestbuy", "San Jose", 8));
//        stores.add(new Store("Walmart", "San Jose", 9));
//        stores.add(new Store("Walgreen", "San Jose", 10));
//        stores.add(new Store("Walgreen", "San Jose", 11));
//
//    如果需要查询San Jose则返回：
//        Bed&Bath:1
//        Walgreen:2
//        Walmart:2
//        Bestbuy:3
//
//    解法：
//    1. 遍历stores将要查询的城市的所有商店名称和对应的数量用HashMap存下来。
//    2. 使用List<Map.Entry<String,Integer>>将HashMap排序保存并返回（需要自己写Comparator）。
public List<Store> stores;

  private class Store {
    String name;
    String city;
    int businessID;

    public Store(String n, String c, int b) {
      name = n;
      city = c;
      businessID = b;
    }
  }

  public BusinessLocation() {
    stores = new ArrayList<>();
    stores.add(new Store("Walmart", "San Jose", 1));
    stores.add(new Store("Walmart", "Los Angeles", 2));
    stores.add(new Store("Bestbuy", "San Jose", 3));
    stores.add(new Store("Bestbuy", "Los Angeles", 4));
    stores.add(new Store("Bestbuy", "Dallas", 5));
    stores.add(new Store("Bed&Bath", "San Jose", 6));
    stores.add(new Store("Bestbuy", "San Jose", 7));
    stores.add(new Store("Bestbuy", "San Jose", 8));
    stores.add(new Store("Walmart", "San Jose", 9));
    stores.add(new Store("Walgreen", "San Jose", 10));
    stores.add(new Store("Walgreen", "San Jose", 11));
  }

  public List<Map.Entry<String,Integer>> findStores(List<Store> stores, String city) {
    Map<String, Integer> map = new HashMap<>();
    for (Store store : stores) {
      if (store.city.equals(city)) {
        map.put(store.name, map.getOrDefault(store.name,0)+1);
      }
    }
    List<Map.Entry<String,Integer>> res = new ArrayList<>(map.entrySet());
    Collections.sort(res, new Comparator<Entry<String,Integer>>() {
      public int compare(Map.Entry<String, Integer> e1,
          Map.Entry<String, Integer> e2) {
        if(e1.getValue() != e2.getValue()){
          return e1.getValue()-e2.getValue();
        }
        return e1.getKey().compareTo(e2.getKey());
      }
    });
    return res;
  }

  public void output() {
    List<Map.Entry<String, Integer>> res = findStores(stores, "San Jose");
    for (Map.Entry<String, Integer> mapping : res) {
      System.out.println(mapping.getKey() + ":" + mapping.getValue());
    }
  }

  public static void main(String[] args) {
    BusinessLocation ins = new BusinessLocation();
    ins.output();
  }

}
