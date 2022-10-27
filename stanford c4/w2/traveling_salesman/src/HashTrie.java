import java.util.HashMap;

public class HashTrie {
    public class Node{
        int depth;
        int val;
        long[] min_dist_w_j;
        HashMap<Integer, Node> nextnodes;
        public Node(int val_,int depth_){
            val = val_;
            depth = depth_;
            min_dist_w_j = new long[depth_];
            nextnodes = new HashMap<Integer, Node>();
        }
    }

    Node root;

    public HashTrie(){
        root = new Node(1,1);
    }

    public void add(stack subset){
        if(subset.get(0) != 1){
            return;
        }

        root = add(root, subset);
    }
    public Node add(Node rootnode, stack subset){
        int depth = rootnode.depth;

        if(depth == subset.n)return rootnode;

        int val = subset.get(depth);

        if(rootnode.nextnodes.containsKey(val)){
            Node child = add(rootnode.nextnodes.get(val), subset);
            rootnode.nextnodes.put(val, child);
        }else{
            Node child = new Node(val,depth+1);
            child = add(child, subset);
            rootnode.nextnodes.put(val, child);
        }

        return rootnode;

    }

    public Node get(stack subset){
        if(subset.get(0) != 1){
            return null;
        }

        return get(root, subset);
    }

    public Node get(Node rootNode, stack subset){
        if(rootNode == null)return null;
        int depth = rootNode.depth;

        if(depth == subset.n)return rootNode;

        Node child = rootNode.nextnodes.get(subset.get(depth));
        return get(child, subset);
    }


    public static void main(String[] args){
        HashTrie h = new HashTrie();
        stack s = new stack();

        s.push(1);
        s.push(2);
        h.add(s);

        s.pull();
        s.push(3);
        h.add(s);

        s.pull();
        s.push(4);
        h.add(s);

        s.push(5);
        h.add(s);

        stack s2 = new stack();

        s2.push(1);
        s2.push(2);
        Node n1 = h.get(s2);
        System.out.println("val : " + n1.val + " depth: " + n1.depth + " childs: " + n1.nextnodes.keySet().size());

        s2.pull();
        s2.push(4);
        System.out.println(s2.get(0) + "" + s2.get(1) + "" + s2.get(2));
        Node n2 = h.get(s2);
        System.out.println("val : " + n2.val + " depth: " + n2.depth + " childs: " + n2.nextnodes.keySet().size());

        s2.pull();
        s2.push(6);
        n1 = h.get(s2);
        System.out.println("val : " + n1.val + " depth: " + n1.depth + " childs: " + n1.nextnodes.keySet().size());

    }
}
