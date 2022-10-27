import java.util.ArrayList;

public class union_find {

    int[] rank;
    int[] elements;
    public union_find(int elements_){
        elements = new int[elements_ + 1];
        rank = new int[elements_ + 1];
        for(int i=0;i<= elements_;i++){
            elements[i] = i;
        }
    }

    public int root(int index){
        ArrayList<Integer> comp = new ArrayList<Integer>();
        while(index != elements[index]){
            comp.add(index);
            index = elements[index];
        }

        for(int child:comp){
            elements[child] = index;
        }

        return index;
    }

    public boolean isconected(int ind1, int ind2){
        return root(ind1) == root(ind2);
    }

    public void connect(int index1, int index2){
        int root1 = root(index1);
        int root2 = root(index2);

        if(rank[root1] > rank[root2]){
            elements[root2] = root1;
        }
        else if(rank[root2] > rank[root1]){
            elements[root1] = root2;
        }
        else{
            elements[root2] = root1;
            rank[root1] += 1;
        }
    }


}
