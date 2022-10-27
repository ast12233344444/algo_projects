import java.util.ArrayList;
import java.util.PriorityQueue;

public class huffman_encoder {

    public Node HuffmanTree(ArrayList<Node> Nodes){
        PriorityQueue<Node> pqueue= new PriorityQueue<Node>(Nodes);

        while(pqueue.size() > 1){
            Node least = pqueue.poll();
            Node secleast = pqueue.poll();
            Node parent = new Node(least, secleast, least.freq + secleast.freq, false);
            pqueue.add(parent);
        }

        return pqueue.poll();
    }

    public int getmaxlen(Node node){
        return getmaxlen(node, 0);
    }

    public int getmaxlen(Node root, int depth){
        if(root.isvalid)return depth;

        int depth1 = getmaxlen(root.left, depth+1);
        int depth2 = getmaxlen(root.right, depth+1);

        return Math.max(depth1, depth2);
    }

    public int getnimlen(Node root){
        return getminlen(root, 0);
    }

    public int getminlen(Node root, int depth){
        if(root.isvalid)return depth;

        int depth1 = getminlen(root.left, depth+1);
        int depth2 = getminlen(root.right, depth+1);

        return Math.min(depth1, depth2);
    }

    public static void main(String[] args){
        In in = new In("freqs.txt");
        int k = in.readInt();
        ArrayList<Node> nodes = new ArrayList<Node>();
        huffman_encoder he = new huffman_encoder();

        for(int i =0;i<k;i++){
            long newfreq = in.readLong();
            nodes.add(new Node(null, null, newfreq, true));
        }

        Node rootaboveall = he.HuffmanTree(nodes);

        System.out.println("Max length is "+ he.getmaxlen(rootaboveall));
        System.out.println("Min length is "+ he.getnimlen(rootaboveall));
    }
}
