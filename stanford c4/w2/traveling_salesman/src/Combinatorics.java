import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Combinatorics {
    ArrayList<Integer> nodes;
    HashTrie subsets;
    int counter;

    public Combinatorics(ArrayList<Integer> elements){
        nodes = elements;
        subsets = new HashTrie();
    }

    public HashTrie getAllsubsets(){
        counter = 0;
        stack s = new stack();
        s.push(nodes.get(0));
        int len = nodes.size();

        for(int i=2; i <= len; i++){
            System.out.println("at length "+i);
            getSubsets(i-1, s, 1);
        }

        return subsets;
    }

    public void getSubsets(int depth, stack subset,int lowbound){
        if(depth == 0){
            /*subsets.add(subset);*/
            counter++;
            if(counter%100000 == 0)System.out.println("at " + counter);
            return;
        }

        int len = nodes.size();
        for(int i = lowbound; i < len - depth+1; i++){
            subset.push(nodes.get(i));
            getSubsets(depth -1, subset, i+1);
            subset.pull();
        }
    }

    public static void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<>();

        for(int i=1;i<=13;i++){
            arr.add(i);
        }

        Combinatorics comb = new Combinatorics(arr);

        stack s = new stack();
        s.push(arr.get(0));
        long t1 = System.currentTimeMillis();
        HashTree ht = comb.getAllSubsets();
        long t2 = System.currentTimeMillis();
        System.out.println((comb.counter + 1) + " subsets mad at " + (t2 - t1) + " seconds");


    }
}
