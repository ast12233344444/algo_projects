import java.util.ArrayList;

public class stack {
    ArrayList<Integer> stack;
    int n;

    public stack(){
        stack = new ArrayList<Integer>();
        n =0;
    }

    public void push(int arg){
        stack.add(n, arg);
        n++;
    }

    public Integer pull(){
        return stack.get(--n);
    }

    public int get(int ind){
        return stack.get(ind);
    }
}
