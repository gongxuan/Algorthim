package G.MinMax;

import java.util.*;

public class CatAndMouse {
  final int DRAW = 0, MOUSE_WIN = 1, CAT_WIN = 2,MOUSE_TURN = 1, CAT_TURN = 2;
  int[][][] childrenNum = new int[50][50][3];
  int N;
  public int catMouseGame(int[][] graph) {
    N = graph.length;
    //status[node] : who wins in the end if we start from this node
    int[][][] status = new int[50][50][3];  //// status[mouse_location][cat_location][turn]
    createChildrenNum(graph);
    // enqueued : all nodes that we know who wins in the end. Nodes with DRAW status is not in this queue.
    Queue<int[]> queue = new LinkedList();
    for (int i = 0; i < N; ++i) {
      for (int t = 1; t <= 2; ++t) {
        status[0][i][t] = MOUSE_WIN;  //The mouse wins if it is at the hole (position 0).
        queue.add(new int[]{0, i, t, MOUSE_WIN});
        if (i > 0) {
          status[i][i][t] = CAT_WIN;  //The cat wins if mouse and cat are at the same location.
          queue.add(new int[]{i, i, t, CAT_WIN});
        }
      }
    }

    // percolate nodes that we know who wins in the end
    while (!queue.isEmpty()) {
      // for nodes that are colored :
      int[] node = queue.remove();
      int m = node[0], c = node[1], t = node[2], curColor = node[3];
      // for every parent of this node m, c, t :
      for (int[] parent : findParents(graph, m, c, t)) {
        int m2 = parent[0], c2 = parent[1], t2 = parent[2];
        if (status[m2][c2][t2] != DRAW) {
          // if this parent is colored
          continue;
        }
        // if we do not know who wins in this parent node
        if ( (t2 == MOUSE_TURN && curColor==MOUSE_WIN) || (t2==CAT_TURN && curColor==CAT_WIN)) {
          // if the parent can make a winning move (ie. mouse to MOUSE)
          status[m2][c2][t2] = curColor;
          queue.add(new int[]{m2, c2, t2, curColor});
        } else {
          // else, this parent has degree[parent]--, and enqueue
          // Enqueue if all children of this parent are colored as losing moves.
          childrenNum[m2][c2][t2]--;
          if (childrenNum[m2][c2][t2] == 0) {
            status[m2][c2][t2] = 3 - t2;
            queue.add(new int[]{m2, c2, t2, 3 - t2});
          }
        }

      }
    }
    //The mouse is at location 1. The cat is at location 2. The mouse moves first.
    return status[1][2][1];
  }

  public void createChildrenNum(int[][] graph){
    //childrenNum[node] : the number of neutral children of this node
    for (int m = 0; m < N; ++m) {   //mouse location
      for (int c = 0; c < N; ++c) { //cat location
        childrenNum[m][c][1] = graph[m].length;
        childrenNum[m][c][2] = graph[c].length;
        for (int x : graph[c]) {
          if (x == 0) { //hole. The cat can not stay at the hole (position 0).
            childrenNum[m][c][2]--;
            break;
          }
        }
      }
    }
  }
  // What nodes could play their turn to
  // arrive at node (m, c, t) ?
  public List<int[]> findParents(int[][] graph, int m, int c, int t) {
    List<int[]> parents = new ArrayList();
    if (t == CAT_WIN) { //prev turn is mouse
      for (int nbr : graph[m]) {
        parents.add(new int[]{nbr, c, 3 - t});
      }
    } else {  //mouse turn
      for (int nbr : graph[c]) {
        if (nbr > 0) {
          parents.add(new int[]{m, nbr, 3 - t});
        }
      }
    }
    return parents;
  }
}
