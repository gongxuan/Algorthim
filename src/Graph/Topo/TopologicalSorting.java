package Graph.Topo;

import java.util.*;

//LeetCode 207
public class TopologicalSorting {
    //assume this is a Directed acyclic graph
    public List<String> topo(List<String[]> pairs){
        Map<String,List<String>> graph = new HashMap<>();
        Set<String> vertices = graph.keySet();
        Set<String> visited = new HashSet<>();
        List<String> route = new LinkedList<>();
        for(String[] pair : pairs){
            graph.computeIfAbsent(pair[0],k->new ArrayList<>()).add(pair[1]);
        }
        for(String vertex : vertices){
            if(!visited.contains(vertex)){
                topoDFS(graph,visited,route,vertex);
            }
        }
        for(int i=0;i<route.size();i++){
            System.out.print(route.get(i)+" ");
        }
        return route;
    }
    public void topoDFS(Map<String,List<String>> graph,Set<String> visited, List<String> route,String cur){
        if(visited.contains(cur)){
            return ;
        }
        visited.add(cur);
        if(graph.containsKey(cur)){
            for(String next : graph.get(cur)){
                topoDFS(graph,visited,route,next);
            }
        }
        //if there is no adjacent vertices
        route.add(0,cur);
    }
    public static void main(String[] args){
        List<String[]> pairs = new LinkedList<>();

        pairs.add(new String[]{"1","2"});
        pairs.add(new String[]{"1","3"});
        pairs.add(new String[]{"2","3"});


        TopologicalSorting tp = new TopologicalSorting();
        tp.topo(pairs);
    }
}
