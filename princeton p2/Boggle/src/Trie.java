public class Trie {
    int R = 128;
    private  class Node{
        Node[] children;
        int depth;
        char key;
        int value;

        public Node(int dept, char key_){
            value = 0;
            children = new Node[R];
            depth = dept;
            key  = key_;
        }

        public void set(){
            if(depth < 2) value = 0;
            else if(depth < 4) value = 1;
            else if(depth < 5) value = 2;
            else if(depth < 6) value = 3;
            else if(depth < 7) value = 5;
            else value = 11;
        }
    }

    Node root;
    public Trie(String directory){
        root = new Node(-1, ' ');
        In in = new In(directory);
        while (in.hasNextLine()){
            this.put(in.readLine());
        }
    }

    public void put(String s){
        root = put(root, s,0);
    }

    public Node put(Node n ,String s, int d){
        if(n == null) n = new Node(d-1, s.charAt(d-1));
        if( d == s.length()) n.set();
        else n.children[s.charAt(d)] = put(n.children[s.charAt(d)], s, d+1);
        return n;
    }

    public int has(CharStack s){
        int sl = s.n;
        Node cn = root;
        for(int i =0;i<sl;i++){
            cn = cn.children[s.get(i)];
            if(cn == null) return -1;
        }
        return cn.value;
    }

    public static void main(String[] args){
        Trie dict = new Trie("dictionary-shakespeare.txt");
        CharStack cs = new CharStack();
        cs.put('A');
        cs.put('B');
        cs.put('E');
        cs.put('R');
        cs.put('G');
        cs.put('A');
        cs.put('V');
        cs.put('E');
        cs.put('N');
        cs.put('N');
        /*cs.put('Y');*/


        System.out.println(dict.has(cs));
    }
}
