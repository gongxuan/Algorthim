package Yelp.OA;

import java.util.*;
import java.util.List;

public class ReachableBusiness {
  public Business a = new Business("A", new HashMap<Business, Integer>());
  public Business b = new Business("B", new HashMap<Business, Integer>());
  public Business c = new Business("C", new HashMap<Business, Integer>());
  public Business d = new Business("D", new HashMap<Business, Integer>());
  public Business e = new Business("E", new HashMap<Business, Integer>());
  public Business f = new Business("F", new HashMap<Business, Integer>());
  public Business g = new Business("G", new HashMap<Business, Integer>());
  public Business h = new Business("H", new HashMap<Business, Integer>());

  public ReachableBusiness() {
    a.nearby_business.put(b, 2);
    b.nearby_business.put(a, 2);
    a.nearby_business.put(c, 3);
    c.nearby_business.put(a, 3);
    b.nearby_business.put(d, 5);
    d.nearby_business.put(b, 5);
    c.nearby_business.put(e, 1);
    e.nearby_business.put(d, 1);
    d.nearby_business.put(f, 1);
  }

  private class Business {
    public String name;
    public HashMap<Business, Integer> nearby_business;

    public Business(String name, HashMap<Business, Integer> nearby_business) {
      this.name = name;
      this.nearby_business = nearby_business;
    }
  }
  class Node{
    Business business;
    int dis;
    public Node(Business b,int dis){
      business = b;
      this.dis = dis;
    }
  }
  public List<String> find_reachable_businesses(Business start, int distance) {
    List<String> res = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    Queue<Node> minHeap = new PriorityQueue<>((a,b)->a.dis -b.dis);
    minHeap.add(new Node(start,0));
    while(!minHeap.isEmpty()){
      Node curr = minHeap.poll();
      res.add(curr.business.name);
      visited.add(curr.business.name);
      for(Map.Entry<Business,Integer> nbr : curr.business.nearby_business.entrySet()){
        if(visited.contains(nbr.getKey().name))  continue;
        int nextDis = nbr.getValue()+curr.dis;
        if(nextDis<=distance){
          minHeap.add(new Node(nbr.getKey(),nextDis));
        }
      }
    }
    return res;
  }

  public void output() {
    List<String> res = find_reachable_businesses(a, 6);
    for (String s: res)
      System.out.print(s + " ");
    System.out.println();
  }
  public static void main(String[] args) {
    ReachableBusiness ins = new ReachableBusiness();
    ins.output();
  }
}
