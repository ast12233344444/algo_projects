public class LinkedList {
    private class Node{
        Node prevnode;
        Node nextnode;
        char value;

        public Node(Node prn, Node nn, char val){
            prevnode = prn;
            nextnode = nn;
            value =val;
        }
    }
    Node curr;
    Node firstNode;
    Node LastNode;
    public LinkedList(){
        firstNode = null;
        LastNode = null;
        curr = null;
    }

    public void add(char value){
        Node n;
        if(firstNode == null){
            n = new Node(null, null, value);
            firstNode = n;
            curr = n;
            LastNode = n;
        }
        else{
            n = new Node(LastNode, null, value);
            LastNode.nextnode = n;
            LastNode = n;
        }
    }

    public int MoveToFirst(char val){
        curr = firstNode;
        int n = 0;
        if(curr.value == val)return 0;

        while (curr.nextnode != null){
            curr = curr.nextnode;
            n++;
            if(curr.value == val)break;

        }
        Node temp = curr.prevnode;
        curr.prevnode.nextnode = curr.nextnode;
        curr.nextnode.prevnode = temp;
        curr.nextnode = firstNode;
        firstNode.prevnode = curr;
        curr.prevnode = null;
        firstNode = curr;
        return n;
    }

    public char get_and_move(int index){
        int n=0;
        curr = firstNode;
        if(index == 0) return curr.value;

        while (n < index){
            curr = curr.nextnode;
            n++;
        }
        Node temp = curr.prevnode;
        curr.prevnode.nextnode = curr.nextnode;
        curr.nextnode.prevnode = temp;
        curr.nextnode = firstNode;
        firstNode.prevnode = curr;
        curr.prevnode = null;
        firstNode = curr;
        return curr.value;
    }

    public boolean listcheck(){
        if(firstNode.nextnode == null) return false;
        curr = firstNode.nextnode;
        while(curr.nextnode != null){
            System.out.print(curr.value);
            if(curr.nextnode.prevnode != curr) return false;
            if(curr.prevnode.nextnode != curr) return false;
            System.out.println(" check");
            curr = curr.nextnode;
        }
        return true;
    }
}
