import java.util.ArrayList;

public class all_pair_s_p {
    public static long shortest_shortest_path(ArrayList<Node> graph){
        int len = graph.size();
        long[][][] solutionTensor = new long[2][len][len];

        for(int i=0;i<len;i++){
            for(int j = 0; j < len;j++){
                solutionTensor[0][i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i =0;i < len;i++){
            Node currnode = graph.get(i);
            for(Node.edge e:currnode.connections){
                int otherid = e.w - 1;
                if(e.weight < solutionTensor[0][i][otherid]) solutionTensor[0][i][otherid] = e.weight;
            }
        }

        for(int k = 1; k < len; k++){
            for(int i = 0; i< len;i++){
                for(int j = 0; j < len;j++){
                    solutionTensor[1][i][j] = Math.min(solutionTensor[0][i][j], solutionTensor[0][i][k] + solutionTensor[0][k][j]);
                }
            }

            for(int i=0;i<len;i++){
                for(int j =0; j<len;j++){
                    solutionTensor[0][i][j] = solutionTensor[1][i][j];
                }
            }
        }

        for(int i=0; i<len;i++){
            if(solutionTensor[1][i][i] < 0){
                return Long.MIN_VALUE;
            }
        }

        long minval = solutionTensor[1][0][0];
        for(int i=0;i<len;i++){
            for(int j = 0; j< len;j++){
                if(solutionTensor[1][i][j] < minval) minval= solutionTensor[1][i][j];
            }
        }

        return minval;
    }

    public static ArrayList<Node> makegraph(String directory){
        In in = new In(directory);
        int n = in.readInt();
        int m = in.readInt();
        ArrayList<Node> graph = new ArrayList<Node>();

        for(int i=1;i <= n; i++){
            graph.add(new Node(i));
        }

        for(int i=0;i < m ;i++){
            int first = in.readInt();
            int second = in.readInt();
            int weight = in.readInt();
            graph.get(first - 1).connect(second, weight);
        }

        return graph;
    }

    public static void main(String[] args){
        ArrayList<Node> graph = makegraph("g1.txt");
        System.out.println(shortest_shortest_path(graph));

        graph = makegraph("g2.txt");
        System.out.println(shortest_shortest_path(graph));

        graph = makegraph("g3.txt");
        System.out.println(shortest_shortest_path(graph));
    }
}
