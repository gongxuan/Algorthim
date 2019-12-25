package Graph;

import java.util.*;

//https://www.geeksforgeeks.org/hierholzers-algorithm-directed-graph/
//LeetCode 332
public class EulerianCircuit {
    //Assume the graph contains Eulerian Circuit, find a itinerary that will visit every airport and starts and ends on same vertex.
    Map<Integer, List<Integer>> graph = new HashMap<>();
    public List<Integer> findItinerary(List<int[]> tickets) {
        for (int[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new LinkedList<>()).add(ticket[1]);
        }
        List<Integer> circuit = new LinkedList<>();
        Stack<Integer> curPath = new Stack<>();
//        curPath.add(0);
        recursion(curPath, circuit, 0);
        return circuit;
    }

    public List<Integer> iteration() {
        return null;
    }

    public void recursion(Stack<Integer> curPath,
                          List<Integer> circuit, int curVertex) {

        if (graph.containsKey(curVertex) && !graph.get(curVertex).isEmpty()) {
            curPath.push(curVertex);
            recursion(curPath, circuit, graph.get(curVertex).remove(0));
        } else {
            //back to start vertex
            circuit.add(0,curVertex);
            if(!curPath.isEmpty()){
                int preVertex = curPath.pop();
                recursion(curPath, circuit, preVertex);
            }
        }
    }

    public static void main(String[] args) {
        List<int[]> tickets = new LinkedList<>();
        tickets.add(new int[]{0, 1});
        tickets.add(new int[]{1, 2});
        tickets.add(new int[]{2, 0});
        tickets.add(new int[]{4, 1});
        tickets.add(new int[]{3, 4});
        tickets.add(new int[]{1, 3});
        EulerianCircuit ec = new EulerianCircuit();
        ec.findItinerary(tickets);
        PriorityQueue<Node> queue = new PriorityQueue<>(3, (a,b)->a.dis-b.dis);
    }
    public class Node{
        int dis;

    }
}
