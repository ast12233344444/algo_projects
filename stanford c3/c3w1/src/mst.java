import java.util.HashSet;
import java.util.PriorityQueue;

public class mst {
    public static void main(String[] args){
        HashSet<Integer> vertices = new HashSet<Integer>();
        PriorityQueue<weighted_edge> edges = new PriorityQueue<weighted_edge>();

        In in = new In("Graph.txt");
        int vert = in.readInt();
        int edge=in.readInt();
        Graph graph = new Graph(vert);
        for(int i=0;i< edge;i++){
            int node1 = in.readInt();
            int node2 = in.readInt();
            int weight = in.readInt();
            graph.connect(node1,node2, weight);
        }





        vertices.add(1);
        Iterable<weighted_edge> temp = graph.getEdges(1);
        for(weighted_edge we:temp){
            /*System.out.println(we.weight);*/
            edges.add(we);
        }
        int sum = 0;
        while(vertices.size() < vert){
            weighted_edge we = edges.poll();
            int newnode;
            if(! vertices.contains(we.m))newnode = we.m;
            else if(! vertices.contains(we.n)) newnode = we.n;
            else continue;
            vertices.add(newnode);
            sum += we.weight;
            Iterable<weighted_edge> newedges = graph.getEdges(newnode);
            for(weighted_edge nedg:newedges){
                if(! vertices.contains(nedg.n)) edges.add(nedg);
                else if(! vertices.contains(nedg.m)) edges.add(nedg);
            }
        }

        System.out.println(sum);
    }
}
