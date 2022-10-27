import java.lang.reflect.Array;
import java.util.ArrayList;

public class Graph {
    public class node{
        ArrayList<Integer> adjacents;
        int id;
        public node(int id_){
            id = id_;
            adjacents = new ArrayList<Integer>();
        }
        public Iterable<Integer> adj(){
            return adjacents;
        }
        public void connect(int other_id){
            adjacents.add(other_id);
        }
    }
    node[] Graph;

    public Graph(int elements){
        Graph = new node[elements];
        for(int i=0;i< elements;i++){
            Graph[i] = new node(i+1);
        }
    }

    public void connect(int element1, int element2){
        Graph[element1-1].connect(element2);
    }

    public node[] reversed(){
        int len = Graph.length;
        node[] revgraph = new node[len];
        for(int i=0;i<len;i++){
            revgraph[i] = new node(i+1);
        }

        for(int i=0;i<len;i++){
            node curr = Graph[i];

            for(int id: curr.adjacents){
                revgraph[id-1].connect(curr.id);
            }
        }

        return revgraph;
    }

    public void dfs1(node root,node[] source, ArrayList<Integer> top_sorted, boolean[] isused){
        isused[root.id-1] = true;
        for(int adj:root.adj()){
            if(! isused[adj - 1])dfs1(source[adj-1], source, top_sorted, isused);
        }
        top_sorted.add(root.id);
    }

    public void dfs2(node root, node[] source,int[] tagged, int tag){
        tagged[root.id-1]=tag;

        for(int i: root.adj()){
            if(tagged[i-1] == 0)dfs2(source[i-1], source, tagged, tag);
        }
    }

    public int[] get_scc_scheme(){
        node[] revgraph = this.reversed();
        int len = revgraph.length;
        ArrayList<Integer> topologically_sorted = new ArrayList<Integer>();
        boolean[] isused = new boolean[len];

        for(int i=0;i < len;i++){
            if(!isused[i])dfs1(revgraph[i], revgraph, topologically_sorted, isused);
        }

        int[] scheme = new int[len];
        int k=1;

        for(int i=len-1;i>=0;i--){
            int currind=topologically_sorted.get(i);
            if(scheme[currind-1] == 0)dfs2(Graph[currind-1], Graph, scheme, k++);
        }

        return scheme;
    }


    public static void main(String[] args){
        Graph g = new Graph(10);
        g.connect(1,7);
        g.connect(1,8);
        g.connect(1,9);
        g.connect(2,1);
        g.connect(2,10);
        g.connect(3,2);
        g.connect(3,4);
        g.connect(4,5);
        g.connect(5,10);
        g.connect(6,7);
        g.connect(7,6);
        g.connect(7,3);
        g.connect(10,9);
        g.connect(9,10);

        int[] scheme = g.get_scc_scheme();
        for(int i=0;i<scheme.length;i++){
            System.out.println((i+1) + "th node group is" + scheme[i]);
        }
    }
}
