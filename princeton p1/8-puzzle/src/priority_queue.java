import java.util.ArrayList;

public class priority_queue<Search_Node extends Comparable>{
    ArrayList<Search_Node> pqueue;
    int n;

    priority_queue(){
        pqueue = new ArrayList<Search_Node>();
        pqueue.add(null);
        n = 0;
    }

    public void insert(Search_Node obj){
        if(pqueue.size() > n+1)   pqueue.set(++n, obj);
        else pqueue.add(++n, obj);
        swim(n);
    }

    public Search_Node getMin(){
        Search_Node val = pqueue.get(1);
        pqueue.set(1, pqueue.get(n));
        sink(1);
        n--;
        return val;
    }

    public void swim(int index){
        while( index > 1 && pqueue.get(index).compareTo( pqueue.get(index/2)) < 0 ) {
            Search_Node temp = pqueue.get(index / 2);
            pqueue.set(index / 2, pqueue.get(index));
            pqueue.set(index, temp);
            index = index/2;
        }
    }

    public void sink(int index){
        while(index < (n)/2){
            int j = index*2;
            if(pqueue.get(j).compareTo(pqueue.get(j+1)) > 0){
                j++;
            }
            if(pqueue.get(index).compareTo(pqueue.get(j)) > 0){
                Search_Node temp = pqueue.get(j);
                pqueue.set(j, pqueue.get(index));
                pqueue.set(index, temp);
                index = j;
            }
            else break;
        }
    }

    public static void main(String[] args){

    }
}
