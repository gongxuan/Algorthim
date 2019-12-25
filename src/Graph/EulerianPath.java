package Graph;

import java.util.*;

public class EulerianPath {
  public List<String> findItinerary(String[][] tickets) {
    //find a path that visit every edge extactly once. Eulerian Path O(E)
    if(tickets==null){
      return new LinkedList<>();
    }
    Map<String,PriorityQueue<String>> graph = new HashMap<>();
    for(String[] ticket : tickets){
      String from = ticket[0];
      String to = ticket[1];
      graph.computeIfAbsent(from,k->new PriorityQueue<>()).add(to);
    }
    List<String> itinerary = new LinkedList<>();
    helper(graph,itinerary,"A");
    System.out.println("");
    for(String s: itinerary){
      System.out.print(s+" ");
    }
    return itinerary;
  }
  public void helper(Map<String,PriorityQueue<String>> graph,List<String> itinerary,String curAP){
    System.out.print(curAP+" ");
    while(graph.containsKey(curAP) && !graph.get(curAP).isEmpty()){

      helper(graph,itinerary,graph.get(curAP).poll());
    }
    itinerary.add(0,curAP);
  }

  public static void main(String[] args) {
    String[][] tickets = {{"A","B"},{"B","E"},{"E","D"},{"D","B"},{"B","C"},{"C","A"}};
    EulerianPath eulerianPath  =new EulerianPath();
    eulerianPath.findItinerary(tickets);
  }
}
