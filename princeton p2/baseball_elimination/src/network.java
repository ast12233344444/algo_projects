import java.util.ArrayList;

public class network {
    public class Node{
        ArrayList<flow_edge> connections;
        int id;
        String description;
        public Node(int id_, String desc){
            id = id_;
            connections = new ArrayList<flow_edge>();
            description = desc;
        }

        public Iterable<flow_edge> adj(){
            return connections;
        }
    }

    ArrayList<Node> Network;
    int n;
    flow_edge edgetonode[];
    Node rootnode;
    Node endnode;

    public network(){
        Network = new ArrayList<Node>();
        n = 0;
        insertnode("root");
        rootnode = Network.get(0);
        endnode = null;
    }

    public void insertnode(String description){
        Network.add(new Node(n++, description));
    }

    public void connect(int v, int w, int cap){
        flow_edge edge = new flow_edge(v, w, cap);
        Network.get(v).connections.add(edge);
        Network.get(w).connections.add(edge);
    }

    public void fixnodecount(int lastconnections, int[] capacities){
        insertnode("endnode");
        endnode = Network.get(n - 1);
        edgetonode = new flow_edge[n];

        for(int i = 1;i<=lastconnections;i++){
                int teamindex = endnode.id - i;
                connect(teamindex, endnode.id, capacities[capacities.length - i]);
        }
    }

    public int maxflow(){
        int allflow = 0;

        while(increaseflow()){
            int amount = Integer.MAX_VALUE;
            Node currnode = endnode;
            for(flow_edge e = edgetonode[endnode.id]; e != null; e = edgetonode[currnode.id]){
                if(e.usable_capacity_to(currnode.id) < amount) amount = e.usable_capacity_to(currnode.id);
                currnode = Network.get(e.other(currnode.id));
            }

            currnode = endnode;
            for(flow_edge e = edgetonode[endnode.id]; e != null; e = edgetonode[currnode.id]){
                e.change_flow(currnode.id, amount);
                currnode = Network.get(e.other(currnode.id));
            }

            allflow += amount;
        }
        return allflow;
    }

    public boolean isEliminated(){
        maxflow();

        for(flow_edge fe: rootnode.connections){
            if(fe.capacity != fe.flow)return true;
        }

        return false;
    }

    public Iterable<String> who_eliminated(){
        ArrayList<String> result = new ArrayList<String>();
        int teamnodes = endnode.connections.size();
        for(int i = 1; i <= teamnodes; i++){
            flow_edge fe = Network.get(n-i-1).connections.get(0);
            if (fe.capacity <= fe.flow) result.add(Network.get(n-i-1).description);
        }

        return result;
    }

    private boolean increaseflow(){
        boolean[] isused = new boolean[n];

        queue<Node> vertices = new queue<Node>();
        vertices.enque(rootnode);
        isused[0] = true;

        while(!vertices.isempty()){
            Node currnode = vertices.deque();
            for(flow_edge fe: currnode.adj()){
                if(fe.usable_capacity_to(fe.other(currnode.id)) > 0 && !isused[fe.other(currnode.id)]){
                    vertices.enque(Network.get(fe.other(currnode.id)));
                    isused[fe.other(currnode.id)] = true;
                    edgetonode[fe.other(currnode.id)] = fe;

                    if(fe.other(currnode.id) == endnode.id)return true;
                }
            }
        }
        return false;
    }




}
