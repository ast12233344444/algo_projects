import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Clusterer {
    public static void main(String[] args){
        In in = new In("clusters.txt");
        int k = in.readInt();
        System.out.println(k);
        union_find uf = new union_find(k);
        ArrayList<weighted_edge> edges = new ArrayList<weighted_edge>();

        while(in.hasNextLine()){
            int node1 = in.readInt();
            int node2 = in.readInt();
            int weight = in.readInt();
            edges.add(new weighted_edge(node1, node2, weight));
        }

        Collections.sort(edges);

        System.out.println(edges.get(0).weight);
        System.out.println(edges.get(1).weight);
        System.out.println(edges.get(2).weight);
        System.out.println(edges.get(edges.size() - 1).weight);



        int clusters = k;
        weighted_edge lastedge = null;
        int cluster_count = 4;

        for(weighted_edge edge:edges){
            if(clusters ==  cluster_count && !uf.isconected(edge.m, edge.n)){
                lastedge = edge;
                break;
            }
            if(! uf.isconected(edge.m, edge.n) && clusters > cluster_count){
                uf.connect(edge.m,edge.n);
                clusters--;
            }
        }
        System.out.println("---------------------------------");
        System.out.println(lastedge.weight);
    }

    public static int clustercount(union_find uf, int elements){
        HashSet<Integer> roots = new HashSet<>();
        for(int i=1;i<=elements;i++){
            if(! roots.contains(uf.root(i))){
                roots.add(uf.root(i));
            }
        }
        return roots.size();
    }
}
