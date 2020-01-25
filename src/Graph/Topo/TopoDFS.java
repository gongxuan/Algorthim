package Graph.Topo;

import java.util.*;

public class TopoDFS {

  static class Graph {

    private int V;   // Number of vertices
    private List<Integer> adj[]; // Adjacency List

    public Graph(int v) {
      V = v;
      adj = new LinkedList[v];
      for (int i = 0; i < v; ++i) {
        adj[i] = new LinkedList();
      }
    }

    public void addEdge(int v, int w) {
      adj[v].add(w);
    }

    public void topologicalSortUtil(int curr, boolean visited[], Stack stack) {
      visited[curr] = true;
      for (Integer nbr : adj[curr]) {
        if (!visited[nbr]) {
          topologicalSortUtil(nbr, visited, stack);
        }
      }
      stack.push(curr);
    }

    public void topologicalSort() {
      Stack stack = new Stack();
      boolean visited[] = new boolean[V];
      Arrays.fill(visited, false);

      for (int v = 0; v < V; v++) {
        if (!visited[v]) {
          topologicalSortUtil(v, visited, stack);
        }
      }

      while (!stack.empty()) {
        System.out.print(stack.pop() + " ");
      }
    }
  }

  public static void main(String args[]) {
    Graph g = new Graph(6);
    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);

    System.out.println("Following is a Topological " +
        "sort of the given graph");
    g.topologicalSort();
  }
}
