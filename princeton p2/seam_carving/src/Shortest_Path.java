import java.util.ArrayList;

public class Shortest_Path {
    public class Node{
        double expense;
        double energy;
        int x;
        int y;
        int index;
        ArrayList<Node> connections;
        Node prevnode;
        public Node(int x_, int y_, double energy_, int index_){
            prevnode = null;
            x = x_;
            y = y_;
            energy = energy_;
            expense = Double.MAX_VALUE;
            connections = new ArrayList<Node>();
            index = index_;
        }

        public Iterable<Node> adj(){
            return connections;
        }
    }
    int n;
    ArrayList<Node> Graph;
    public Shortest_Path(){
        n=0;
        Graph = new ArrayList<Node>();
    }

    public void insert(int x, int y, double energy){
        Graph.add(new Node(x, y, energy, n++));
    }

    public void connect(int index1, int index2){
        Graph.get(index1).connections.add(Graph.get(index2));
    }

    private void relax(Node n1, Node n2){
        if(n2.expense > n1.expense + n2.energy){
            n2.expense = n1.expense + n2.energy;
            n2.prevnode = n1;
        }
    }

    public ArrayList<Integer> topologicalsort(){
        ArrayList<Integer> sorted = new ArrayList<Integer>();
        boolean[] isused = new boolean[n];

        connections_dfs(Graph.get(0), sorted, isused);

        return sorted;
    }

    private void connections_dfs(Node n, ArrayList<Integer> sortedarray, boolean[] isused){

        isused[n.index] = true;
        for(Node node: n.adj()){

            if(isused[node.index] == false){

                connections_dfs(node, sortedarray, isused);
            }

        }

        sortedarray.add(n.index);
    }

    public Node shortestpath(){
        ArrayList<Integer> sorted = topologicalsort();
        Graph.get(0).expense = Graph.get(0).energy;
        for(int i = sorted.size() - 1; i >= 0;i--){
            Node currnode = Graph.get(sorted.get(i));
            for(Node adjnode: currnode.adj()){
                relax(currnode, adjnode);
            }
        }
        return Graph.get(sorted.get(0));
    }

    public static void main(String[] args){
        Shortest_Path sp = new Shortest_Path();
        sp.insert(0,0,0);
        sp.insert(0,0,1);
        sp.insert(0,0,2);
        sp.insert(0,0,3);
        sp.insert(0,0,4);
        sp.insert(0,0,5);
        sp.insert(0,0,6);
        sp.insert(0,0,7);
        sp.insert(0,0,8);
        sp.insert(0,0,9);
        sp.insert(0,0,10);
        sp.connect(0,1);
        sp.connect(0,2);
        sp.connect(0,3);
        sp.connect(1,4);
        sp.connect(1,5);
        sp.connect(2,4);
        sp.connect(2,5);
        sp.connect(2,6);
        sp.connect(3,5);
        sp.connect(3,6);
        sp.connect(4,7);
        sp.connect(4,8);
        sp.connect(5,7);
        sp.connect(5,8);
        sp.connect(5,9);
        sp.connect(6,8);
        sp.connect(6,9);
        sp.connect(7,10);
        sp.connect(8,10);
        sp.connect(9,10);

        ArrayList<Integer> al = sp.topologicalsort();

        for(Integer I: al){
            System.out.println(I);
        }

        System.out.println();
        Node n = sp.shortestpath();

        while(n != null){
            System.out.println(n.index);
            n= n.prevnode;
        }
    }
}


