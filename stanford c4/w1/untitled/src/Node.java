import java.util.ArrayList;

public class Node {
    public class edge{
        int weight;
        int v;
        int w;
        public edge(int v_, int w_, int weight_){
            v = v_;
            w = w_;
            weight = weight_;
        }
    }
    int id;
    ArrayList<edge> connections;
    public Node(int id_){
        id = id_;
        connections = new ArrayList<edge>();
    }

    public void connect(int targetId, int weight){
        connections.add(new edge(this.id, targetId, weight));
    }
}
