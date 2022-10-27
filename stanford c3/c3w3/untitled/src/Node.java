public class Node implements Comparable<Node>{
    Node left;
    Node right;
    long freq;
    boolean isvalid;
    public Node(Node left_, Node right_, long freq_, boolean isvalid_){
        left = left_;
        right = right_;
        freq = freq_;
        isvalid = isvalid_;
    }

    public int compareTo(Node o){
        if(this.freq > o.freq)return 1;
        else if(this.freq < o.freq) return -1;
        else return 0;
    }
}