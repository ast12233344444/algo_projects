import java.util.ArrayList;

public class Graph {
    public class node{
        int id;
        ArrayList<weighted_edge> edges;
        public node(int id_){
            id = id_;
            edges = new ArrayList<weighted_edge>();
        }

    }
    node[] graph;
    public Graph(int vertices){
        graph = new node[vertices];
        for(int i =1; i <= vertices; i++){
            graph[i-1]=new node(i);
        }
    }

    public void connect(int i, int j, int weight){
        weighted_edge we = new weighted_edge(i ,j, weight);
        graph[i-1].edges.add(we);
        graph[j-1].edges.add(we);
    }

    public Iterable<weighted_edge> getEdges(int id){
        return graph[id-1].edges;
    }
}
